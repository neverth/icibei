package fun.nevertheless.controller;

import fun.nevertheless.bean.po.SysUser;
import fun.nevertheless.bean.po.User;
import fun.nevertheless.service.SysUserService;
import fun.nevertheless.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


@Controller
public class AdminController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin")
    public String handleAdminRequest(Model model){
        return "view/admin/index";
    }

    @RequestMapping(value = "/dashboardLogin")
    public String handleDashboardRequest(Model model,
                                         HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse){

        String email =  httpServletRequest.getParameter("email");
        String passwd = httpServletRequest.getParameter("password");
        ArrayList<User> userList = userService.getUserList(1, 10);
        SysUser sysUser  = new SysUser();
        sysUser.setSys_user_account(email);
        sysUser.setSys_user_passw(passwd);
        if (sysUserService.ValidateLogon(sysUser))
            return "view/admin/dashboard";
        else
            return "view/admin/index";
    }

    @RequestMapping(value = "/userAd")
    public  String  handleuserAdRequest(){
        return "view/admin/userAd";
    }

    @RequestMapping(value = "/cet4WordAd")
    public  String  handleCet4WordAdRequest(){
        return "view/admin/cet4WordAd";
    }
    @RequestMapping(value = "/userAdApi/{pageNum}")
    public  @ResponseBody ArrayList<User> handleUserAdApiRequest(@PathVariable("pageNum") Integer pageNum,
                                                                 HttpServletRequest httpServletRequest,
                                                                 HttpServletResponse httpServletResponse){
        ArrayList<User> userList = userService.getUserList(pageNum, 10);
        httpServletResponse.addHeader("Access-Control-Allow-Origin","*");
        return userList;
    }

    @RequestMapping(value = "/userAdDeleteApi/{userId}")
    public  @ResponseBody String handleUserAdDeleteApiRequest(@PathVariable("userId") Integer userId,
                                                                 HttpServletRequest httpServletRequest,
                                                                 HttpServletResponse httpServletResponse){
        httpServletResponse.addHeader("Access-Control-Allow-Origin","*");
        User user = new User();
        user.setUser_id(userId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", String.valueOf(userService.deleteUserById(user)).trim());
        System.out.println();
        return jsonObject.toString();
    }
    @RequestMapping(value = "/userAdUpdateApi/")
    public  @ResponseBody String handleUserAdUpdateApiRequest(HttpServletRequest httpServletRequest,
                                                              HttpServletResponse httpServletResponse){
        httpServletResponse.addHeader("Access-Control-Allow-Origin","*");
        User user = new User();
        user.setUser_id(Integer.parseInt(httpServletRequest.getParameter("user_id")));
        user.setUser_account(httpServletRequest.getParameter("user_account"));
        user.setUser_passw(httpServletRequest.getParameter("user_passw"));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", String.valueOf(userService.updateUserById(user)).trim());
        System.out.println();
        return jsonObject.toString();
    }

}
