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
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
public class MessagingConfig {
	public static final String QUEUE = "demo_queue";	
	public static final String EXCHANGE = "demo_exchange";	
	public static final String ROUTING_KEY = "demo_routingKey";
	
	public static final String QUEUE_1 = "demo_queue1";
	public static final String EXCHANGE_1 = "demo_exchange1";	
	public static final String ROUTING_KEY_1 = "demo_routingKey1";
	

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
	TopicExchange appExchange1() {
        return new TopicExchange(EXCHANGE_1);
    }
    
    @Bean
    Queue appQueueSpecific1() {
        return new Queue(QUEUE_1);
    }
   
        
    @Bean
    Binding declareBindingSpecific1() {
        return BindingBuilder.bind(appQueueSpecific1()).to(appExchange1()).with(ROUTING_KEY_1);
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

}
