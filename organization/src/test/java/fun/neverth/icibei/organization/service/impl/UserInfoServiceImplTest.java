package fun.neverth.icibei.organization.service.impl;

import fun.neverth.icibei.organization.entity.po.UserInfo;
import fun.neverth.icibei.organization.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserInfoServiceImplTest {

    @Autowired
    UserInfoService userInfoService;

    @Test
    void getByUserId() {
        final UserInfo byUserId = userInfoService.getByUserId("117");
        System.out.println(byUserId);
    }

    @Test
    void getVoByUserId() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }
}