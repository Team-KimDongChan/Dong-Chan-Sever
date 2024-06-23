package com.dongchan.babo.domain.entity;

import com.dongchan.babo.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.lang.reflect.Member;
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

    private String location;

    private LocalDate planedDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_member_id")
    private MemberEntity registerMember;
}
