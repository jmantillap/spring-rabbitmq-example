package work.javiermantilla.rabbitmq.producer;


import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
//import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import work.javiermantilla.rabbitmq.config.MessagingConfig;
import work.javiermantilla.rabbitmq.dto.MessageDTO;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProducerMessage {
	private final RabbitTemplate rabbitTemplate;	
	
	/*
	@PostConstruct
    public void startSendingMessages() {
        Flux.interval(Duration.ofSeconds(10))
            .flatMap(tick -> sendMessage())
            .subscribe();
    }	
	*/
	@Scheduled(fixedDelay = 10000L)
	private Mono<Void> sendMessage() {
		
		return Mono.fromRunnable(()->{
			MessageDTO message= new MessageDTO(UUID.randomUUID().toString()
					,"Hola desde: "+LocalDateTime.now().toString() );
			
			log.info("Sending message id: ... {}",message.getId() );
			rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, message);
		}).then();		
		
	}
	
	
	@Scheduled(fixedDelay = 10000L)
	private Mono<Void> sendMessageOther() {
		
		return Mono.fromRunnable(()->{
			MessageDTO message= new MessageDTO(UUID.randomUUID().toString()
					,"Cola other Hola desde: "+LocalDateTime.now().toString() );
			
			log.info("Sending message ... {}",message.getId() );
			rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE_OTHER, MessagingConfig.ROUTING_KEY_OTHER, message);
		}).then();		
		
	}	
	
	
}
