package com.dongchan.babo.api.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberReq {
    private String email;
    private String password;
    private String name;
}
