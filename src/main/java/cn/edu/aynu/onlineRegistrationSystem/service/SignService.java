package cn.edu.aynu.onlineRegistrationSystem.service;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.memInfoExample;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfoExample;
import cn.edu.aynu.onlineRegistrationSystem.mapper.memInfoMapper;
import cn.edu.aynu.onlineRegistrationSystem.mapper.teamInfoMapper;
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
    @Autowired
    teamInfoMapper teamInfoMapper;
    @Autowired
    memInfoMapper memInfoMapper;

    public List<memInfo> signInMem(Integer id, String password){
        memInfoExample example=new memInfoExample();
        example.setOrderByClause("mem_id desc");
        example.setDistinct(false);
        memInfoExample.Criteria criteria=example.createCriteria();
        criteria.andMemIdEqualTo(id);
        criteria.andMemPasswordEqualTo(password);
        return mapper.selectByExample(example);
    }

    public List<teamInfo> signInTeam(String team_account,String team_password){
        teamInfoExample example=new teamInfoExample();
        example.setDistinct(false);
        example.setOrderByClause("team_id desc");
        teamInfoExample.Criteria criteria=example.createCriteria();
        criteria.andTeamAccountEqualTo(team_account);
        criteria.andTeamPasswordEqualTo(team_password);
        List<teamInfo> lists=teamInfoMapper.selectByExample(example);
        return lists;
    }

    public memInfo checkId(Integer id){
        return mapper.selectByPrimaryKey(id);
    }
    public teamInfo checkTeamAccount(String team_account){
        return teamInfoMapper.selectByTeamAccount(team_account);
    }
    public Boolean signUp(memInfo memInfo) {
        if(mapper.insert(memInfo)>0){
            return true;
        }else{
            return false;
        }
    }
    public Boolean signUpTeam(teamInfo team){
        if(teamInfoMapper.insert(team)>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 查找个人email是否重复
     * @param email
     * @return
     */
    public int checkMemEmailExist(String email){
        return memInfoMapper.checkEmailExist(email);
    }

    /**
     * 查看团队email是否重复
     * @param email
     * @return
     */
    public int checkTeamEmailExist(String email){
        return teamInfoMapper.checkEmailExist(email);
    }
}
