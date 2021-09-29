package com.df4j.xcwork.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class XcworkTextWebSocketHandler extends TextWebSocketHandler {

    private Logger logger = LoggerFactory.getLogger(XcworkTextWebSocketHandler.class);

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if(logger.isDebugEnabled()) {
            logger.debug("[xcwork-websocket][连接关闭]status: {}", status);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        if(logger.isDebugEnabled()) {
            logger.debug("[xcwork-websocket][连接]session: {}", session);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if(logger.isDebugEnabled()) {
            logger.debug("[xcwork-websocket][处理消息]session: {}, message: {}", session, message);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(logger.isDebugEnabled()) {
            logger.debug("[xcwork-websocket][处理消息]session: {}, exception: {}", session, exception.toString());
        }
    }
}
