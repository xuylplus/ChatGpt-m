package xyl.me.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyl.me.domain.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
