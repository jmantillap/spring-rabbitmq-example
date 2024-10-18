package work.javiermantilla.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import work.javiermantilla.rabbitmq.config.MessagingConfig;
import work.javiermantilla.rabbitmq.dto.MessageDTO;

@Service
@Log4j2
public class CustomMessageListener {
	
	@RabbitListener(queues = MessagingConfig.QUEUE)
    public void receiveMessage(final MessageDTO customMessage) {
        log.info("Received message and deserialized to 'CustomMessage': {}", customMessage.toString());
    }
	
}
