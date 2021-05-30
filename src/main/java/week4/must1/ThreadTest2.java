package week4.must1;

/**
 * @Author: LiXin
 * @CreateTime: 2021/05/29/ 18:01
 * @Presentation: 思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这
 * 个方法的返回值后，退出主线程? 写出你的方法，
 */
public class ThreadTest2 {
    public static void main(String[] args) throws Exception {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String result = show();
                System.out.println(result);
            }
        };
        Thread thread = new Thread(runnable);
        thread.setName("子线程");
        thread.start();
        Thread main = Thread.currentThread();
        main.setName("主线程");
        main.join(500);
        System.out.println(main.getName()+"退出");
    }
    public static String show(){
        return "方法已运行";
    }
}
