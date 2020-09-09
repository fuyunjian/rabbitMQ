package sin.cloud.rabbitmqproducer.dao;

import sin.cloud.rabbitmqproducer.pojo.User;

import java.util.List;

/**
 * user资源库
 * @author: crj
 * @date: 2018年8月21日 上午10:48:16
 */
public interface IUserDao {

    int add( User user);

    int update(User user);

    int delete(long id);

    User findUserById(long id);

    List<User> findUserList();


}