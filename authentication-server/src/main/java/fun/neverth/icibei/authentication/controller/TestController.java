package fun.neverth.icibei.authentication.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author NeverTh
 * @date 0:16 2020/9/25
 */
@RestController
public class TestController {
    @GetMapping("/me")
    public HttpEntity<?> oauthMe(Principal principal) {
        return ResponseEntity.ok(principal);
    }
}
