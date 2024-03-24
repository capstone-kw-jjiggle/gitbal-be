package gitbal.backend.security;

import gitbal.backend.domain.User;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Slf4j
public class CustomUserDetails implements UserDetails, OAuth2User {

    @Getter
    private String nickname;

    @Getter
    private String avatarUrl;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public CustomUserDetails(String nickname, String avatarUrl, Collection<? extends GrantedAuthority> authorities
       ) {
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
        this.authorities = authorities;
    }

    public static CustomUserDetails create(User user) {

        List<GrantedAuthority> authorities = Collections.
            singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        log.info("hello 나는 유저 디테일 하하");
        return new CustomUserDetails(
            user.getNickname(),
            user.getProfile_img(),
            authorities
        );
    }


    public static CustomUserDetails create(User user, Map<String, Object> attributes) {

        CustomUserDetails userDetails = CustomUserDetails.create(user);

        userDetails.setAttributes(attributes);

        log.info("hello 나는 유저 디테일 하하");
        return userDetails;
    }


    @Override
    public String getName() {
        return nickname;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}