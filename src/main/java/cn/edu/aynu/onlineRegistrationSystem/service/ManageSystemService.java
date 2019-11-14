package cn.edu.aynu.onlineRegistrationSystem.service;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.mapper.memInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/11/13 0013
 */
@Service
public class ManageSystemService {
    @Autowired
    memInfoMapper memInfoMapper;
    /**
     * 获取所有meminfo
     * @return
     */
    public List<memInfo> getMemInfoLists(){
        return memInfoMapper.getMemInfoLists();
    }

    /**
     * 修改用户，除了学号
     * @param user
     * @return
     */
    public int updateMem(memInfo user){
        return memInfoMapper.updateByPrimaryKey(user);
    }

    /**
     * 通过学号删除用户
     * @param id
     * @return
     */
    public int deleteMem(Integer id){
        return memInfoMapper.deleteByPrimaryKey(id);
    }
}
