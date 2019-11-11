package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.SignService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * company: cn.edu.aynu
 * Author: 算法艺术社
 * Create Data: 2019/11/3 0003
 */
@RestController
public class SignController {
    @Autowired
    SignService service;


    @Autowired
    JSONObject json;
    /**
     *个人注册
     * @param memId 学号
     * @param memName 姓名
     * @param memPassowrd 密码
     * @param memEmail 安全邮箱
     * @return json{code:200,msg:xxx,data:[]}
     */
    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public JSONObject signUp(String memId, String memName, String memPassowrd, String memEmail, String memSex)  {

        memInfo memInfo = new memInfo(Integer.valueOf(memId),memName,memEmail,"M",memPassowrd);

        if(service.singUp(memInfo)){
            String obj = JSONObject.toJSONString(memInfo);
            json.put("code",200);
            json.put("msg","注册成功");
            json.put("data",obj);
        }else{
            json.put("success",404);
            json.put("msg","用户已存在！");
        }

        return json;
    }

    /***
     *个人注册ajax检测id是否重复
     * @param memId 检查id重复
     * @return json{code:200,msg:"此学号可以注册"}
     */
    @RequestMapping(value = "/signUp",method = RequestMethod.GET)
    public JSONObject checkId(Integer memId){
        return json;
    }

    /***
     *个人post传入登录表单
     * @param id 前端学号
     * @param password 密码
     * @return json{code:200,msg:登陆成功,data:登录用户对象}
     * 登陆成功向session中存入mem_id和用户类型（mem类型）
     */
    @RequestMapping(value = "/signIn",method = RequestMethod.POST)
    public JSONObject signIn(String id, String password, HttpServletRequest request){
        List<memInfo> list;
        try {
            list = service.signIn(Integer.valueOf(id), password);
            if (list.size() != 0) {
                memInfo user=list.get(0);
                HttpSession session=request.getSession();
                session.setAttribute("mem_id",user.getMemId());
                session.setAttribute("type","mem");
                String jsonstring = JSONArray.toJSONString(list.get(0));
                json.put("data", jsonstring);
                json.put("code", 200);
                json.put("msg", "登陆成功");

            } else {
                json.put("success", 404);
                json.put("msg", "学号不存在或密码错误");
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg","数据库查询失败"+e.getMessage());
        }
        return json;
    }


}
