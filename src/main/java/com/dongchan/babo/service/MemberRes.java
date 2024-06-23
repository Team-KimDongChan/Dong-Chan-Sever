package com.dongchan.babo.service;

import com.dongchan.babo.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRes {
    private String email;
    private String name;
    private String password;
    private Role role;
}
