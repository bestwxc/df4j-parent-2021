package com.df4j.xcwork.autoconfigure.websocket;

import com.df4j.xcwork.base.constant.Constants;
import com.df4j.xcwork.websocket.XcworkTextWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistration;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.websocket.server.ServerContainer;
import java.util.List;

@Configuration
@EnableWebSocket
@EnableConfigurationProperties(WebSocketProperties.class)
@AutoConfigureAfter(WebSocketServletAutoConfiguration.class)
@ConditionalOnClass({ServerContainer.class, WebSocketConfigurer.class})
@ConditionalOnProperty(prefix = Constants.FRAME_CODE + ".websocket", value = "enabled", havingValue = "true", matchIfMissing = true)
public class WebSocketAutoConfiguration implements WebSocketConfigurer {

    @Autowired
    private WebSocketProperties webSocketProperties;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        XcworkTextWebSocketHandler handler = new XcworkTextWebSocketHandler();
        WebSocketHandlerRegistration registration = registry.addHandler(handler, webSocketProperties.getEndpoint());
        registration.setAllowedOrigins(webSocketProperties.getAllowedOrigins());
    }
}
