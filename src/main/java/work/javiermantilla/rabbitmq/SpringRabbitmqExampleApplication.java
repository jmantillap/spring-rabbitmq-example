package work.javiermantilla.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringRabbitmqExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRabbitmqExampleApplication.class, args);
	}

}
