package com.dongchan.babo.domain.entity;

import com.dongchan.babo.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@Entity(name = "tbl_event")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String registerId;

    private String location;

    private LocalDate planedDt;
}
