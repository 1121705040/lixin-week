package week2.sixth;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author: LiXin
 * @CreateTime: 2021/05/16/ 22:53
 * @Presentation:
 */
public class HttpClientTestAndOKHttp {
    public static void main(String[] args) throws IOException {
        httpClient();
    }

    /**
     * @author LiXin
     * @date 2021-05-16 23:23
     * @describe httpclient请求
     */
    public static void httpClient() throws IOException {
        //打开浏览器
        CloseableHttpClient client = HttpClients.createDefault();
        //创建地址
        String url = "http://localhost:8801/";
        //创建请求
        HttpGet httpGet = new HttpGet(url);
        //执行请求,并接受响应
        CloseableHttpResponse response = client.execute(httpGet);
        //判断状态码
        if (response.getStatusLine().getStatusCode() == 200){
            HttpEntity entity = response.getEntity();
            //将响应体转换为字符串
            String text = EntityUtils.toString(entity);
            System.out.println(text);
        }
        //释放资源
        response.close();
        client.close();
    }

    /**
     * @author LiXin
     * @date 2021-05-16 23:23
     * @describe  OK http 请求
     */
    public static void okHttp(){
        //创建okhttp客户端对象
//        new OkHttpClient();
        //创建网络请求
    }
}
