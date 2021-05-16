# 4种GC策略分析

## 并行GC的运行分析

#### youngGen的GC一共发生了8次

1. young区从约33m->约3.7m(young区总容量约38m)  堆内存从约33m->月3.7m(堆总容量约125m)用时4ms

2. young区从约37m->约5.1m(young区总容量约71m)  堆内存从约37m->月8.4m(堆总容量约159m)用时6ms
3. young区从约71m->约5.1m(young区总容量约71m)  堆内存从约75m->月16m(堆总容量约159m)用时9ms
4. young区从约71m->约5.1m(young区总容量约138m)  堆内存从约83m->月24m(堆总容量约225m)用时7ms
5. young区从约138m->约5.1m(young区总容量约138m)  堆内存从约157m->月38m(堆总容量约225m用时52ms
6. young区从约138m->约20m(young区总容量约286m)  堆内存从约171m->月54m(堆总容量约373m)用时16ms
7. young区从约283m->约26m(young区总容量约289m)  堆内存从约317m->月82m(堆总容量约376m)用时71ms
8. young区从约289m->约41m(young区总容量约555m)  堆内存从约345m->月109m(堆总容量约643m)用时145mm

#### Full GC 一共发生了1次

1. young区从约419m->约65m(young区总容量约555m),Old区从约67m->约87m(old区总容量约153m)堆内存从约109m->93m(堆总容量约709m),元数据区约2m->约2m(总容量约1g).用时165mm

#### 解析:

因为我没有配置任何参数,所以堆内存在内存不够用的时候会自动扩容,从日志来看,young区的内存从开始的约38m一直扩容到555m,翻了14.6倍左右;整个堆内存则从开始的约125m扩容到约709m,翻了5.6倍左右.令人疑惑的是full GC的时候old区的内存反而增加了从约67m->约87m,这是为什么呢?

这是因为old去的总容量过大,full gc的时候old区的使用量还未达到清理标准,而young区又会在gc的时候将一些对象持久化到old区,于是old区的容量变大了.在young区还有两个幸存者区,每次垃圾回收都会去压缩young区,清理一些对象,那些幸存下来的对象过几个回合就被复制到old区.

这个结论是这么来的.我这次启动的时候加了参数-Xmx256m,整个堆内存的空间都变小了old区最大的时候也只分配了约175m的容量,于是full gc的时候,old被压缩了.

```java
1.084: [Full GC (Ergonomics) [PSYoungGen: 7892K->0K(75776K)] [ParOldGen: 168534K->145342K(175104K)] 176427K->145342K(250880K), [Metaspace: 2706K->2706K(1056768K)], 0.0257423 secs] [Times: user=0.08 sys=0.01, real=0.02 secs] 
```

而且这一次一共发生了40多次young gc,4次full gc,堆内存空间变小导致更频繁的gc,但每次gc的暂停时间变少了,因为空间小了,所以清理起来快了.

#### 适用场景:

因为gc暂停是会根据你堆内存的增大而增加的,如果项目运行环境内存过大,不建议使用,当然如果你对几十秒的gc暂停无感的话就无所谓了.

## CMS-GC 的运行分析

#### 日志:

```java
0.396: [GC (Allocation Failure) 0.396: [ParNew: 78468K->8669K(78656K), 0.0054095 secs] 135876K->74855K(253440K), 0.0054933 secs] [Times: user=0.01 sys=0.01, real=0.00 secs] 
0.422: [GC (Allocation Failure) 0.422: [ParNew: 78621K->8682K(78656K), 0.0055298 secs] 144807K->82989K(253440K), 0.0056232 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
0.448: [GC (Allocation Failure) 0.448: [ParNew: 78634K->8084K(78656K), 0.0050290 secs] 152941K->90583K(253440K), 0.0051136 secs] [Times: user=0.01 sys=0.01, real=0.00 secs] 
0.474: [GC (Allocation Failure) 0.474: [ParNew: 77949K->8695K(78656K), 0.0068354 secs] 160447K->99411K(253440K), 0.0069253 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
0.481: [GC (CMS Initial Mark) [1 CMS-initial-mark: 90716K(174784K)] 99699K(253440K), 0.0001708 secs] [Times: user=0.00 sys=0.01, real=0.00 secs] 
0.481: [CMS-concurrent-mark-start]
0.485: [CMS-concurrent-mark: 0.003/0.003 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
0.485: [CMS-concurrent-preclean-start]
0.485: [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
0.485: [CMS-concurrent-abortable-preclean-start]
0.502: [GC (Allocation Failure) 0.502: [ParNew: 78647K->8690K(78656K), 0.0054308 secs] 169363K->108199K(253440K), 0.0055252 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
0.529: [GC (Allocation Failure) 0.529: [ParNew: 78606K->8138K(78656K), 0.0051590 secs] 178115K->115855K(253440K), 0.0052523 secs] [Times: user=0.01 sys=0.01, real=0.00 secs] 
0.555: [GC (Allocation Failure) 0.555: [ParNew: 78090K->8681K(78656K), 0.0055596 secs] 185807K->125071K(253440K), 0.0056483 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
0.581: [GC (Allocation Failure) 0.581: [ParNew: 78633K->8378K(78656K), 0.0048732 secs] 195023K->132372K(253440K), 0.0049566 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
0.607: [GC (Allocation Failure) 0.607: [ParNew: 78330K->8693K(78656K), 0.0064796 secs] 202324K->142702K(253440K), 0.0065672 secs] [Times: user=0.01 sys=0.01, real=0.00 secs] 
0.634: [GC (Allocation Failure) 0.634: [ParNew: 78645K->8655K(78656K), 0.0050392 secs] 212654K->150402K(253440K), 0.0051417 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
0.660: [GC (Allocation Failure) 0.660: [ParNew: 78607K->8703K(78656K), 0.0053081 secs] 220354K->158735K(253440K), 0.0053972 secs] [Times: user=0.01 sys=0.01, real=0.00 secs] 
0.686: [GC (Allocation Failure) 0.686: [ParNew: 78655K->8406K(78656K), 0.0051848 secs] 228687K->166802K(253440K), 0.0052694 secs] [Times: user=0.01 sys=0.01, real=0.00 secs] 
0.712: [GC (Allocation Failure) 0.712: [ParNew: 78358K->8666K(78656K), 0.0052191 secs] 236754K->175364K(253440K), 0.0053092 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
0.717: [CMS-concurrent-abortable-preclean: 0.007/0.232 secs] [Times: user=0.27 sys=0.09, real=0.24 secs] 
0.717: [GC (CMS Final Remark) [YG occupancy: 8821 K (78656 K)]0.717: [Rescan (parallel) , 0.0002449 secs]0.717: [weak refs processing, 0.0000236 secs]0.717: [class unloading, 0.0002356 secs]0.718: [scrub symbol table, 0.0003112 secs]0.718: [scrub string table, 0.0001697 secs][1 CMS-remark: 166698K(174784K)] 175519K(253440K), 0.0010847 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
0.718: [CMS-concurrent-sweep-start]
0.719: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
0.719: [CMS-concurrent-reset-start]
0.719: [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
```

解析:我将最大堆内存调值256m一共发生了50多次ParNew-GC对应着并行gc的young gc,一共发生了7次cms gc,和并行gc不同的是cms gc是针对old区的垃圾回收的,并且是分为7步来执行的.

1. 初始标记
   1. 这个阶段会发生gc暂停,标记老年代的根对象.包含直接引用的对象.
2. 并发标记
   1. 并发的标记所有老年代中存活的对象,
3. 并发预清理,
   1. 这个阶段是把那些在标记过程中引用关系发生变化的对象标记起来,通过card的方式标记为脏数据.
4. 可取消的并发预清理
   1. 这个阶段是可中断的,在执行这一步的时候,可能会执行多个parnew gc.
5. 最终标记
   1. gc过程中第二次暂停.这个阶段把所有的存活对象都标记了起来.
6. 并发清楚
   1. 清除那些没被标记的对象,
7. 并发重置
   1. 重置cms算法内部数据

相对不并发gc ,cms gc将新生代和老年代的对象分开清理,尽量的减少了gc暂停时间.请能够回收老年代gc的时候,除了初始标记和最终标记需要gc暂停,其他步骤都不需要gc暂停.

如果单论老年区的gc暂停时间,那么cms gc是明显碾压并行gc 的.

#### 适用场景:

虽然old的垃圾回收做了并行的优化,但是young区和并行gc一样,也不适合在内存过大的环境运行,但是总体上比并行gc的延迟要低.如果并行gc可以放在8g堆内存上跑,你就可以放在16g内存上跑.

## G1-GC的运行分析

#### 日志:

```java
1.070: [GC pause (G1 Evacuation Pause) (young) 62M->60M(68M), 0.0006521 secs]
1.071: [GC concurrent-mark-end, 0.0017554 secs]
1.071: [GC remark, 0.0010118 secs]
1.072: [GC cleanup 61M->61M(68M), 0.0003354 secs]
1.073: [GC pause (G1 Evacuation Pause) (young)-- 63M->62M(68M), 0.0007433 secs]
1.075: [GC pause (G1 Evacuation Pause) (young)-- 64M->64M(68M), 0.0039824 secs]
1.079: [Full GC (Allocation Failure)  64M->59M(68M), 0.0143704 secs]
1.095: [GC pause (G1 Evacuation Pause) (young) 62M->60M(68M), 0.0013680 secs]
1.101: [GC pause (G1 Evacuation Pause) (young) (initial-mark)-- 63M->63M(68M), 0.0009508 secs]
1.103: [GC concurrent-root-region-scan-start]
1.103: [GC concurrent-root-region-scan-end, 0.0000060 secs]
1.103: [GC concurrent-mark-start]
1.103: [Full GC (Allocation Failure)  63M->59M(68M), 0.0078481 secs]
1.111: [GC concurrent-mark-abort]
1.112: [GC pause (G1 Evacuation Pause) (young) 62M->60M(68M), 0.0010201 secs]
1.114: [GC pause (G1 Evacuation Pause) (young) (initial-mark)-- 62M->60M(68M), 0.0011761 secs]
1.115: [GC concurrent-root-region-scan-start]
1.116: [GC concurrent-root-region-scan-end, 0.0001272 secs]
1.116: [GC concurrent-mark-start]
1.117: [GC pause (G1 Evacuation Pause) (young)-- 62M->62M(68M), 0.0006408 secs]
1.118: [Full GC (Allocation Failure)  62M->60M(68M), 0.0027369 secs]
1.121: [GC concurrent-mark-abort]
1.122: [GC pause (G1 Evacuation Pause) (young)-- 63M->63M(68M), 0.0006154 secs]
1.122: [Full GC (Allocation Failure)  63M->60M(68M), 0.0033270 secs]
```

在这个测试中我将最大堆内存调小到68m,在运行中由于内存分配失败,策略降级,变成了串行gc策略,但是g1 gc由于可是设置期望暂停时间,可以在一些情况下降gc暂停时间控制在很低的范围,我设置的期望暂停时间为2毫秒,堆内存是1g,共发生40次gc每次平均5毫秒.将堆内存调制4g,每次平均20几毫秒,而且相当稳定,也就是说,在不考虑full gc 的情况下G1 gc是能将暂停时间最小化的gc 策略.

串行化在条件允许的情况下也会执行并发标记回收,和cms gc并发策略原理一样.

#### 使用场景:

因为预期gc暂停时间的存在,可是胜任更大堆内存环境,而且old区回收也用到了并行回收,整体暂停时间要比并行gc和cms gc更短.如果cms gc能够胜任16g内存,那你就能胜任64g内存.

## 串行GC

#### 日志:

```java
CommandLine flags: -XX:InitialHeapSize=1073741824 -XX:MaxGCPauseMillis=2 -XX:MaxHeapSize=1073741824 -XX:+PrintGC -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseSerialGC 
0.560: [GC (Allocation Failure)  279616K->33521K(1013632K), 0.0322730 secs]
0.718: [GC (Allocation Failure)  313137K->59950K(1013632K), 0.1028107 secs]
0.936: [GC (Allocation Failure)  339566K->86423K(1013632K), 0.0642861 secs]
1.133: [GC (Allocation Failure)  366039K->113178K(1013632K), 0.0286330 secs]
```

串行gc在4g堆内存下的表现,虽然说垃圾回收次数少,但是这有一次gc的暂停时间直接0.1毫秒.而且如果在内存配置小的话(123m)会频繁的full gc

```java
0.514: [GC (Allocation Failure)  115641K->84951K(122752K), 0.0052892 secs]
0.531: [Full GC (Allocation Failure)  118655K->79049K(122752K), 0.0171494 secs]
0.560: [GC (Allocation Failure)  112797K->83665K(122752K), 0.0010955 secs]
0.573: [GC (Allocation Failure)  117391K->87603K(122752K), 0.0025390 secs]
0.587: [Full GC (Allocation Failure)  121459K->86044K(122752K), 0.0169182 secs]
0.617: [Full GC (Allocation Failure)  122688K->88236K(122752K), 0.0137254 secs]
0.643: [Full GC (Allocation Failure)  122632K->85962K(122752K), 0.0197787 secs]
0.676: [Full GC (Allocation Failure)  122531K->91571K(122752K), 0.0032029 secs]
0.690: [Full GC (Allocation Failure)  122661K->94887K(122752K), 0.0028005 secs]
0.703: [Full GC (Allocation Failure)  122577K->96921K(122752K), 0.0073716 secs]
0.719: [Full GC (Allocation Failure)  122500K->94754K(122752K), 0.0193691 secs]
0.748: [Full GC (Allocation Failure)  122682K->98113K(122752K), 0.0055240 secs]
0.761: [Full GC (Allocation Failure)  122587K->100660K(122752K), 0.0042514 secs]
0.774: [Full GC (Allocation Failure)  122736K->102316K(122752K), 0.0042467 secs]
0.786: [Full GC (Allocation Failure)  122733K->100686K(122752K), 0.0218749 secs]
0.814: [Full GC (Allocation Failure)  122440K->103048K(122752K), 0.0057939 secs]
0.826: [Full GC (Allocation Failure)  122580K->105665K(122752K), 0.0045484 secs]
0.838: [Full GC (Allocation Failure)  122578K->107841K(122752K), 0.0036632 secs]
0.846: [Full GC (Allocation Failure)  122697K->105884K(122752K), 0.0234623 secs]
0.878: [Full GC (Allocation Failure)  122599K->107789K(122752K), 0.0068434 secs]
0.890: [Full GC (Allocation Failure)  122664K->109588K(122752K), 0.0040832 secs]
0.899: [Full GC (Allocation Failure)  122703K->110709K(122752K), 0.0068041 secs]
0.909: [Full GC (Allocation Failure)  122682K->108425K(122752K), 0.0218085 secs]
0.938: [Full GC (Allocation Failure)  122668K->109331K(122752K), 0.0030332 secs]
0.945: [Full GC (Allocation Failure)  122706K->111375K(122752K), 0.0065003 secs]
0.957: [Full GC (Allocation Failure)  122744K->112450K(122752K), 0.0024688 secs]
0.962: [Full GC (Allocation Failure)  122748K->110851K(122752K), 0.0229607 secs]
0.990: [Full GC (Allocation Failure)  122679K->112052K(122752K), 0.0045650 secs]
0.998: [Full GC (Allocation Failure)  122485K->113319K(122752K), 0.0065405 secs]
1.008: [Full GC (Allocation Failure)  122470K->114283K(122752K), 0.0029282 secs]
1.014: [Full GC (Allocation Failure)  122556K->113628K(122752K), 0.0247396 secs]
1.042: [Full GC (Allocation Failure)  122640K->115139K(122752K), 0.0043623 secs]
1.051: [Full GC (Allocation Failure)  122727K->115125K(122752K), 0.0068199 secs]
1.060: [Full GC (Allocation Failure)  122722K->116128K(122752K), 0.0075068 secs]
1.071: [Full GC (Allocation Failure)  122744K->115682K(122752K), 0.0195949 secs]
1.093: [Full GC (Allocation Failure)  122732K->116483K(122752K), 0.0021621 secs]
1.097: [Full GC (Allocation Failure)  122536K->117834K(122752K), 0.0067924 secs]
```

并行gc 也一样,但人家并行gc在多核CPU下垃圾回收速度快,且串行也是每次gc都会进行gc暂停.后来串行gc并改进了一次,在进行young区回收的时候会使用parnew 垃圾回收算法,但是我这个jdk版本没有更新这个点.

#### 适用场景:

在几百兆项目且单核的cup的情况下推荐使用.