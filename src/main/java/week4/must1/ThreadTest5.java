package week4.must1;

/**
 * @Author: LiXin
 * @CreateTime: 2021/05/29/ 18:01
 * @Presentation: 思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这
 * 个方法的返回值后，退出主线程? 写出你的方法，
 */
public class ThreadTest5 {
    public static void main(String[] args) throws Exception {
        Thread main = Thread.currentThread();
        main.setName("主线程");
        Thread thread = new MyThread();
        synchronized(thread) {
            thread.setName("子线程");
            thread.start();
            thread.wait();
            System.out.println(main.getName() + "退出");
        }
    }

    public static class MyThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                String result = show();
                System.out.println(result);
                this.notify();
            }
            try {
                Thread.sleep(1000);
                System.out.println("notify已执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public String show(){
            return "方法已运行";
        }
    }

}
