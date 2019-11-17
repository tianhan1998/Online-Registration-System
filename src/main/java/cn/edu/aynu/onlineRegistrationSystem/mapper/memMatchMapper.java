package cn.edu.aynu.onlineRegistrationSystem.mapper;

import cn.edu.aynu.onlineRegistrationSystem.entity.matchInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.memMatch;
import cn.edu.aynu.onlineRegistrationSystem.entity.memMatchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface memMatchMapper {
    long countByExample(memMatchExample example);

    int deleteByExample(memMatchExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(memMatch record);

    int insertSelective(memMatch record);

    List<memMatch> selectByExample(memMatchExample example);

    memMatch selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") memMatch record, @Param("example") memMatchExample example);

    int updateByExample(@Param("record") memMatch record, @Param("example") memMatchExample example);

    int updateByPrimaryKeySelective(memMatch record);

    int updateByPrimaryKey(memMatch record);

    int checkExistInMemMatch(@Param("mem_id") Integer mem_id,@Param("match_id") Integer match_id);

    List<Integer> getJoinedMemByMatchId(Integer id);
}