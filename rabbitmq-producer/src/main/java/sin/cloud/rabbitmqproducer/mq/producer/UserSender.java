package sin.cloud.rabbitmqproducer.mq.producer;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sin.cloud.rabbitmqproducer.mq.dto.DirectionSendDTO;
import sin.cloud.rabbitmqproducer.mq.dto.UserSendDTO;
import sin.cloud.rabbitmqproducer.mq.utils.DESUtil;

import static sin.cloud.rabbitmqproducer.mq.constant.DesConst.DES_KEY;
import static sin.cloud.rabbitmqproducer.mq.constant.MQConst.*;

@RestController
public class UserSender {

    //使用RabbitTemplate,这提供了接收/发送等等方法
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendDirectMessage( UserSendDTO userSendDTO ) {
        // 将消息转化为json
        String json = JSON.toJSONString(userSendDTO);
        // 对json进行数据加密
        String jsonStr = DESUtil.encrypt(json,DES_KEY);
        // 传输jsonStr
        // 将消息携带绑定键值：通过路由键 DIRECT_ROUTINGKEY 发送到交换机 DIRECT_EXCHANGE 上
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE, DIRECT_ROUTINGKEY, jsonStr);
    }

    public void sendDirection ( DirectionSendDTO directionSendDTO ) {
        // 将消息转化为json
        String json = JSON.toJSONString(directionSendDTO);
        // 对json进行数据加密
        String jsonStr = DESUtil.encrypt(json,DES_KEY);
        // 将消息携带绑定键值：通过路由键 DIRECT_ROUTINGKEY 发送到交换机 DIRECT_EXCHANGE 上
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE, DIRECT_ROUTINGKEY, jsonStr);
    }
}
