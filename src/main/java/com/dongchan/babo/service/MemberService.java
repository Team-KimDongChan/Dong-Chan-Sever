package com.dongchan.babo.service;

import com.dongchan.babo.api.req.AuthReq;
import com.dongchan.babo.api.req.MemberReq;
import com.dongchan.babo.common.Response;
import com.dongchan.babo.common.ResponseData;
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

    public Response signup(MemberReq request) {
       memberRepository.save(memberMapper.createUserEntity(request));
       return Response.created("회원가입 성공");
    }

    public ResponseData<JsonWebTokenResponse> auth(AuthReq request) {
        MemberEntity userEntity = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        if (!new BCryptPasswordEncoder().matches(request.getPassword(),userEntity.getPassword())){
            throw new RuntimeException("password Error");
        }
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        MemberVO user = ((CustomUserDetails) authenticate.getPrincipal()).getMember();
        JsonWebTokenResponse token = JsonWebTokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(user.getEmail(), user.getRole()))
                .refreshToken(jwtProvider.generateRefreshToken(user.getEmail(), user.getRole()))
                .build();
        return ResponseData.ok("로그인 성공", token);
    }

    public ResponseData<JsonWebTokenResponse> refresh(String token) {
        Jws<Claims> claims = jwtProvider.getClaims(jwtProvider.extractToken(token));
        if (jwtProvider.isWrongType(claims, JwtType.REFRESH)) {
            throw new RuntimeException("Refresh Token Error");
        }
        JsonWebTokenResponse tokenRes = JsonWebTokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(claims.getBody().getSubject(), (Role) claims.getHeader().get("authority")))
                .refreshToken(jwtProvider.generateRefreshToken(claims.getBody().getSubject(), (Role) claims.getHeader().get("authority")))
                .build();
        return ResponseData.ok("토큰 재발급 성공", tokenRes);
    }

}
