package week3.第一题必做.server;


import org.apache.flink.shaded.netty4.io.netty.buffer.Unpooled;
import org.apache.flink.shaded.netty4.io.netty.channel.ChannelFutureListener;
import org.apache.flink.shaded.netty4.io.netty.channel.ChannelHandlerContext;
import org.apache.flink.shaded.netty4.io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.flink.shaded.netty4.io.netty.handler.codec.http.DefaultFullHttpResponse;
import org.apache.flink.shaded.netty4.io.netty.handler.codec.http.FullHttpRequest;
import org.apache.flink.shaded.netty4.io.netty.handler.codec.http.FullHttpResponse;
import org.apache.flink.shaded.netty4.io.netty.handler.codec.http.HttpUtil;
import org.apache.flink.shaded.netty4.io.netty.util.ReferenceCountUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static org.apache.flink.shaded.netty4.io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static org.apache.flink.shaded.netty4.io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static org.apache.flink.shaded.netty4.io.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.apache.flink.shaded.netty4.io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static org.apache.flink.shaded.netty4.io.netty.handler.codec.rtsp.RtspHeaderValues.KEEP_ALIVE;

public class HttpHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            //logger.info("channelRead流量接口请求开始，时间为{}", startTime);
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            String uri = fullRequest.uri();
            //logger.info("接收到的请求url为{}", uri);
            if (uri.contains("/1")) {
                handlerSum(fullRequest, ctx);
            }else if (uri.contains("/2")){
                handlerDif(fullRequest, ctx);
            }else if (uri.contains("/3")){
                handlerQua(fullRequest, ctx);
            }else {
                handlerMsg(fullRequest, ctx);
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void handlerSum(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;
        try {
            String value = connectionServer("http://127.0.0.1:8801");  // "hello,kimmking"; // 对接上次作业的httpclient或者okhttp请求另一个url的响应数据
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());

        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }

    private void handlerDif(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;
        try {
            String value = connectionServer("http://127.0.0.1:8802");  // "hello,kimmking"; // 对接上次作业的httpclient或者okhttp请求另一个url的响应数据
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());

        } catch (Exception e) {
            System.out.println("处理出错:"+e.getMessage());
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }

    private void handlerQua(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;
        try {
            String value = connectionServer("http://127.0.0.1:8803");  // "hello,kimmking"; // 对接上次作业的httpclient或者okhttp请求另一个url的响应数据
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());

        } catch (Exception e) {
            System.out.println("处理出错:"+e.getMessage());
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }

    private void handlerMsg(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;
        try {
            String value = "请输入/1 /2 /3 ";  // "hello,kimmking"; // 对接上次作业的httpclient或者okhttp请求另一个url的响应数据
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());

        } catch (Exception e) {
            System.out.println("处理出错:"+e.getMessage());
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    public String connectionServer(String url) throws IOException {
        //打开浏览器
        CloseableHttpClient client = HttpClients.createDefault();
        //创建请求
        HttpGet httpGet = new HttpGet(url);
        //执行请求,并接受响应
        CloseableHttpResponse response = client.execute(httpGet);
        //判断状态码
        String text="";
        if (response.getStatusLine().getStatusCode() == 200){
            HttpEntity entity = response.getEntity();
            //将响应体转换为字符串
            text = EntityUtils.toString(entity);
        }
        //释放资源
        response.close();
        client.close();
        return text;
    }

}
