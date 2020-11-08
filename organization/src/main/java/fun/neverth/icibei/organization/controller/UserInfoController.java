package fun.neverth.icibei.organization.controller;


import fun.neverth.icibei.common.core.vo.Result;
import fun.neverth.icibei.organization.entity.po.User;
import fun.neverth.icibei.organization.entity.po.UserInfo;
import fun.neverth.icibei.organization.entity.vo.UserInfoVO;
import fun.neverth.icibei.organization.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author neverTh
 * @since 2020-10-11
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(value = "/{userId}")
    public Result<UserInfoVO> get(@PathVariable String userId){
        return Result.success(userInfoService.getVoByUserId(userId));
    }

    @PutMapping(value = "/{userId}")
    public Result<Boolean> update(@PathVariable String userId, @RequestBody UserInfo userInfo){
        return Result.success(userInfoService.update(userId, userInfo, true));
    }

    @PostMapping("/{userId}/avatar/upload")
    public Result<Boolean> uploadAvatar(@PathVariable String userId, @RequestParam("avatar")MultipartFile file){
        return Result.success(userInfoService.uploadAvatar(userId, file));
    }
}
