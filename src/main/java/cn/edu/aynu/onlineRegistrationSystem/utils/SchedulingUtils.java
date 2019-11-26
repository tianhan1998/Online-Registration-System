package cn.edu.aynu.onlineRegistrationSystem.utils;

import cn.edu.aynu.onlineRegistrationSystem.entity.matchInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.matchInfoExample;
import cn.edu.aynu.onlineRegistrationSystem.mapper.matchInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class SchedulingUtils {//TODO 检查比赛时间完成，每分钟检查一次，检查后保存至内存，除非添加新比赛，否则不会重新查询数据库
    public static LinkedList<matchInfo> list=null;
    @Autowired
    matchInfoMapper matchInfoMapper;
    @Scheduled(cron = "0 */1 * * * ?")//每一分钟查一次
    public void checkMatchStatus(){
        if(list!=null&&list.size()>0){
            setMatchStatus();
        }else{
            matchInfoExample example=new matchInfoExample();
            example.setDistinct(false);
            example.setOrderByClause("match_star_time asc");
            matchInfoExample.Criteria criteria=example.createCriteria();
            criteria.andMatchStatusEqualTo(0);
            list= new LinkedList<>(matchInfoMapper.selectByExample(example));
            if(list.size()>0) {
                setMatchStatus();
            }
        }
    }
    public void setMatchStatus(){
        long currentMills=System.currentTimeMillis();
        System.out.println("执行比赛检查，当前时间戳"+currentMills);
        matchInfo first=list.peekFirst();
        long matchMills=first.getMatchStarTime().getTime();
        while(list.size()>0&&matchMills<currentMills){
            System.out.println(first.getMatchId()+"比赛开始时间"+matchMills);
            first.setMatchStatus(1);
            matchInfoMapper.updateByPrimaryKeySelective(first);
            list.pollFirst();
            first=list.peekFirst();
            if(first==null)
                break;
            else {
                matchMills = first.getMatchStarTime().getTime();
            }
        }

    }
}
