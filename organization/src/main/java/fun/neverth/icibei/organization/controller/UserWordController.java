package fun.neverth.icibei.organization.controller;


import fun.neverth.icibei.common.core.vo.Result;
import fun.neverth.icibei.organization.entity.form.UserWordForm;
import fun.neverth.icibei.organization.entity.po.UserWord;
import fun.neverth.icibei.organization.service.UserWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 用户单词信息表 前端控制器
 * </p>
 *
 * @author neverTh
 * @since 2020-10-19
 */
@RestController
@RequestMapping("/userWord")
public class UserWordController {

    @Autowired
    private UserWordService userWordService;

    @PostMapping
    Result<Boolean> add(@Valid @RequestBody UserWordForm form){
        return Result.success(userWordService.add(form));
    }

    @PutMapping("/exeTimes")
    Result<Boolean> incrementExeTimes(@RequestParam String userId, @RequestParam String word){
        return Result.success(userWordService.incrementExeTimes(userId, word));
    }

    @PutMapping("/relation")
    Result<Boolean> updateRelation(String userId, String word, int relation){
        return Result.success(userWordService.updateRelation(userId, word, relation));
    }

    @GetMapping
    Result<UserWord> get(@RequestParam String userId, @RequestParam String word){
        return Result.success(userWordService.get(userId, word));
    }
}
