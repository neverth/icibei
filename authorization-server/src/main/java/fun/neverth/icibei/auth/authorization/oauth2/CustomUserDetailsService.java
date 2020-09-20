package fun.neverth.icibei.auth.authorization.oauth2;

import fun.neverth.icibei.auth.authorization.constant.MessageConstant;
import fun.neverth.icibei.auth.authorization.entity.Role;
import fun.neverth.icibei.auth.authorization.entity.User;
import fun.neverth.icibei.auth.authorization.service.RoleService;
import fun.neverth.icibei.auth.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/17 16:56
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    private final RoleService roleService;

    public CustomUserDetailsService(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String uniqueId) throws UsernameNotFoundException {
        User user = userService.getByUniqueId(uniqueId);

        if (user == null) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }

        return new SecurityUser(user, obtainGrantedAuthorities(user));
    }

    /**
     * 获得登录者所有角色的权限集合.
     */
    protected Collection<SimpleGrantedAuthority> obtainGrantedAuthorities(User user) {
        Set<Role> roles = roleService.queryUserRolesByUserId(user.getId());
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getCode()))
                .collect(Collectors.toList());
    }
}
