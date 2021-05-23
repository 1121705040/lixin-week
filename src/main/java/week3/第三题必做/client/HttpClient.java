package week3.第三题必做.client;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Random;

/**
 * @Author: LiXin
 * @CreateTime: 2021/05/16/ 22:53
 * @Presentation:
 */
public class HttpClient {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        //打开浏览器
        CloseableHttpClient client = HttpClients.createDefault();
        //创建地址
        String url = "http://localhost:8808";
        switch (random.nextInt(3)){
            case 0:
                url+="/1";
                break;
            case 1:
                url+="/2";
                break;
            case 2:
                url+="/3";
                break;
            default:
                url+="/1";
                break;
        }
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
}
