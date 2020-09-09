package sin.cloud.rabbitmqproducer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("sin.cloud.rabbitmqproducer.mapper")
@MapperScan("sin.cloud.rabbitmqproducer.mq.mapper")
public class RabbitmqProducerApplication {

    public static void main ( String[] args ) {
        SpringApplication.run(RabbitmqProducerApplication.class , args);
    }

}
