package sin.cloud.rabbitmqproducer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sin.cloud.rabbitmqproducer.dao.IUserDao;
import sin.cloud.rabbitmqproducer.pojo.User;

import java.util.List;

/**
 * UserDao实现类
 */
@Repository
public class UserDaoImpl implements IUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Override
    public int add(User user) {
        return jdbcTemplate.update("insert into user(name, age, sex, height, memo) values(?, ?, ?, ?, ?)",
                user.getName(),user.getAge(),user.getSex(),user.getHeight(),user.getMemo());
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @Override
    public int update(User user) {
        return jdbcTemplate.update("UPDATE  user SET NAME=? ,age=?, sex=?, height=?, memo=? WHERE id=?",
                user.getName(),user.getAge(),user.getSex(),user.getHeight(),user.getMemo(),user.getId());
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public int delete(long id) {
        return jdbcTemplate.update("DELETE from user where id=?",id);
    }

    /**
     * 根据用户Id查询数据
     * @param id
     * @return
     */
    @Override
    public User findUserById( long id) {
        List<User> list = jdbcTemplate.query("select * from user where id = ?", new Object[]{id}, new BeanPropertyRowMapper(User.class));
        if(list!=null && list.size()>0){
            User user = list.get(0);
            return user;
        }else{
            return null;
        }
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> findUserList() {
        List<User> list = jdbcTemplate.query("select * from user", new Object[]{}, new BeanPropertyRowMapper(User.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }

}