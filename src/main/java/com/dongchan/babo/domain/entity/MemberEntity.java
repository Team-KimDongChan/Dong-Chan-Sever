package com.dongchan.babo.domain.entity;

import com.dongchan.babo.common.BaseTimeEntity;
import com.dongchan.babo.domain.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@Entity(name = "tbl_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseTimeEntity {
    @Id
    private String email;

    private String password;

    private String name;

    private Role role;
}
