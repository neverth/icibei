package fun.neverth.icibei.organization.controller;

import fun.neverth.icibei.organization.service.TranslateTssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @author neverth.li
 * @date 2020/10/21 10:19
 */
@RestController
@RequestMapping("/translate/tss")
@Slf4j
public class TranslateTssController {

    @Autowired
    private TranslateTssService translateTssService;

    @GetMapping("/{word}")
    public void get(HttpServletResponse response, @PathVariable String word) {
        response.setHeader("Content-Type", "audio/mpeg");
        response.setHeader("cache-control", "max-age=86400");
        response.setHeader("content-disposition", "attachment; filename=tts.mp3");
        try {
            ServletOutputStream out = response.getOutputStream();
            out.write(translateTssService.get(word));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
