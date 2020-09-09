package sin.cloud.rabbitmqproducer.mq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sin.cloud.rabbitmqproducer.mq.entity.User;

public interface IUserSendService extends IService<User> {

    /**
     * user mq 发送
     * @param userSend
     * @param id
     * @param type
     * @return
     */
    boolean userSend(Object userSend,String id,String type,String messageType);

    /**
     * 发送指令到消费段做更新
     * @param direction
     * @param id
     * @param type
     * @param messageType
     * @return
     */
    boolean userSend(String direction,String id,String type,String messageType);

}
