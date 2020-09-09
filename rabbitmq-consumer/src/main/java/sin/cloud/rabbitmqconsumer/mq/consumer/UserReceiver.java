package sin.cloud.rabbitmqconsumer.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import sin.cloud.rabbitmqconsumer.mq.constant.DesConst;
import sin.cloud.rabbitmqconsumer.mq.dto.UserSendDTO;
import sin.cloud.rabbitmqconsumer.mq.entity.User;
import sin.cloud.rabbitmqconsumer.mq.mapper.UserMapper;
import sin.cloud.rabbitmqconsumer.mq.utils.DESUtil;
import sin.cloud.rabbitmqconsumer.rabbitconst.RabbitConst;

import static sin.cloud.rabbitmqconsumer.mq.constant.MQConst.*;

@Component
public class UserReceiver {

    @Autowired
    private UserMapper userMapper;

    @RabbitListener(bindings = @QueueBinding (
            value = @Queue (value = DIRECT_QUEUE, durable = "true"),
            exchange = @Exchange (value = DIRECT_EXCHANGE, ignoreDeclarationExceptions = "true"),
            key = DIRECT_ROUTINGKEY
    ))
    @RabbitHandler
    public void receiveDirectMessage( Message message, Channel channel ) throws Exception {
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        // 获取消息内容
        String mes = message.getPayload().toString();
        // 解密
        String conent = DESUtil.decrypt(mes, DesConst.DES_KEY);
        UserSendDTO userSendDTO = JSON.parseObject(conent,new TypeReference<UserSendDTO>(){});
        String messgeId = userSendDTO.getMessageId();
        String producer = userSendDTO.getProducer();
        String andOrUpdate = userSendDTO.getAddOrUpdate();
        String messageType = userSendDTO.getMessageType();
        User user = userSendDTO.getUserData();
        try{
            if(RabbitConst.SEND_ENTITY.equals(messageType)){
                // 新增用户
                if(RabbitConst.ADD_USER.equals(andOrUpdate)){
                    insert(user);
                }
                // 更新用户
                else if (RabbitConst.UPDATE_USER.equals(andOrUpdate)){
                    update(user);
                }
                // 删除用户
                else if (RabbitConst.DELETE_USER.equals(andOrUpdate)){
                    delete(user);
                }
            }
            if(RabbitConst.SEND_DIRECTION.equals(messageType)){
                // 发送指令，执行sql语句
            }
            channel.basicAck(deliveryTag, false);
        }catch(Exception e){
            System.out.println("由"+producer+"发送的消息:"+messgeId+"消费失败");
            channel.basicReject(deliveryTag, false);
        }
    }

    public void insert(User object) {
        if (object != null) {
            userMapper.insert(object);
        }
    }

    /**
     * qcData修改
     *
     * @param object data数据源
     */
    public void update(User object) {
        if (object != null) {
            userMapper.updateById(object);
        }
    }

    /**
     * 删除
     *
     * @param object data数据源
     */
    public void delete(User object) {
        if (object != null) {
            object.setIsDeleted(1);
            userMapper.updateById(object);
        }
    }

}
