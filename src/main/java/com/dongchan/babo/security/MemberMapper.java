package com.dongchan.babo.security;

import com.dongchan.babo.api.req.MemberReq;
import com.dongchan.babo.domain.entity.MemberEntity;
import com.dongchan.babo.domain.enums.Role;
import com.dongchan.babo.service.MemberRes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberEntity toCreate(String name, String email, String password){
        return MemberEntity.builder()
                .email(email)
                .name(name)
                .password(password)
                .role(Role.USER)
                .build();
    }

    public MemberRes toUser(MemberEntity entity){
        return MemberRes.builder()
                .email(entity.getEmail())
                .name(entity.getName())
                .password(entity.getPassword())
                .role(entity.getRole())
                .build();
    }

    public MemberEntity createUserEntity(MemberReq signupRequest){
        return MemberEntity.builder()
                .email(signupRequest.getEmail())
                .password(new BCryptPasswordEncoder().encode(signupRequest.getPassword()))
                .name(signupRequest.getName())
                .role(Role.USER)
                .build();
    }

}
