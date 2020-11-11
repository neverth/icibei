package fun.neverth.icibei.gameserver;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author NeverTh
 * @date 19:40 2020/11/11
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 将字节解码为 HttpRequest、 HttpContent 和 LastHttpContent。并将 HttpRequest、 HttpContent 和 LastHttpContent 编码为字节
        // websocket协议本身是基于http协议的，所以这边也要使用http解编码器
        pipeline.addLast(new HttpServerCodec());
        // 写入一个文件的内容，以块的方式来写的处理器
        pipeline.addLast(new ChunkedWriteHandler());
        // 将一个 HttpMessage 和跟随它的多个 HttpContent 聚合
        // 为单个 FullHttpRequest 或者 FullHttpResponse（取
        // 决于它是被用来处理请求还是响应）。安装了这个之后，
        // ChannelPipeline 中的下一个 ChannelHandler 将只会
        // 收到完整的 HTTP 请求或响应
        // netty是基于分段请求的，HttpObjectAggregator的作用是将请求分段再聚合,参数是聚合字节的最大长度
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        // 按照 WebSocket 规范的要求，处理 WebSocket 升级握手、PingWebSocketFrame 、 PongWebSocketFrame 和 CloseWebSocketFrame
        // 文本和二进制数据帧被传递到管道中的下一个处理程序进行处理。
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new TextWebSocketFrameHandler());
    }
}
