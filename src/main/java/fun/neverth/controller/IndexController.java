package fun.neverth.controller;


import fun.neverth.bean.po.User;
import fun.neverth.service.WordCet4Service;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class IndexController {
    @Autowired
    private WordCet4Service wordCet4Service;


    @RequestMapping(value = "/")
    public String handleIndexRequest(Model model) {
        return "view/index";
    }

    @RequestMapping(value = "/getIndexWordList", produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String handleGetIndexWordListApiRequest(HttpServletRequest httpServletRequest,
                                            HttpServletResponse httpServletResponse) {
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        JSONObject jsonObject = new JSONObject();
        try{
            HttpSession session = httpServletRequest.getSession(false);
            if (session != null) {
                if (session.getAttribute("LoginState").equals("1")){
                    User user = (User)session.getAttribute("user");
                    if (user != null){
                        jsonObject = wordCet4Service.getCet4ListrRndomByUserId(5, user);
                        return jsonObject.toString();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        // jsonObject = wordCet4Service.getCet4List(11, 10);
        jsonObject = wordCet4Service.getCet4ListrRndom(5);
        return jsonObject.toString();
    }
}
