package fun.neverth.icibei.organization.controller;

import fun.neverth.icibei.common.core.vo.Result;
import fun.neverth.icibei.organization.entity.param.WordsCet4QueryParam;
import fun.neverth.icibei.organization.entity.po.WordsCet4;
import fun.neverth.icibei.organization.entity.vo.WordsCet4Page;
import fun.neverth.icibei.organization.entity.vo.WordsCet4VO;
import fun.neverth.icibei.organization.service.WordsCet4Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 已经在网管中配置，/words下面的api都不需要鉴权
 *
 * @author neverth.li
 * @date 2020/10/10 16:20
 */
@Slf4j
@RestController
@RequestMapping("/words")
public class WordsCet4Controller {

    private final WordsCet4Service wordsCet4Service;

    public WordsCet4Controller(WordsCet4Service wordsCet4Service) {
        this.wordsCet4Service = wordsCet4Service;
    }

    @GetMapping(value = "/{word}")
    public Result<WordsCet4> get(@PathVariable String word) {
        return Result.success(wordsCet4Service.getByWord(word));
    }

    @GetMapping
    public Result<List<WordsCet4VO>> getFromWords(@RequestParam String words) {
        return Result.success(wordsCet4Service.getFromWords(words.split(",")));
    }

    @GetMapping("/query")
    public Result<WordsCet4Page> query(@Valid WordsCet4QueryParam param) {
        return Result.success(wordsCet4Service.query(param));
    }
}