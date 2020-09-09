package sin.cloud.rabbitmqproducer.mq.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DirectionSendDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *需要传输的操作指令
     */
    private String direction;

    /**
     * 操作类型
     * 1：新增
     * 2：修改
     */
    private String addOrUpdate;

    /**
     * 消息类型
     * 1：传输指令
     * 2：传输数据实体
     */
    private String messageType;

    /**
     *  生产者
     */
    private String  producer;

    /**
     *  消息id
     */
    private String  messageId;
}
