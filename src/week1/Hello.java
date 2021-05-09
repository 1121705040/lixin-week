package week1;

/**
 * @Author: LiXin
 * @CreateTime: 2021/05/09/ 18:21
 * @Presentation:
 *
 *  Classfile /Users/lixin/lixin-week/src/week1/Hello.class
 *   Last modified 2021-5-9; size 774 bytes
 *   MD5 checksum e948047e49c6a8c243cfd7ec65c13102
 *   Compiled from "Hello.java"
 * public class week1.Hello
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #8.#19         // java/lang/Object."<init>":()V
 *    #2 = Class              #20            // java/lang/Integer
 *    #3 = Methodref          #2.#21         // java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 *    #4 = Methodref          #2.#22         // java/lang/Integer.intValue:()I
 *    #5 = Fieldref           #23.#24        // java/lang/System.out:Ljava/io/PrintStream;
 *    #6 = Methodref          #25.#26        // java/io/PrintStream.println:(Ljava/lang/Object;)V
 *    #7 = Class              #27            // week1/Hello
 *    #8 = Class              #28            // java/lang/Object
 *    #9 = Utf8               <init>
 *   #10 = Utf8               ()V
 *   #11 = Utf8               Code
 *   #12 = Utf8               LineNumberTable
 *   #13 = Utf8               main
 *   #14 = Utf8               ([Ljava/lang/String;)V
 *   #15 = Utf8               StackMapTable
 *   #16 = Class              #29            // "[Ljava/lang/Integer;"
 *   #17 = Utf8               SourceFile
 *   #18 = Utf8               Hello.java
 *   #19 = NameAndType        #9:#10         // "<init>":()V
 *   #20 = Utf8               java/lang/Integer
 *   #21 = NameAndType        #30:#31        // valueOf:(I)Ljava/lang/Integer;
 *   #22 = NameAndType        #32:#33        // intValue:()I
 *   #23 = Class              #34            // java/lang/System
 *   #24 = NameAndType        #35:#36        // out:Ljava/io/PrintStream;
 *   #25 = Class              #37            // java/io/PrintStream
 *   #26 = NameAndType        #38:#39        // println:(Ljava/lang/Object;)V
 *   #27 = Utf8               week1/Hello
 *   #28 = Utf8               java/lang/Object
 *   #29 = Utf8               [Ljava/lang/Integer;
 *   #30 = Utf8               valueOf
 *   #31 = Utf8               (I)Ljava/lang/Integer;
 *   #32 = Utf8               intValue
 *   #33 = Utf8               ()I
 *   #34 = Utf8               java/lang/System
 *   #35 = Utf8               out
 *   #36 = Utf8               Ljava/io/PrintStream;
 *   #37 = Utf8               java/io/PrintStream
 *   #38 = Utf8               println
 *   #39 = Utf8               (Ljava/lang/Object;)V
 * {
 *   public week1.Hello();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0          //	将第一个引用类型局部变量推送至栈顶
 *          1: invokespecial #1     //调用超类构造方法/初始化实例           // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 90: 0
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=5, locals=6, args_size=1
 *          0: bipush        6         //将单字节的常量6推至栈顶
 *          2: anewarray     #2           //创建一个引用型数组       // class java/lang/Integer
 *          5: dup                      //复制栈顶数值,并压入栈顶
 *          6: iconst_0                 //将int型0推送至栈顶,做索引
 *          7: iconst_1                 //将int型0推送至栈顶,索引值
 *          8: invokestatic  #3         //调用静态方法         // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 *         11: aastore                  //将栈顶的引用数值,存入引用型数组
 *         12: dup                      //复制栈顶数值,并压入栈顶;目前Integer[]=={1}
 *         13: iconst_1                 //将int型1推送至栈顶
 *         14: iconst_2                 //将int型2推送至栈顶
 *         15: invokestatic  #3         //调用静态方法         // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 *         18: aastore                  //将栈顶的引用数值,存入引用型数组
 *         19: dup                      //复制栈顶数值,并压入栈顶;目前Integer[]=={1,2}
 *         20: iconst_2
 *         21: iconst_3
 *         22: invokestatic  #3                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 *         25: aastore
 *         26: dup                       //复制栈顶数值,并压入栈顶;目前Integer[]=={1,2,3}
 *         27: iconst_3
 *         28: iconst_4
 *         29: invokestatic  #3                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 *         32: aastore
 *         33: dup                      //复制栈顶数值,并压入栈顶;目前Integer[]=={1,2,3,4}
 *         34: iconst_4
 *         35: iconst_5
 *         36: invokestatic  #3                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 *         39: aastore
 *         40: dup                      //复制栈顶数值,并压入栈顶;目前Integer[]=={1,2,3,4,5}
 *         41: iconst_5                 //将int型5推送至栈顶
 *         42: bipush        6          //将int型5推送至栈顶,值为0-5时用iconst,6以上其他[-128~127]使用bipush
 *         44: invokestatic  #3         //调用静态方法         // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 *         47: aastore              //将引用类型的值存入引用类型数组
 *         48: astore_1             //将栈顶的引用类型值存在,1索引的局部变量; 目前Integer[]=={1,2,3,4,5}
 *         49: iconst_0             // 将int型0推送至栈顶,做索引
 *         50: istore_2             // 将int值存入,2索引的局部变量i
 *         51: iload_2              // 将2索引的局部变量i加载到栈顶
 *         52: aload_1              // 将1索引的局部变量arr加载到栈顶
 *         53: arraylength          //获得数组的长度值并压入栈顶
 *         54: if_icmpge     135    //如果i大于等于 arr.length,则跳到第135步
 *         57: aload_1              // 将1索引的局部变量arr加载到栈顶
 *         58: iload_2              // 将2索引的局部变量i加载到栈顶
 *         59: aload_1              // 将1索引的局部变量arr加载到栈顶
 *         60: iload_2              // 将2索引的局部变量i加载到栈顶
 *         61: aaload               // 将引用类型数组(aload_1)中i索引(iload_2)的值加载到栈顶
 *         62: invokevirtual #4      //调用实例方法        // Method java/lang/Integer.intValue:()I
 *         65: iconst_2             //将int型2推送至栈顶,做加法
 *         66: iadd                 //将栈顶两int型数值相加并将结果压入栈顶
 *         67: invokestatic  #3      //调用静态方法            // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 *         70: aastore              //将结果保存到引用类型数组(aload_1)中i索引(iload_2)
 *         71: aload_1              // 将1索引的局部变量arr加载到栈顶
 *         72: iload_2              // 将2索引的局部变量i加载到栈顶
 *         73: aload_1              // 将1索引的局部变量arr加载到栈顶
 *         74: iload_2              // 将2索引的局部变量i加载到栈顶
 *         75: aaload               // 将引用类型数组arr(aload_1)中i索引(iload_2)的值加入栈顶
 *         76: invokevirtual #4     //调用实例方法             // Method java/lang/Integer.intValue:()I
 *         79: iconst_1             //将int类型常量1加入栈顶
 *         80: isub                 //将栈顶两个int类型值相减并将结果压入栈顶
 *         81: invokestatic  #3                // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 *         84: aastore             //将结果保存到引用类型数组(aload_1)中i索引(iload_2)
 *         85: aload_1              // 将1索引的局部变量arr加载到栈顶
 *         86: iload_2              // 将2索引的局部变量i加载到栈顶
 *         87: aload_1              // 将1索引的局部变量arr加载到栈顶
 *         88: iload_2              // 将2索引的局部变量i加载到栈顶
 *         89: aaload               // 将数组arr(aload_1)的i索引(iload_2)的值压入栈顶
 *         90: invokevirtual #4                  // Method java/lang/Integer.intValue:()I
 *         93: aload_1              // 将1索引的局部变量arr加载到栈顶
 *         94: iload_2              // 将2索引的局部变量i加载到栈顶
 *         95: aaload               // 将数组arr(aload_1)的i索引(iload_2)的值压入栈顶
 *         96: invokevirtual #4                  // Method java/lang/Integer.intValue:()I
 *         99: imul                 //将栈顶两个int类型值相乘并将结果压入栈顶
 *        100: invokestatic  #3                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 *        103: aastore              //将栈顶的值存入arr[i]中
 *        104: aload_1              // 将1索引的局部变量arr加载到栈顶
 *        105: iload_2q             // 将2索引的局部变量i加载到栈顶
 *        106: aaload               //将数组arr(i)的值压入栈顶
 *        107: invokevirtual #4                  // Method java/lang/Integer.intValue:()I
 *        110: iconst_2             //将一个常量2,压入栈顶
 *        111: irem                 //将栈顶两int型数值作取模运算并将结果压入栈顶
 *        112: ifne          129    //当栈顶int值不等于0时调到129步
 *        115: aload_1              // 将1索引的局部变量arr加载到栈顶
 *        116: iload_2             // 将2索引的局部变量i加载到栈顶
 *        117: aload_1              // 将1索引的局部变量arr加载到栈顶
 *        118: iload_2             // 将2索引的局部变量i加载到栈顶
 *        119: aaload               //将arr[i]的值压入栈顶
 *        120: invokevirtual #4                  // Method java/lang/Integer.intValue:()I
 *        123: iconst_2            //将int型常量2压入栈顶
 *        124: idiv                 //将栈顶两个int值相除,结果压入栈顶
 *        125: invokestatic  #3                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 *        128: aastore              //将栈顶值存入arr[i]中
 *        129: iinc          2, 1   //变量表中2索引处的变量+1,也就是i+1
 *        132: goto          51     //无条件跳转
 *        135: return
 *       LineNumberTable:
 *         line 92: 0
 *         line 93: 49
 *         line 94: 57
 *         line 95: 71
 *         line 96: 85
 *         line 97: 104
 *         line 98: 115
 *         line 93: 129
 *         line 101: 135
 *         line 102: 155
 *         line 101: 163
 *         line 104: 169
 *       StackMapTable: number_of_entries = 5
 *         frame_type = 253
 *        offset_delta=51
 *        locals=[class "[Ljava/lang/Integer;",int]
 *      frame_type=251  same_frame_extended
 *       offset_delta=77
 *       frame_type=250  chop
 *       offset_delta=5
 *       frame_type=254  append
 *       offset_delta=7
 *       locals=[class "[Ljava/lang/Integer;",int,int]
 *       frame_type=248  chop
 *       offset_delta=25
 *  }
 *      LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *            51      84     2     i   I
 *             0     136     0  args   [Ljava/lang/String;
 *            49      87     1   arr   [Ljava/lang/Integer;
 *
 */
public class Hello {
    public static void main(String[] args) {
        Integer[] arr = {1,2,3,4,5,6};
        for (int i=0;i<arr.length; i++) {
            arr[i] = arr[i]+2;
            arr[i] = arr[i]-1;
            arr[i] = arr[i]*arr[i];
            if (arr[i]%2 ==0){
                arr[i] = arr[i]/2;
            }
        }
    }
}
