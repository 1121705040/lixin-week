package week3.第一题必做.server.servers;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: LiXin
 * @CreateTime: 2021/05/16/ 22:41
 * @Presentation: 课堂案例1
 */
public class HttpServiceTest2 {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8802);
        System.out.println("test2服务启动,监听地址和端口为 http://127.0.0.1:8802/");

        while(true){
            Socket accept = serverSocket.accept();
            service(accept);
        }
    }

    private static void service(Socket accept) {
        try {
            PrintWriter printWriter = new PrintWriter(accept.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello NIO-2";
            printWriter.println("Content-Length:"+body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
