package sin.cloud.rabbitmqproducer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sin.cloud.rabbitmqproducer.pojo.User;

import java.util.List;

public interface IUserService extends IService<User> {

    boolean save ( User user );

    void deleteUser( Long id);

    User getUserById(Long id);

    List<User> listUsers();

    User update(User user);

}
