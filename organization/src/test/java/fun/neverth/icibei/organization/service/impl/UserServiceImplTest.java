package fun.neverth.icibei.organization.service.impl;

import fun.neverth.icibei.organization.entity.form.UserForm;
import fun.neverth.icibei.organization.entity.po.User;
import fun.neverth.icibei.organization.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    void add() {
        UserForm userForm = new UserForm();
        userForm.setUsername("neverth");
        userForm.setPassword("123");
        userService.add(userForm);
        System.out.println();
    }

    @Test
    void add1() {
        User user = new User();
        user.setUsername("neverth");
        user.setPassword("123");
        userService.add(user);
        System.out.println();
    }
}