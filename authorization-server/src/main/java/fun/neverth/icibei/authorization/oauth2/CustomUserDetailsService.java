package fun.neverth.icibei.authorization.oauth2;

import fun.neverth.icibei.authorization.constant.MessageConstant;

import fun.neverth.icibei.authorization.entity.IRole;
import fun.neverth.icibei.authorization.entity.IUser;
import fun.neverth.icibei.authorization.service.IUserService;
import fun.neverth.icibei.authorization.service.IRoleService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

    private final IUserService userService;

    private final IRoleService roleService;

    public CustomUserDetailsService(IUserService userService, IRoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String uniqueId) throws UsernameNotFoundException {
        IUser user = userService.getByUniqueId(uniqueId);

        if (user == null) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }

        return new SecurityUser(user, obtainGrantedAuthorities(user));
    }

    /**
     * 获得登录者所有角色的权限集合.
     */
    protected Collection<SimpleGrantedAuthority> obtainGrantedAuthorities(IUser user) {
        Set<IRole> roles = roleService.queryUserRolesByUserId(user.getId());
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getCode()))
                .collect(Collectors.toList());
    }
}
