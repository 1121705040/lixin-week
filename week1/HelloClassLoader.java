package week1;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author: LiXin
 * @CreateTime: 2021/05/06/ 12:55
 * @Presentation:
 */
public class HelloClassLoader extends ClassLoader{
    public static void main(String[] args) throws Exception {
        //得到byte数组
        byte[] bytes = getBytes();
        //遍历byte数组,转换字节
        byteFor(bytes);
        //调用hello方法
        invokeMethod(bytes);
    }

    private static void invokeMethod(byte[] bytes) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        HelloClassLoader he = new HelloClassLoader();
        Class<?> aClass = he.defineClass("Hello", bytes, 0, bytes.length);
        Object o = aClass.newInstance();
        Method hello = aClass.getMethod("hello");
        hello.invoke(o);
    }

    private static void byteFor(byte[] bytes) {
        for (int i=0;i<bytes.length;i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
    }

    private static byte[] getBytes() throws IOException {
        FileInputStream in = new FileInputStream("/Users/lixin/Downloads/作业相关/Hello/Hello.xlass");
        byte[] bytesTmp = new byte[1024*4];
        int length = in.read(bytesTmp);
        return Arrays.copyOf(bytesTmp,length);
    }
}
