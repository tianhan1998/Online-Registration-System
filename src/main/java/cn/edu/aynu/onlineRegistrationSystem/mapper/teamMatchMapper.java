package cn.edu.aynu.onlineRegistrationSystem.mapper;

import cn.edu.aynu.onlineRegistrationSystem.entity.teamMatch;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamMatchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

    int checkExistInTeamMatch(Integer team_id,Integer match_id);
}