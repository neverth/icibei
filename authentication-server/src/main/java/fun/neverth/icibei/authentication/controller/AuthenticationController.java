package fun.neverth.icibei.authentication.controller;

import fun.neverth.icibei.authentication.service.AuthenticationService;
import fun.neverth.icibei.authentication.wrapper.HttpServletRequestAuthWrapper;
import fun.neverth.icibei.common.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author neverth.li
 * @date 2020/9/29 15:10
 */
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/auth/permission")
    public Result<String> decide(@RequestParam String url, @RequestParam String method, HttpServletRequest request) {
        String result  = authenticationService.decide(new HttpServletRequestAuthWrapper(request, url, method));
        return Result.success(result);
    }
}
