package com.dongchan.babo.service;

import com.dongchan.babo.api.req.AuthReq;
import com.dongchan.babo.api.req.MemberReq;
import com.dongchan.babo.domain.entity.MemberEntity;
import com.dongchan.babo.domain.enums.Role;
import com.dongchan.babo.repository.MemberRepository;
import com.dongchan.babo.security.CustomUserDetails;
import com.dongchan.babo.security.JwtProvider;
import com.dongchan.babo.security.MemberMapper;
import com.dongchan.babo.security.config.JwtType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final MemberMapper memberMapper;

    public void signup(MemberReq request) {
       memberRepository.save(memberMapper.createUserEntity(request));
    }

    public JsonWebTokenResponse auth(AuthReq request) {
        MemberEntity userEntity = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        if (!new BCryptPasswordEncoder().matches(request.getPassword(),userEntity.getPassword())){
            throw new RuntimeException("password Error");
        }
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        MemberRes user = ((CustomUserDetails) authenticate.getPrincipal()).getMember();
        return JsonWebTokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(user.getEmail(), user.getRole()))
                .refreshToken(jwtProvider.generateRefreshToken(user.getEmail(), user.getRole()))
                .build();
    }

    public JsonWebTokenResponse refresh(String token) {
        Jws<Claims> claims = jwtProvider.getClaims(jwtProvider.extractToken(token));
        if (jwtProvider.isWrongType(claims, JwtType.REFRESH)) {
            throw new RuntimeException("Refresh Token Error");
        }
        return JsonWebTokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(claims.getBody().getSubject(), (Role) claims.getHeader().get("authority")))
                .refreshToken(jwtProvider.generateRefreshToken(claims.getBody().getSubject(), (Role) claims.getHeader().get("authority")))
                .build();
    }

}
