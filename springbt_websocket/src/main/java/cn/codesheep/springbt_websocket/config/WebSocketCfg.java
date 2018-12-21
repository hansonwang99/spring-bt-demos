package cn.codesheep.springbt_websocket.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketCfg implements WebSocketMessageBrokerConfigurer {

    // 配置 (Message Broker) 消息代理
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic","/user");  // 使用内置的消息代理进行单播和广播: "/topic"用于广播, "/user"用于单播
        config.setApplicationDestinationPrefixes("/app");
    }

    // 注册一个 stomp客户端，使用 SockJS协议
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-endpoint").withSockJS();
    }
}
