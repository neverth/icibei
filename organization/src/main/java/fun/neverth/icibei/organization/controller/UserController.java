package fun.neverth.icibei.organization.controller;

import fun.neverth.icibei.organization.entity.form.UserForm;
import fun.neverth.icibei.organization.entity.form.UserQueryForm;
import fun.neverth.icibei.organization.entity.form.UserUpdateForm;
import fun.neverth.icibei.organization.entity.param.UserQueryParam;
import fun.neverth.icibei.organization.entity.po.User;
import fun.neverth.icibei.common.core.vo.Result;
import fun.neverth.icibei.organization.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Result<Boolean> add(@Valid @RequestBody UserForm userForm) {
        return Result.success(userService.add(userForm));
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        return Result.success(userService.delete(id));
    }

    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id, @Valid @RequestBody UserUpdateForm userUpdateForm) {
        User user = userUpdateForm.toPO(User.class);
        user.setId(id);
        return Result.success(userService.update(user));
    }

    @GetMapping(value = "/{id}")
    public Result get(@PathVariable String id) {
        log.debug("get with id:{}", id);
        return Result.success(userService.get(id));
    }

    @GetMapping(value = "/validateUniqueUserName/{username}")
    public Result<Boolean> validateUniqueUserName(@PathVariable String username) {
        return Result.success(userService.validateUniqueUserName(username));
    }

    @GetMapping
    public Result query(@RequestParam String uniqueId) {
        return Result.success(userService.getByUniqueId(uniqueId));
    }

    @PostMapping(value = "/conditions")
    public Result search(@Valid @RequestBody UserQueryForm userQueryForm) {
        log.debug("search with userQueryForm:{}", userQueryForm);
        return Result.success(userService.query(userQueryForm.getPage(), userQueryForm.toParam(UserQueryParam.class)));
    }
}