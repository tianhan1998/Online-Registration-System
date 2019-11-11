package cn.edu.aynu.onlineRegistrationSystem.mapper;

import cn.edu.aynu.onlineRegistrationSystem.entity.scoreInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.scoreInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface scoreInfoMapper {
    long countByExample(scoreInfoExample example);

    int deleteByExample(scoreInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(scoreInfo record);

    int insertSelective(scoreInfo record);

    List<scoreInfo> selectByExample(scoreInfoExample example);

    scoreInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") scoreInfo record, @Param("example") scoreInfoExample example);

    int updateByExample(@Param("record") scoreInfo record, @Param("example") scoreInfoExample example);

    int updateByPrimaryKeySelective(scoreInfo record);

    int updateByPrimaryKey(scoreInfo record);
}