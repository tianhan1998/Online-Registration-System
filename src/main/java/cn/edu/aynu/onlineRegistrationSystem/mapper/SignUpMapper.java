package cn.edu.aynu.onlineRegistrationSystem.mapper;

import cn.edu.aynu.onlineRegistrationSystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

//注册
@Component
public interface SignUpMapper {
    boolean signUp(User user);
    User checkId(Integer id);
}
