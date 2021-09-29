package com.df4j.xcwork.message;

/**
 * 消息发送接口
 */
public interface MessageSender {
    /**
     * 发送消息
     * @param message
     */
    void sendMessage(Message message);
}
