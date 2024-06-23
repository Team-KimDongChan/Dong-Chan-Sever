package com.dongchan.babo.api.req;

import com.dongchan.babo.domain.entity.EventEntity;
import com.dongchan.babo.domain.entity.MemberEntity;

import java.time.LocalDate;

public record EventReq(
        String title,
        String content,
        String location,
        LocalDate planedDt
){
    public EventEntity toEntity(MemberEntity member){
        return EventEntity.builder()
                .title(title)
                .content(content)
                .location(location)
                .planedDt(planedDt)
                .registerMember(member)
                .build();
    }
}