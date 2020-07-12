package fun.neverth.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import fun.neverth.event.RabbitReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/12 11:52
 */
@Configuration
@Slf4j
public class RabbitConfig {

    public static final String EXCHANGE_NAME = "icibei-exchange";
    public static final String ROUTING_KEY = "icibei-gateway-route";

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    Queue queue() {
        String queueName = new Base64UrlNamingStrategy(appName + ".").generateName();
        log.info("{} queue :{}", appName, queueName);
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        log.info("{} exchange:{}", appName, EXCHANGE_NAME);
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        log.info("binding {} to {} with {}", queue, exchange, ROUTING_KEY);
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    MessageListenerAdapter messageListenerAdapter(
            RabbitReceiver rabbitReceiver,
            MessageConverter messageConverter
    ) {
        log.info("new listener");
        return new MessageListenerAdapter(rabbitReceiver, messageConverter);
    }

    @Bean
    SimpleMessageListenerContainer simpleMessageListenerContainer(
            ConnectionFactory connectionFactory,
            MessageListenerAdapter messageListenerAdapter,
            Queue queue
    ) {
        log.info("init simpleMessageListenerContainer 监听: {}", queue.getName());
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(queue.getName());
        container.setMessageListener(messageListenerAdapter);
        return container;
    }

    /**
     * 定义消息转换器直接发送对象
     */
    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter(objectMapper));
    }

}
