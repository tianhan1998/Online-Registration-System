package cn.edu.aynu.onlineRegistrationSystem.service;

import cn.edu.aynu.onlineRegistrationSystem.entity.User;
import cn.edu.aynu.onlineRegistrationSystem.mapper.SignUpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    @Autowired
    public SignUpMapper mapper;

    public boolean signUp(User user){
        return mapper.signUp(user);
    }
    public User checkId(Integer id){
        return mapper.checkId(id);
    }
}
