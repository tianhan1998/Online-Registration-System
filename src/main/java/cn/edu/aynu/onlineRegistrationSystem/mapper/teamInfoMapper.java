package cn.edu.aynu.onlineRegistrationSystem.mapper;

import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface teamInfoMapper {
    long countByExample(teamInfoExample example);

    int deleteByExample(teamInfoExample example);

    int deleteByPrimaryKey(Integer teamId);

    int insert(teamInfo record);

    int insertSelective(teamInfo record);

    List<teamInfo> selectByExample(teamInfoExample example);

    teamInfo selectByPrimaryKey(Integer teamId);

    teamInfo selectByTeamAccount(String team_account);

    List<teamInfo> getTeamList();

    int updateByExampleSelective(@Param("record") teamInfo record, @Param("example") teamInfoExample example);

    int updateByExample(@Param("record") teamInfo record, @Param("example") teamInfoExample example);

    int updateByPrimaryKeySelective(teamInfo record);

    int updateByPrimaryKey(teamInfo record);

    int joinTeam(@Param("team") teamInfo team,@Param("memId") Integer memId);

    List<teamInfo> getTeamInfoByMemId(Integer memId);

}