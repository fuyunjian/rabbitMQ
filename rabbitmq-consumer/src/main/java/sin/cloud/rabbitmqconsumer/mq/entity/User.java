package sin.cloud.rabbitmqconsumer.mq.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {

    /**
     * 用户id
     */
    @TableId(value = "id")
    Integer id;
    /**
     * 姓名
     */
    String name;
    /**
     * 年龄
     */
    Integer age;
    /**
     * 性别
     */
    String sex;
    /**
     * 身高
     */
    String height;
    /**
     * 简介
     */
    String memo;
    /**
     * 是否删除
     */
    Integer isDeleted;

}
