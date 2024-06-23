package com.dongchan.babo.security;

import com.dongchan.babo.repository.MemberRepository;
import com.dongchan.babo.service.MemberRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Component
@Service
@Slf4j
public class AuthDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberRes user = memberRepository
                .findByEmail(email).map(memberMapper::toUser)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return CustomUserDetails.create(user);
    }

}
