package org.ct.seckill.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author K
 * @Date 2020/2/3 13:15
 * @Version 1.0
 * rabbitMq配置
 */
@Configuration
public class MQConfig {
    public static final String SEC_KILL_MESSAGE_QUEUE = "sec_kill_message_queue";
    public static final String TOPIC_QUEUE1 = "topic_queue1";
    public static final String TOPIC_QUEUE2 = "topic_queue2";
    public static final String HEADER_QUEUE = "header_queue2";
    public static final String TOPIC_EXCHANGE = "topic_exchange";
    public static final String FANOUT_EXCHANGE = "fanout_exchange";
    public static final String HEADER_EXCHANGE = "header_exchange";

    /**
     * Direct 交换机
     * */
    @Bean
    public Queue queue() {
        return new Queue(SEC_KILL_MESSAGE_QUEUE, true);
    }

//    /**
//     * Topic 交换机
//     * */
//    @Bean
//    public Queue Topic_queue1() {
//        return new Queue(TOPIC_QUEUE1, true);
//    }
//
//    /**
//     * Topic 交换机
//     * */
//    @Bean
//    public Queue Topic_queue2() {
//        return new Queue(TOPIC_QUEUE2, true);
//    }
//
//    /**
//     * Header 交换机
//     * */
//    @Bean
//    public Queue Header_queue() {
//        return new Queue(HEADER_QUEUE, true);
//    }
//
//    /**
//     * TopicExchange
//     */
//    @Bean
//    public TopicExchange topicExchange() {
//        return new TopicExchange(TOPIC_EXCHANGE);
//    }
//
//    /**
//     * FanoutExchange 广播模式
//     */
//    @Bean
//    public FanoutExchange fanoutExchange() {
//        return new FanoutExchange(FANOUT_EXCHANGE);
//    }
//
//    /**
//     * HeaderExchange 模式
//     */
//    @Bean
//    public HeadersExchange headersExchange() {
//        return new HeadersExchange(HEADER_EXCHANGE);
//    }
//
//    /**
//     * Binding
//     */
//    @Bean
//    public Binding topicBinding1() {
//        return BindingBuilder.bind(Topic_queue1()).to(topicExchange()).with("topic.key1");
//    }
//    @Bean
//    public Binding topicBinding2() {
//        return BindingBuilder.bind(Topic_queue2()).to(topicExchange()).with("topic.#");
//    }
//    @Bean
//    public Binding fanoutBinding1() {
//        return BindingBuilder.bind(Topic_queue1()).to(fanoutExchange());
//    }
//    @Bean
//    public Binding fanoutBinding2() {
//        return BindingBuilder.bind(Topic_queue2()).to(fanoutExchange());
//    }
//    @Bean
//    public Binding headerBinding() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("header1", "value1");
//        map.put("header2", "value2");
//        return BindingBuilder.bind(Header_queue()).to(headersExchange()).whereAll(map).match();
//    }

}
