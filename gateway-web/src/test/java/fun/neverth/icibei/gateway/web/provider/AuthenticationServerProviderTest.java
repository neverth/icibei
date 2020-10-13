package fun.neverth.icibei.gateway.web.provider;

import fun.neverth.icibei.common.core.vo.Result;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class AuthenticationServerProviderTest {

    @Autowired
    AuthenticationServerProvider authenticationServerProvider;

    @Test
    void auth() {
        Result auth = authenticationServerProvider.auth("123", "123", "123");
        System.out.println(auth);
    }
}