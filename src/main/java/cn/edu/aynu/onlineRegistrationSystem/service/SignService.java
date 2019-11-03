package cn.edu.aynu.onlineRegistrationSystem.service;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.memInfoExample;
import cn.edu.aynu.onlineRegistrationSystem.mapper.memInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/11/3 0003
 */
@Service
public class SignService {
    @Autowired
    memInfoMapper mapper;

    public List<memInfo> signIn(Integer id,String password){
        memInfoExample example=new memInfoExample();
        example.setOrderByClause("mem_id desc");
        example.setDistinct(false);
        memInfoExample.Criteria criteria=example.createCriteria();
        criteria.andMemIdEqualTo(id);
        criteria.andMemPasswordEqualTo(password);
        return mapper.selectByExample(example);
    }
    public Boolean singUp(memInfo memInfo) {
        if(mapper.insert(memInfo)>0){
            return true;
        }else{
            return false;
        }
    }

}
