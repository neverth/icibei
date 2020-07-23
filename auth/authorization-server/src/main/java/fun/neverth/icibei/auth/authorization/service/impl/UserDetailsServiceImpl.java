package fun.neverth.icibei.auth.authorization.service.impl;

import cn.hutool.core.collection.CollUtil;
import fun.neverth.icibei.auth.authorization.constant.MessageConstant;
import fun.neverth.icibei.auth.authorization.entity.SecurityUser;
import fun.neverth.icibei.auth.authorization.entity.User;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/17 16:56
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private List<User> userList;

    @Resource
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
        String password = passwordEncoder.encode("123456");
        userList = new ArrayList<>();
        userList.add(new User("123", password));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<User> findUserList = userList
                .stream()
                .filter(item -> item.getUsername().equals(s))
                .collect(Collectors.toList());

        if (CollUtil.isEmpty(findUserList)) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }

        SecurityUser securityUser = new SecurityUser(findUserList.get(0), null);
        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }

        return securityUser;
    }
}
