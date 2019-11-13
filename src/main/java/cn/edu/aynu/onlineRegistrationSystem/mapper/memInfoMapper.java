package cn.edu.aynu.onlineRegistrationSystem.mapper;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.memInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface memInfoMapper {
    long countByExample(memInfoExample example);

    int deleteByExample(memInfoExample example);

    int deleteByPrimaryKey(Integer memId);

    int insert(memInfo record);

    int insertSelective(memInfo record);

    List<memInfo> selectByExample(memInfoExample example);

    memInfo selectByPrimaryKey(Integer memId);

    int updateByExampleSelective(@Param("record") memInfo record, @Param("example") memInfoExample example);

    int updateByExample(@Param("record") memInfo record, @Param("example") memInfoExample example);

    int updateByPrimaryKeySelective(memInfo record);

    int updateByPrimaryKey(memInfo record);

    List<memInfo> getMemInfoByIds(@Param("ids") List<Integer> ids);

    List<memInfo>getMemInfoLists();
}