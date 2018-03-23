package hmst.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Administrator on 2018/2/27 0027.
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        System.out.println("客户端连接服务器，开始发送数据.....");
        byte [] req = "QUERY TIME ORDER".getBytes();
        ByteBuf firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
        ctx.writeAndFlush(firstMessage);
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        System.out.println("client 读取server数据..");
        byte[]req = new byte[in.readableBytes()];
        in.readBytes(req);
        String body = new String(req,"UTF-8");
        System.out.println("服务器数据为"+body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
