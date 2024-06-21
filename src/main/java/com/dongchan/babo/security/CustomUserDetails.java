package com.dongchan.babo.security;

import com.dongchan.babo.service.MemberRes;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomUserDetails implements UserDetails {

    private final MemberRes member;
    private Collection<? extends GrantedAuthority> authorities;

    private CustomUserDetails(final MemberRes member, final Collection<? extends GrantedAuthority> authorities) {
        this.member = member;
        this.authorities = authorities;
    }

    public CustomUserDetails(final MemberRes member) {
        this.member = member;
        this.authorities = Collections.singleton(() -> member.getRole().getKey());
    }

    public static CustomUserDetails create(MemberRes member) {
        return new CustomUserDetails(member, Collections.singleton((GrantedAuthority) member.getRole()::getKey));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getName();
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

}
