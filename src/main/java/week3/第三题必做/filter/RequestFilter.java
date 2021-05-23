package week3.第三题必做.filter;

import org.apache.flink.shaded.netty4.io.netty.channel.ChannelHandlerContext;
import org.apache.flink.shaded.netty4.io.netty.handler.codec.http.FullHttpRequest;
import org.apache.flink.shaded.netty4.io.netty.handler.codec.http.HttpHeaders;

/**
 * @Author: LiXin
 * @CreateTime: 2021/05/23/ 20:01
 * @Presentation:
 */
public class RequestFilter {
    public void filter(ChannelHandlerContext ctx, FullHttpRequest fullRequest){
        HttpHeaders headers = fullRequest.headers();
        headers.set("li","xin");
    }
}
