package work.javiermantilla.rabbitmq.consumer;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;
import work.javiermantilla.rabbitmq.config.MessagingConfig;
import work.javiermantilla.rabbitmq.dto.MessageDTO;

@Service
@Log4j2
public class ConsumerMessageListener {
	
	
	@RabbitListener(queues = MessagingConfig.QUEUE_OTHER,ackMode = "MANUAL" )
    public Mono<Void> receiveMessage(final MessageDTO customMessage) {
		
		return Mono.fromRunnable(() -> {
			//log.info("**** ConsumerMessageListener ****  Escuchando Cola:{}",MessagingConfig.QUEUE_OTHER);
            log.info("Received queque:{}  message and deserialized to 'Message': {}",MessagingConfig.QUEUE_OTHER, 
            		customMessage.getId());           
        }).then();
    }
		
}
