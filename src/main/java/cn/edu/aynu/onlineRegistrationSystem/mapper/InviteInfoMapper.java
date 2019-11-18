package cn.edu.aynu.onlineRegistrationSystem.mapper;

import cn.edu.aynu.onlineRegistrationSystem.entity.InviteInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteInfoMapper {
    int insertInviteInfo(InviteInfo invite);
    InviteInfo checkExistById(Integer id);
    int deleteInviteById(Integer id);
    int checkExistByFromAndTo(@Param("from_id")Integer fromId,@Param("to_id")Integer toId);
}
