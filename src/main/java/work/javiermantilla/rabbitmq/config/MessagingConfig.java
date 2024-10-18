package work.javiermantilla.rabbitmq.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
	public static final String QUEUE = "demo_queue";
	public static final String EXCHANGE = "demo_exchange";
	public static final String ROUTING_KEY = "demo_routingKey";

	@Bean
	TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE);
    }
   
    @Bean
    Queue appQueueSpecific() {
        return new Queue(QUEUE);
    }
   
    @Bean
    Binding declareBindingSpecific() {
        return BindingBuilder.bind(appQueueSpecific()).to(appExchange()).with(ROUTING_KEY);
    }
    
    @Bean
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
//    
//    @Bean
//    FanoutExchange fanoutExchange() {
//        return new FanoutExchange("exchange-name");
//    }

}
