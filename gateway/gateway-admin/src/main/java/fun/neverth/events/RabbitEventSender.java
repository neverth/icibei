package fun.neverth.events;

import fun.neverth.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/12 11:41
 */
@Component
@Slf4j
public class RabbitEventSender {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    private MessageConverter messageConverter;

    @PostConstruct
    public void init() {
        rabbitTemplate.setMessageConverter(messageConverter);
    }

    public void send(String routingKey, Object object) {
        log.info("routingKey:{} => message:{}", routingKey, object);
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, routingKey, object);
    }

}
