package cn.edu.aynu.onlineRegistrationSystem.mapper;

import cn.edu.aynu.onlineRegistrationSystem.entity.matchInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.matchInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface matchInfoMapper {

    long countByExample(matchInfoExample example);

    int deleteByExample(matchInfoExample example);

    int deleteByPrimaryKey(Integer matchId);

    int insert(matchInfo record);

    int insertSelective(matchInfo record);

    List<matchInfo> selectByExample(matchInfoExample example);

    matchInfo selectByPrimaryKey(Integer matchId);

    int updateByExampleSelective(@Param("record") matchInfo record, @Param("example") matchInfoExample example);

    int updateByExample(@Param("record") matchInfo record, @Param("example") matchInfoExample example);

    int updateByPrimaryKeySelective(matchInfo record);

    int updateByPrimaryKey(matchInfo record);
}