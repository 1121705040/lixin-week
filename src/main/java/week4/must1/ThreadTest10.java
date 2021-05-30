package week4.mustdo1;

import java.util.concurrent.*;


/**
 * @Author: LiXin
 * @CreateTime: 2021/05/29/ 18:01
 * @Presentation: 思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这
 * 个方法的返回值后，退出主线程? 写出你的方法，
 */
public class ThreadTest10 {
    public static void main(String[] args) throws Exception {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> show());
        String show = stringCompletableFuture.get();
        System.out.println(show);
        Thread main = Thread.currentThread();
        main.setName("主线程");
        System.out.println(main.getName() + "退出");
    }

    public static String show(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "方法已运行";
    }
}
