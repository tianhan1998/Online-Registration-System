package cn.edu.aynu.onlineRegistrationSystem.service;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.memInfoExample;
import cn.edu.aynu.onlineRegistrationSystem.mapper.memInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<memInfo> findUser(Integer id){
        memInfoExample example=new memInfoExample();
        example.setOrderByClause("mem_id desc");
        example.setDistinct(false);
        memInfoExample.Criteria criteria=example.createCriteria();
        criteria.andMemIdEqualTo(id);
        return mapper.selectByExample(example);

    }
}
