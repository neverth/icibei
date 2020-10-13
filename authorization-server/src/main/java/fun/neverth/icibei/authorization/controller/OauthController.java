package fun.neverth.icibei.authorization.controller;

import fun.neverth.icibei.common.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * @author NeverTh
 * @date 19:50 2020/10/6
 */
@RestController
public class OauthController {

    @Autowired
    TokenEndpoint tokenEndpoint;

    @Autowired
    CheckTokenEndpoint checkTokenEndpoint;

    @PostMapping(value = "/oauth/token")
    public Result postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) {
        Result result;
        try {
            result = customResp(tokenEndpoint.postAccessToken(principal, parameters));
        } catch (InvalidGrantException | UsernameNotFoundException e) {
            result = Result.success(e.getMessage());
        } catch (Exception e) {
            result = Result.fail(e.getMessage());
        }
        return result;
    }

    @PostMapping(value = "/oauth/check_token")
    public Result checkToken(@RequestParam("token") String value) {
        Result result;
        try{
            result = Result.success(checkTokenEndpoint.checkToken(value));
        }catch (Exception e){
            e.printStackTrace();
            result = Result.fail(e.getMessage());
        }
        return result;
    }

    private Result customResp(ResponseEntity<OAuth2AccessToken> responseEntity) {
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return Result.success(responseEntity.getBody());
        }
        return Result.fail();
    }
}
