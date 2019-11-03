package cn.edu.aynu.onlineRegistrationSystem.mapper;

import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface teamInfoMapper {
    long countByExample(teamInfoExample example);

    int deleteByExample(teamInfoExample example);

    int deleteByPrimaryKey(Integer teamId);

    int insert(teamInfo record);

    int insertSelective(teamInfo record);

    List<teamInfo> selectByExample(teamInfoExample example);

    teamInfo selectByPrimaryKey(Integer teamId);

    int updateByExampleSelective(@Param("record") teamInfo record, @Param("example") teamInfoExample example);

    int updateByExample(@Param("record") teamInfo record, @Param("example") teamInfoExample example);

    int updateByPrimaryKeySelective(teamInfo record);

    int updateByPrimaryKey(teamInfo record);
}