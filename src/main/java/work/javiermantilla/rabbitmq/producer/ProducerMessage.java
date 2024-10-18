package work.javiermantilla.rabbitmq.producer;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import work.javiermantilla.rabbitmq.config.MessagingConfig;
import work.javiermantilla.rabbitmq.dto.MessageDTO;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProducerMessage {
	private final RabbitTemplate rabbitTemplate;	
	
	@Scheduled(fixedDelay = 10000L)
	public void sendMessage() {
		MessageDTO messaje= new MessageDTO(UUID.randomUUID().toString()
				,"Hola desde: "+Thread.currentThread().getName() );
		
		log.info("Sending message... {}",messaje.getId() );
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, messaje);
	}
}
