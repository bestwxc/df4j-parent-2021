package com.df4j.xcwork.message;

/**
 * 消息接收监听类
 */
public interface MessageReceiverListener {
    /**
     * 接收消息
     * @param message
     * @return
     */
    boolean onMessage(Message message);
}
