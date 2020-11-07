package fun.neverth.icibei.organization.controller;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import fun.neverth.icibei.common.core.vo.Result;
import fun.neverth.icibei.organization.entity.form.UserForm;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;

/**
 * @author NeverTh
 * @date 20:14 2020/11/7
 */
@RestController
@RequestMapping("/file")
public class FileController {

    final String accessKey = "KS1_Wu6paDRLTcv5KYnuu-F_a8l-dzNKEsE-NzzQ";
    final String secretKey = "5xzwds96UAjZEtmCKeaOID7GbFToySajpJ7gvpBl";
    final String bucket = "icibei";

    @GetMapping("/upload/token")
    public Result<String> getUploadToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        StringMap putPolicy = new StringMap();
        putPolicy.put("callbackUrl", "http://127.0.0.1:8010/file/upload/callback");
        putPolicy.put("callbackBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        putPolicy.put("callbackBodyType", "application/json");
        long expireSeconds = 3600;
        String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
        return Result.success(upToken);
    }

    @GetMapping("/upload/callback")
    public Result<String> callback(HttpServletRequest request) {
        return Result.success("");
    }
}
