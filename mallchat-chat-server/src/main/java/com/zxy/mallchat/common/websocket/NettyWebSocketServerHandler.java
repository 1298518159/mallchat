package com.zxy.mallchat.common.websocket;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.zxy.mallchat.common.websocket.domain.enums.WSReqTypeEnum;
import com.zxy.mallchat.common.websocket.domain.enums.WSRespTypeEnum;
import com.zxy.mallchat.common.websocket.domain.vo.req.WSBaseReq;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import static com.zxy.mallchat.common.websocket.domain.enums.WSReqTypeEnum.*;


@Slf4j
@Sharable
public class  NettyWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete){
            System.out.println("握手完成");
        } else if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state()==IdleState.READER_IDLE){
                System.out.println("读空闲");
            }
        }
    }

    // 读取客户端发送的请求报文
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String text = msg.text();
        System.out.println(text);
        WSBaseReq req = JSONUtil.toBean(text, WSBaseReq.class);
        WSReqTypeEnum typeEnum = of(req.getType());
        switch (typeEnum){
            case AUTHORIZE:
                break;
            case HEARTBEAT:
                break;
            case LOGIN:
                System.out.println(111);
                ctx.channel().writeAndFlush(new TextWebSocketFrame("123"));
        }
    }
}
