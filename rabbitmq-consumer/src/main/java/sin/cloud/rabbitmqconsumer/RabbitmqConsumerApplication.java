package sin.cloud.rabbitmqconsumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan ("sin.cloud.rabbitmqconsumer.mq.mapper")
public class RabbitmqConsumerApplication {
    public static void main ( String[] args ) {
        SpringApplication.run(RabbitmqConsumerApplication.class , args);
    }

}
