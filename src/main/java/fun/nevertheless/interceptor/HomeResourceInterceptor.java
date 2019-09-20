package fun.nevertheless.interceptor;

import fun.nevertheless.annotation.LoginUser;
import fun.nevertheless.bean.po.User;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeResourceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            //注解在方法上
            LoginUser loginUserAnnotation = ((HandlerMethod) handler).getMethodAnnotation(LoginUser.class);
            //注解在类上
            LoginUser classLoginUserAnnotation = ((HandlerMethod) handler).getMethod().getDeclaringClass().getAnnotation(LoginUser.class);
            if ((loginUserAnnotation != null) || (classLoginUserAnnotation != null)) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    if (session.getAttribute("LoginState").equals("1")){
                        User user = (User)session.getAttribute("user");
                        if (user != null){
                            System.out.println("welcome" + user.getUser_account());
                            System.out.println("you have login");
                            return true;
                        }
                    }
                } else {//session为空，用户未登录
                    response.getWriter().println("you have to logon");
                    response.getWriter().println("<a href='#'>touch here</a> ");
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
