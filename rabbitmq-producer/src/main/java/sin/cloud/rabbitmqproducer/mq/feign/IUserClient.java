package sin.cloud.rabbitmqproducer.mq.feign;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 模拟feign
 */
public interface IUserClient {

    /**
     * 用户表mq消息发送
     * @param user
     * @param id
     * @param type
     * @return
     */
    boolean UserSend( @RequestBody Object user, @RequestParam String id, @RequestParam String type, @RequestParam String messageType);


    /**
     * 发送指令
     * @param direction
     * @param id
     * @param type
     * @param messageType
     * @return
     */
    boolean UserSend( @RequestParam String direction,@RequestParam String id, @RequestParam String type, @RequestParam String messageType);

}
