package work.javiermantilla.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDTO {
	private String id;
	private String message;
}
