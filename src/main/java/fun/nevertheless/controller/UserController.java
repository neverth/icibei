package fun.nevertheless.controller;


import fun.nevertheless.annotation.LoginUser;
import fun.nevertheless.bean.po.User;
import fun.nevertheless.bean.po.UserWordRelation;
import fun.nevertheless.service.UserService;
import fun.nevertheless.service.UserWordRelationService;
import fun.nevertheless.service.WordCet4Service;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WordCet4Service wordCet4Service;

    @Autowired
    private UserWordRelationService userWordRelationService;

    @RequestMapping(value = "/userLogin")
    public void handleUserLoginRequest(Model model,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) throws Exception{
        PrintWriter printWriter = httpServletResponse.getWriter();
        httpServletResponse.setContentType("text/html;charset=utf-8");
        String account =  httpServletRequest.getParameter("account");
        String passwd = httpServletRequest.getParameter("password");
        String passwd1 = httpServletRequest.getParameter("password1");
        User user = new User();
        user.setUser_account(account);
        user.setUser_passw(passwd);
        if(!passwd1.equals("")){
            int result = userService.insertUser(user);
            if(result == 1){
                printWriter.flush();
                printWriter.println("<script>");
                printWriter.println("alert('注册成功')");
                printWriter.println("window.location.href='./';");
                printWriter.println("</script>");
                printWriter.close();
            }
            else{
                printWriter.flush();
                printWriter.println("<script>");
                printWriter.println("alert('数据库中存在用户')");
                printWriter.println("window.location.href='./';");
                printWriter.println("</script>");
                printWriter.close();
            }
        }else{
            user = userService.ValidateLogon(user);
            if(user != null){
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("LoginState", "1");
                session.setAttribute("user", user);
                printWriter.flush();
                printWriter.println("<script>");
                printWriter.println("alert('登录成功')");
                printWriter.println("window.location.href='./';");
                printWriter.println("</script>");
                printWriter.close();
            }
            else{
                printWriter.flush();
                printWriter.println("<script>");
                printWriter.println("alert('密码错误')");
                printWriter.println("window.location.href='./';");
                printWriter.println("</script>");
                printWriter.close();
            }
        }
    }

    @LoginUser
    @RequestMapping(value = "/userLoginOut")
    public void handleUserLoginOutRequest(Model model,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) throws Exception{
        HttpSession session = httpServletRequest.getSession(false);
        session.setAttribute("LoginState", null);
        session.setAttribute("user", null);
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.flush();
        printWriter.println("<script>");
        printWriter.println("window.location.href='./';");
        printWriter.println("</script>");
        printWriter.close();
    }

//    @LoginUser
    @RequestMapping(value = "/userWordList")
    public String  handleuserWordListRequest(Model model,
                                          HttpServletRequest httpServletRequest,
                                          HttpServletResponse httpServletResponse) throws Exception{
        return "view/user/userWordList";
    }
    @RequestMapping(value = "/wordListLike")
    public String  handleUserwordListLikeRequest(Model model,
                                             HttpServletRequest httpServletRequest,
                                             HttpServletResponse httpServletResponse) throws Exception{
        return "view/user/wordListLike";
    }

//    @LoginUser
    @RequestMapping(value = "/getWordList/{pageNum}", produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String handleGetIndexWordListApiRequest(@PathVariable("pageNum") Integer pageNum,
                                            HttpServletRequest httpServletRequest,
                                            HttpServletResponse httpServletResponse) {
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        JSONObject jsonObject = new JSONObject();
        jsonObject = wordCet4Service.getCet4List(pageNum, 50);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/getWordUserAndRelationList/{pageNum}", produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String handleGetWordAndRelationListApiRequest(@PathVariable("pageNum") Integer pageNum,
                                            HttpServletRequest httpServletRequest,
                                            HttpServletResponse httpServletResponse) {
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        String userId =  httpServletRequest.getParameter("userId");
        User user = new User();
        user.setUser_id(Integer.parseInt(userId));
        JSONObject jsonObject = new JSONObject();
        jsonObject = userWordRelationService.getWordUserAndRelationListByUser(pageNum, 50, user);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/getLikeWordList/{userId}/{pageNum}", produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String handleGetLikeWordListApiRequest(@PathVariable("pageNum") Integer pageNum,
                                           @PathVariable("userId") Integer userId,
                                            HttpServletRequest httpServletRequest,
                                            HttpServletResponse httpServletResponse) {
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        if(userId == 0){
            userId = 1;
        }
        JSONObject jsonObject = new JSONObject();
        User user = new User();
        user.setUser_id(userId);
        jsonObject = wordCet4Service.selectLikeCet4WordsByUserId(1, 50, user);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/updateRelationById/{relationId}", produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String handleUpdateRelationByIdApiRequest(@PathVariable("relationId") Integer relationId,
                                           HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse) {
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        String like =  httpServletRequest.getParameter("like");
        String type =  httpServletRequest.getParameter("type");
        UserWordRelation userWordRelation = new UserWordRelation();
        userWordRelation.setUser_word_relation_id(relationId);
        userWordRelation.setUser_word_relation_is_like(Integer.parseInt(like));
        int result = userWordRelationService.updateRelationById(userWordRelation, Integer.parseInt(type));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/addUserWordRelation/", produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String handleAddUserWordRelationApiRequest(HttpServletRequest httpServletRequest,
                                              HttpServletResponse httpServletResponse) {
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        String like =  httpServletRequest.getParameter("like");
        String userId =  httpServletRequest.getParameter("userId");
        String wordId =  httpServletRequest.getParameter("wordId");
        UserWordRelation userWordRelation = new UserWordRelation();
        userWordRelation.setUser_id(Integer.parseInt(userId));
        userWordRelation.setUser_word_relation_is_like(Integer.parseInt(like));
        userWordRelation.setWord_cet4_id(Integer.parseInt(wordId));
        int result = userWordRelationService.insertUserWordRelation(userWordRelation);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject.toString();
    }
}
