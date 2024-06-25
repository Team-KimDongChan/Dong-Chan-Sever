package com.dongchan.babo.api.req;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberReq {
    @Email private String email;
    private String password;
    private String name;
}
