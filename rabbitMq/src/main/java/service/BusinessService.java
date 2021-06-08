package service;

import com.alibaba.fastjson.JSONObject;
import dao.MsgBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    private static final Logger logger = LoggerFactory.getLogger(BusinessService.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendCommonMqMsg(MsgBody msgBody) {
        logger.info("发送sendCommonMqMsg消息 {}" + JSONObject.toJSONString(msgBody));
        amqpTemplate.convertAndSend("commonExchange", "commonQueue", JSONObject.toJSONString(msgBody));
    }

    public void sendDelayMqMsg(MsgBody msgBody) {
        logger.info("发送sendDelayMqMsg消息 {} 时间{}", JSONObject.toJSONString(msgBody), System.currentTimeMillis());
        amqpTemplate.convertAndSend("delayExchange", "delayQueue", JSONObject.toJSONString(msgBody),
                message -> {
                    message.getMessageProperties().setDelay(msgBody.getDelayTime());
                    return message;
                });
    }

}
