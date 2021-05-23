package week3.第三题必做.filter;

import org.apache.flink.shaded.netty4.io.netty.buffer.ByteBuf;
import org.apache.flink.shaded.netty4.io.netty.buffer.Unpooled;
import org.apache.flink.shaded.netty4.io.netty.handler.codec.http.FullHttpResponse;
import org.apache.flink.shaded.netty4.io.netty.util.CharsetUtil;

/**
 * @Author: LiXin
 * @CreateTime: 2021/05/23/ 20:01
 * @Presentation:
 */
public class ResponseFilter {
    public void filter(FullHttpResponse response){
        ByteBuf content = response.content();
        System.out.println("返回内容为"+content.toString(CharsetUtil.UTF_8));
    }
}
