package com.dongchan.babo.domain.entity;

import com.dongchan.babo.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@Entity(name = "tbl_applicant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_member_id")
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_event_id")
    private EventEntity event;
}