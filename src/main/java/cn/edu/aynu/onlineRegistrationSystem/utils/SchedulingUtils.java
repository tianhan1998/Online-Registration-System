package cn.edu.aynu.onlineRegistrationSystem.utils;

import cn.edu.aynu.onlineRegistrationSystem.entity.matchInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.matchInfoExample;
import cn.edu.aynu.onlineRegistrationSystem.mapper.matchInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class SchedulingUtils {//TODO 检查比赛时间，每一分钟检查一次，检查后保存至内存，除非添加新比赛，否则不会重新查询数据库
    public static LinkedList<matchInfo> startList =null;
    public static LinkedList<matchInfo> endList=null;
    @Autowired
    matchInfoMapper matchInfoMapper;
    @Scheduled(cron = "0 */1 * * * ?")//每一分钟查一次
    public void checkStartMatchStatus(){
        Thread thread=Thread.currentThread();
        System.out.println("进入检查比赛开始定时任务，当前线程名="+thread.getName()+"线程id="+thread.getId());
        if(startList !=null&& startList.size()>0){
            setStartMatchStatus();
        }else{
            matchInfoExample example=new matchInfoExample();
            example.setDistinct(false);
            example.setOrderByClause("match_star_time asc");
            matchInfoExample.Criteria criteria=example.createCriteria();
            criteria.andMatchStatusEqualTo(0);
            startList = new LinkedList<>(matchInfoMapper.selectByExample(example));
            if(startList.size()>0) {
                setStartMatchStatus();
            }
        }
    }
    public void setStartMatchStatus(){
        long currentMills=System.currentTimeMillis();
        int flag=0;//检查是否有更改
        matchInfo first= startList.peekFirst();
        long matchMills=first.getMatchStarTime().getTime();
        while(startList.size()>0&&matchMills<currentMills){
            System.out.println("比赛开始定时器设置函数————比赛名称="+first.getMatchTitle()+"比赛id="+first.getMatchId()+"比赛开始时间"+matchMills+"当前时间戳"+currentMills);
            first.setMatchStatus(1);
            matchInfoMapper.updateByPrimaryKeySelective(first);
            if(flag==0) {
                System.out.println(first.getMatchTitle()+"比赛数据库设置状态完毕");
                flag = 1;
            }
            startList.pollFirst();
            first= startList.peekFirst();
            if(first==null)
                break;
            else {
                matchMills = first.getMatchStarTime().getTime();
            }
        }
        if(flag==1){
            endList=null;//有新的比赛开始了，结束比赛检查内存要清空
            System.out.println("清除定时器比赛结束链表");
        }
    }
    @Scheduled(cron = "0 */1 * * * ?")//每一分钟查一次
    public void checkEndMatchStatus(){
        Thread thread=Thread.currentThread();
        System.out.println("进入检查比赛结束定时任务，当前线程名="+thread.getName()+"线程id="+thread.getId());
        if(endList !=null&& endList.size()>0){
            setEndMatchStatus();
        }else{
            matchInfoExample example=new matchInfoExample();
            example.setDistinct(false);
            example.setOrderByClause("match_end_time asc");
            matchInfoExample.Criteria criteria=example.createCriteria();
            criteria.andMatchStatusEqualTo(1);
            endList = new LinkedList<>(matchInfoMapper.selectByExample(example));
            if(endList.size()>0) {
                setEndMatchStatus();
            }
        }
    }
    public void setEndMatchStatus(){
        long currentMills=System.currentTimeMillis();
        matchInfo first= endList.peekFirst();
        long matchMills=first.getMatchEndTime().getTime();
        while(endList.size()>0&&matchMills<currentMills){
            System.out.println("比赛结束定时器设置函数————比赛名称="+first.getMatchTitle()+"比赛id="+first.getMatchId()+"比赛开始时间"+matchMills+"当前时间戳"+currentMills);
            first.setMatchStatus(2);
            matchInfoMapper.updateByPrimaryKeySelective(first);
            System.out.println(first.getMatchTitle()+"比赛已结束，数据库状态设置完毕");
            endList.pollFirst();
            first= endList.peekFirst();
            if(first==null)
                break;
            else {
                matchMills = first.getMatchEndTime().getTime();
            }
        }
    }
}
