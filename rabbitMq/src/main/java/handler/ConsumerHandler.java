package handler;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConsumerHandler {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerHandler.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 普通队列消费
     */
    @RabbitListener(queues = "commonQueue")
    public void commonHandler(String msgData, Channel channel, Message message) throws IOException {
        logger.info("消费commonQueue {}" + msgData);
        //手动ack  multiple：true(会重新发)，false(不会重新发)
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 延时队列消费
     */
    @RabbitListener(queues = "delayQueue")
    public void delayHandler(String msgData, Channel channel, Message message) throws IOException {
        logger.info("消费delayQueue {} time{}", msgData, System.currentTimeMillis());
        //手动ack  multiple：true(会重新发)，false(不会重新发)
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 死信队列消费
     */
    @RabbitListener(queues = "deadLetterCommonQueue")
    public void deadLetterCommonHandler(String msgData, Channel channel, Message message) throws IOException {
        logger.info("消费deadLetterCommonQueue {}", msgData);
        //手动拒绝非法消息 multiple：true(会重新发)，false(不会重新发)
        //应该进入到死信队列 deadLetterQueue
        channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
    }

}
