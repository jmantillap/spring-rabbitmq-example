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
	public static final String QUEUE_ONE = "demo_queue_one";
	public static final String EXCHANGE = "demo_exchange";	
	public static final String ROUTING_KEY = "demo_routingKey";
	
	
	public static final String QUEUE_OTHER = "demo_queue_other";
	public static final String EXCHANGE_OTHER = "demo_exchange_other";	
	public static final String ROUTING_KEY_OTHER = "demo_routingKey_other";
	

	@Bean
	TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE);
    }
   
    @Bean
    Queue appQueueSpecific() {
        return new Queue(QUEUE);
    }
    
    @Bean
    Queue appQueueSpecific1() {
        return new Queue(QUEUE_ONE);
    }
    
    @Bean
    Binding declareBindingSpecific1() {
        return BindingBuilder.bind(appQueueSpecific()).to(appExchange()).with(ROUTING_KEY);
    }
    
    @Bean
    Binding declareBindingSpecific2() {
        return BindingBuilder.bind(appQueueSpecific1()).to(appExchange()).with(ROUTING_KEY);
    }
    
    
    @Bean
	TopicExchange appExchangeOther() {
        return new TopicExchange(EXCHANGE_OTHER);
    }
    
    @Bean
    Queue appQueueSpecificOther() {
        return new Queue(QUEUE_OTHER);
    }
        
    @Bean
    Binding declareBindingSpecificOther() {
        return BindingBuilder.bind(appQueueSpecificOther()).to(appExchangeOther()).with(ROUTING_KEY_OTHER);
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
