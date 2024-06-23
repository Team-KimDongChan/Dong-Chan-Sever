package com.dongchan.babo.api.req;

import java.time.LocalDate;

public record EventWithIdReq(
        Long id,
        String title,
        String content,
        String location,
        LocalDate planedDt
){
}