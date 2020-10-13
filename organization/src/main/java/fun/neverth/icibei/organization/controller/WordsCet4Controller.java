package fun.neverth.icibei.organization.controller;

import fun.neverth.icibei.common.core.vo.Result;
import fun.neverth.icibei.organization.entity.po.WordsCet4;
import fun.neverth.icibei.organization.service.WordsCet4Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
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
}
