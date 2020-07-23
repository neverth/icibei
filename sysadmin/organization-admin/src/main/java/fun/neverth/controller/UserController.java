package fun.neverth.controller;

import fun.neverth.entity.vo.Result;
import fun.neverth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/23 18:52
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    UserService userService;

    @GetMapping(value = "/{id}")
    public Result get(@PathVariable String id) {
        log.debug("get with id:{}", id);
        return Result.success(userService.get(id));
    }
}
