package cn.edu.aynu.onlineRegistrationSystem.mapper;

import cn.edu.aynu.onlineRegistrationSystem.entity.MatchAppleInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamMatch;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamMatchExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface teamMatchMapper {
    long countByExample(teamMatchExample example);

    int deleteByExample(teamMatchExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(teamMatch record);

    int insertSelective(teamMatch record);

    List<teamMatch> selectByExample(teamMatchExample example);

    teamMatch selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") teamMatch record, @Param("example") teamMatchExample example);

    int updateByExample(@Param("record") teamMatch record, @Param("example") teamMatchExample example);

    int updateByPrimaryKeySelective(teamMatch record);

    int updateByPrimaryKey(teamMatch record);

    int checkExistInTeamMatch(@Param("team_id") Integer team_id,@Param("match_id") Integer match_id);

    List<Integer> getJoinedTeamByMatchId(Integer id);

    List<MatchAppleInfo> getTeamMatchInfoByMatchId(Integer matchId);
}