package config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Config {
    /**
     * 普通队列
     */
    @Bean
    public Queue commonQueue() {
        return new Queue("commonQueue");
    }

    @Bean
    public TopicExchange commonExchange() {
        return new TopicExchange("commonExchange");
    }

    @Bean
    public Binding commonBinding() {
        return BindingBuilder.bind(commonQueue()).to(commonExchange()).with("commonQueue");
    }

    /**
     * 延时队列
     */
    @Bean
    public Queue delayQueue() {
        return new Queue("delayQueue");
    }

    @Bean
    public DirectExchange delayExchange() {
        DirectExchange exchange = new DirectExchange("delayExchange");
        exchange.setDelayed(true);
        return exchange;
    }

    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with("delayQueue");
    }

    /**
     * 死信队列相关
     */
    /**
     * 普通队列：
     * 满足三个条件进入死信：1.超时未消费；2.拒绝；3.队列已满。
     */
    @Bean
    public Queue deadLetterCommonQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "deadLetterExchange");
        args.put("x-dead-letter-routing-key", "deadLetterQueue");
        return new Queue("deadLetterCommonQueue", true, false, false, args);
    }

    @Bean
    public TopicExchange deadLetterCommonExchange() {
        return new TopicExchange("deadLetterCommonExchange");
    }

    @Bean
    public Binding deadLetterCommonBinding() {
        return BindingBuilder.bind(deadLetterCommonQueue()).to(deadLetterCommonExchange()).with("deadLetterCommonQueue");
    }

    /**
     * 死信队列
     */
    @Bean
    public Queue deadLetterQueue() {
        return new Queue("deadLetterQueue");
    }

    @Bean
    public TopicExchange deadLetterExchange() {
        return new TopicExchange("deadLetterExchange");
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with("deadLetterQueue");
    }


}
