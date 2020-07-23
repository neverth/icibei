package fun.neverth.icibei.sysadmin.organization.event;

import fun.neverth.icibei.sysadmin.organization.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 22:10
 */
@Component
@Slf4j
public class EventSender {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private MessageConverter messageConverter;

    @PostConstruct
    public void init() {
        rabbitTemplate.setMessageConverter(messageConverter);
    }

    public void send(String routingKey, Object object) {
        log.info("routingKey:{}=>message:{}", routingKey, object);
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, routingKey, object);
    }

}
