package sin.cloud.rabbitmqconsumer.mq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import sin.cloud.rabbitmqconsumer.mq.entity.User;

@Repository
public interface UserMapper extends BaseMapper<User> {


}
