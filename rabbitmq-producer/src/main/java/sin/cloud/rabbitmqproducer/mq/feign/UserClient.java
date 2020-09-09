package sin.cloud.rabbitmqproducer.mq.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sin.cloud.rabbitmqproducer.mq.service.IUserSendService;

@RestController
public class UserClient implements IUserClient {

    /**
     * 用户表Service
     */
    @Autowired
    IUserSendService userSendService;


    /**
     * 发送User MQ 信息
     * @param user 更改的信息，由前端输入
     * @param id 消息id
     * @param type 操作类型
     * @param messageType 消息类型
     * @return
     */
    @Override
    public boolean UserSend ( @RequestBody Object user , @RequestParam String id , @RequestParam String type , @RequestParam String messageType) {
        return userSendService.userSend(user,id,type,messageType);
    }

    /**
     * 发送指令到消费端，消费端根据指令自行操作
     * @param direction 指令（sql语句）
     * @param id 消息id
     * @param type 操作类型
     * @param messageType 消息类型
     * @return
     */
    @Override
    public boolean UserSend (@RequestParam String direction ,@RequestParam String id ,@RequestParam String type ,@RequestParam String messageType ) {
        return userSendService.userSend(direction,id,type,messageType);
    }
}
