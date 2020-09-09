package sin.cloud.rabbitmqproducer.mq.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import sin.cloud.rabbitmqproducer.mq.dto.DirectionSendDTO;
import sin.cloud.rabbitmqproducer.mq.dto.UserSendDTO;
import sin.cloud.rabbitmqproducer.mq.entity.User;
import sin.cloud.rabbitmqproducer.mq.mapper.UserMapper;
import sin.cloud.rabbitmqproducer.mq.producer.UserSender;
import sin.cloud.rabbitmqproducer.mq.service.IUserSendService;

import java.util.UUID;

@Service
public class UserSendServiceImpl extends ServiceImpl<UserMapper,User> implements IUserSendService {

    @Autowired
    UserSender userSender;

    @Override
    public boolean userSend ( Object userSend , String id , String type ,String messageType) {
        // 默认发送成功
        boolean flag = true;
        try{
            UserSendDTO userSendDTO = new UserSendDTO();
            User userdata = null;
            if(!id.isEmpty()){
                Long userId = Long.valueOf(id);
                userdata = baseMapper.selectById(userId);
            }else {
                String data = JSON.toJSONString(userSend);
                userdata = JSON.parseObject(data,User.class);
            }
            // 设置发送数据实体
            userSendDTO.setUserData(userdata);
            // 设置发送的消息ID
            String messageId = System.currentTimeMillis()+"__"+ UUID.randomUUID().toString();
            userSendDTO.setMessageId(messageId);
            // 设置发送的操作类型
            userSendDTO.setAddOrUpdate(type);
            // 设置发送的消息类型
            userSendDTO.setMessageType(messageType);
            // 设置生产者
            userSendDTO.setProducer("producer");
            // 消息发送
            userSender.sendDirectMessage(userSendDTO);
        }catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean userSend ( String direction , String id , String type , String messageType ) {
        boolean flag = true;
        // 封装信息
        DirectionSendDTO directionSendDTO = new DirectionSendDTO();
        directionSendDTO.setDirection(direction);
        directionSendDTO.setMessageId(id);
        directionSendDTO.setAddOrUpdate(type);
        directionSendDTO.setMessageType(messageType);
        try{
            userSender.sendDirection(directionSendDTO);
        }catch (Exception e){
            flag = false;
        }
        return flag;
    }
}
