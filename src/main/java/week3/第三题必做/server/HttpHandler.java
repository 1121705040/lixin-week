package week3.第三题必做.server;


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
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import week3.第三题必做.filter.RequestFilter;
import week3.第三题必做.filter.ResponseFilter;

import java.io.IOException;

import static org.apache.flink.shaded.netty4.io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static org.apache.flink.shaded.netty4.io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static org.apache.flink.shaded.netty4.io.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.apache.flink.shaded.netty4.io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static org.apache.flink.shaded.netty4.io.netty.handler.codec.rtsp.RtspHeaderValues.KEEP_ALIVE;

public class HttpHandler extends ChannelInboundHandlerAdapter {
    private CloseableHttpAsyncClient httpclient;
    private RequestFilter requestFilter = new RequestFilter();
    private ResponseFilter responseFilter = new ResponseFilter();

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            String uri = fullRequest.uri();
            int cores = Runtime.getRuntime().availableProcessors();

            IOReactorConfig ioConfig = IOReactorConfig.custom()
                    .setConnectTimeout(1000)
                    .setSoTimeout(1000)
                    .setIoThreadCount(cores)
                    .setRcvBufSize(32 * 1024)
                    .build();
            httpclient = HttpAsyncClients.custom().setMaxConnTotal(40)
                    .setMaxConnPerRoute(8)
                    .setDefaultIOReactorConfig(ioConfig)
                    .setKeepAliveStrategy((response,context) -> 6000)
                    .build();
            httpclient.start();
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
        try {
            connectionServer("http://127.0.0.1:8801",ctx,fullRequest);  // "hello,kimmking"; // 对接上次作业的httpclient或者okhttp请求另一个url的响应数据
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void handlerDif(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        try {
            connectionServer("http://127.0.0.1:8802",ctx,fullRequest);  // "hello,kimmking"; // 对接上次作业的httpclient或者okhttp请求另一个url的响应数据
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handlerQua(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        try {
            connectionServer("http://127.0.0.1:8803",ctx,fullRequest);  // "hello,kimmking"; // 对接上次作业的httpclient或者okhttp请求另一个url的响应数据
        } catch (Exception e) {
            e.printStackTrace();
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

    public String connectionServer(String url, ChannelHandlerContext ctx,FullHttpRequest inbound) throws IOException {
        //创建请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        requestFilter.filter(ctx,inbound);
        httpclient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                FullHttpResponse response = null;
                try {
                    byte[] body = EntityUtils.toByteArray(httpResponse.getEntity());
                    response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
                    response.headers().set("Content-Type", "application/json");
                    response.headers().setInt("Content-Length", Integer.parseInt(httpResponse.getFirstHeader("Content-Length").getValue()));
                    responseFilter.filter(response);
                } catch (Exception e) {
                    e.printStackTrace();
                    response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
                    exceptionCaught(ctx, e);
                } finally {
                    if (inbound != null) {
                        if (!HttpUtil.isKeepAlive(inbound)) {
                            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                        } else {
                            ctx.write(response);
                        }
                    }
                    ctx.flush();
                }

            }

            @Override
            public void failed(Exception e) {
                httpGet.abort();
                e.printStackTrace();
            }

            @Override
            public void cancelled() {
                httpGet.abort();
            }
        });
        //执行请求,并接受响应
        String text="";
        return text;
    }

}
