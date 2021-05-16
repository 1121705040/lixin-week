
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author: LiXin
 * @CreateTime: 2021/05/16/ 13:42
 * @Presentation: 作业第一题
 */
public class GCLogAnalysisTest {
    private static Random random = new Random();
    public static void main(String[] args) {
        //当前毫秒数
        long timeMillis = System.currentTimeMillis();
        //持续运行毫秒数
        long timeOutMillis = TimeUnit.SECONDS.toMillis(1);
        //结束时间戳
        long enMillis = timeMillis + timeOutMillis;
        LongAdder counter = new LongAdder();
        System.out.println("正在执行......");
        //缓存一部分对象;进入老年代
        int cacheSize = 2000;
        Object[] cacheGarbage = new Object[cacheSize];
        //在此范围内持续循环
        while (System.currentTimeMillis() < enMillis){
            //生产垃圾对象
            Object garbage = generateGarbage(100*1024);
            //计数器
            counter.increment();
            int randomIndex = random.nextInt(2 * cacheSize);
            if (randomIndex < cacheSize){
                cacheGarbage[randomIndex] = garbage;
            }
        }
        System.out.println("执行结束! 共生成对象次数:"+counter.longValue());
    }

    private static Object generateGarbage(int max) {
        int randomSize = random.nextInt(max);
        int type = randomSize;
        switch (type){
            case 0:return new int[randomSize];
            case 1:return new byte[randomSize];
            case 2:return new double[randomSize];
            default:
                StringBuilder builder = new StringBuilder(); String randomString = "randomString-Anything"; while (builder.length() < randomSize) {
                builder.append(randomString);
                builder.append(max);
                builder.append(randomSize);
            }
                return builder.toString();
        }
    }
}
