package fun.neverth.icibei.authorization.oauth2;

import fun.neverth.icibei.authorization.entity.IUser;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/7/17 16:39
 */
@Data
public class SecurityUser implements UserDetails {

    private IUser user;

    private Collection<SimpleGrantedAuthority> authorities;

    public SecurityUser(IUser user, Collection<SimpleGrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.user.getEnabled();
    }
}
