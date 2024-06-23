package com.dongchan.babo.service.res;

import com.dongchan.babo.domain.entity.EventEntity;

import java.time.LocalDate;

public record EventRes(
        Long id,
        String title,
        String content,
        String location,
        LocalDate planedDt,
        String registerEmail,
        String registerName
) {
    static public EventRes of(EventEntity event){
        return new EventRes(
                event.getId(),
                event.getTitle(),
                event.getContent(),
                event.getLocation(),
                event.getPlanedDt(),
                event.getRegisterMember().getEmail(),
                event.getRegisterMember().getName()
        );
    }
}