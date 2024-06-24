package com.dongchan.babo.service.res;

import com.dongchan.babo.service.MemberVO;

public record MemberRes(String email, String name) {
    static public MemberRes of(MemberVO member){
        return new MemberRes(member.getEmail(), member.getName());
    }
}
