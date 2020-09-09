package sin.cloud.rabbitmqproducer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sin.cloud.rabbitmqproducer.mapper.UserMappers;
import sin.cloud.rabbitmqproducer.mq.feign.IUserClient;
import sin.cloud.rabbitmqproducer.pojo.User;
import sin.cloud.rabbitmqproducer.service.IUserService;

import java.util.List;

import static sin.cloud.rabbitmqproducer.constantproducer.ProducerConstant.*;

@Service
public class UserServiceImpl extends ServiceImpl<UserMappers,User> implements IUserService {

    /**
     * 定义UserMapper接口
     */

    @Autowired
    IUserClient userClient;

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Override
    public boolean save ( User user ) {
//        userDao.add(user);
        Integer statu = baseMapper.insert(user);
        userClient.UserSend(user,EMPTY_STRING ,ADD_USER,SEND_ENTITY);
        return statu > 0 ? true : false;
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @Override
    public User update ( User user ) {
//      userDao.update(user);
        baseMapper.update(user,null);
        userClient.UserSend(user,EMPTY_STRING ,UPDATE_USER,SEND_ENTITY);
        return user;
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public void deleteUser ( Long id ) {
        baseMapper.deleteByUserId(id);
        String sql = "UPDATE USER SET is_deleted = 1 WHERE id = " + id;
        userClient.UserSend(sql,EMPTY_STRING ,UPDATE_USER,SEND_ENTITY);
//      userDao.delete(id);
    }


    /**
     * 通过用户Id查询用户
     * @param id
     * @return
     */
    @Override
    public User getUserById ( Long id ) {
        return baseMapper.selectById(id);
//      return userDao.findUserById(id);
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> listUsers () {
        return baseMapper.selectList(null);
//      return userDao.findUserList();
    }

}
