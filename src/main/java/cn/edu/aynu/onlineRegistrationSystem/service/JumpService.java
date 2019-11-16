package cn.edu.aynu.onlineRegistrationSystem.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class JumpService {
    /**
     * 清空session
     * @param session
     */
    public void clearSession(HttpSession session){
        session.removeAttribute("mem_id");
        session.removeAttribute("user");
        session.removeAttribute("team");
        session.removeAttribute("team_id");
        session.removeAttribute("team_account");
        session.removeAttribute("type");
    }

}
