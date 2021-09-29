package com.df4j.xcwork.autoconfigure.websocket;

import com.df4j.xcwork.base.constant.Constants;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.df4j.xcwork.base.constant.Constants.FRAME_CODE;

@ConfigurationProperties(prefix = FRAME_CODE + ".websocket")
public class WebSocketProperties {

    /**
     * 是否启用
     */
    private boolean enabled = true;

    /**
     * 连接路径
     */
    private String endpoint = FRAME_CODE;

    /**
     * 跨域配置
     */
    private String allowedOrigins = "*";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(String allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }
}
