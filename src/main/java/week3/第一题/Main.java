package week3.第一题;

import week3.第一题.server.NettyHttpServer;
import week3.第一题.server.servers.HttpServiceTest;
import week3.第一题.server.servers.HttpServiceTest2;
import week3.第一题.server.servers.HttpServiceTest3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: LiXin
 * @CreateTime: 2021/05/23/ 16:00
 * @Presentation:
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Class[] claaz = {NettyHttpServer.class, HttpServiceTest.class, HttpServiceTest2.class, HttpServiceTest3.class};
        for (Class c:claaz) {
            Method m = c.getDeclaredMethod("main",new Class[]{String[].class});
            new Thread().start();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        m.invoke(null, new Object[]{new String[]{}});
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }, 0, 10);
        }
    }
}
