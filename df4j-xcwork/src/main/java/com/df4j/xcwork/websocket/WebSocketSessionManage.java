package com.df4j.xcwork.websocket;

import javax.websocket.Session;

/**
 * WebSocketSession管理类
 */
public interface WebSocketSessionManage {
    /**
     * 设置会话
     * @param id
     * @param session
     */
    void set(String id, Session session);

    /**
     * 移除会话
     * @param id
     */
    void remove(String id);
}
