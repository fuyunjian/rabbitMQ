package sin.cloud.rabbitmqproducer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import sin.cloud.rabbitmqproducer.pojo.User;


@Repository
public interface UserMappers extends BaseMapper<User> {

    boolean deleteByUserId(@Param ("id") long id);


}
