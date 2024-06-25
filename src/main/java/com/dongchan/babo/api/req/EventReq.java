package com.dongchan.babo.api.req;

import com.dongchan.babo.domain.entity.EventEntity;
import com.dongchan.babo.domain.entity.MemberEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record EventReq(
        String title,
        String content,
        String location,
        @DateTimeFormat(pattern = "YYYY-MM-DD")
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