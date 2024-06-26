package com.dongchan.babo.service.res;

import com.dongchan.babo.domain.entity.EventEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EventRes(
        Long id,
        String title,
        String content,
        String location,
        LocalDate planedDt,
        String registerEmail,
        String registerName,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    static public EventRes of(EventEntity event){
        return new EventRes(
                event.getId(),
                event.getTitle(),
                event.getContent(),
                event.getLocation(),
                event.getPlanedDt(),
                event.getRegisterMember().getEmail(),
                event.getRegisterMember().getName(),
                event.getCreatedAt(),
                event.getModifiedAt()
        );
    }
}