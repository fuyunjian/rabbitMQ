package sin.cloud.rabbitmqproducer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 用户id
     */
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
