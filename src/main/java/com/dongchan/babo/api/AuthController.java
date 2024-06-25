package com.dongchan.babo.api;

import com.dongchan.babo.api.req.AuthReq;
import com.dongchan.babo.api.req.MemberReq;
import com.dongchan.babo.api.req.RefreshTokenReq;
import com.dongchan.babo.common.Response;
import com.dongchan.babo.common.ResponseData;
import com.dongchan.babo.service.JsonWebTokenResponse;
import com.dongchan.babo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final MemberService memberService;

    @PostMapping("/sign")
    public ResponseData<JsonWebTokenResponse> sign(@RequestBody AuthReq req){
        return memberService.auth(req);
    }

    @PostMapping("/sign-up")
    public Response signUp(@RequestBody MemberReq req){
        return memberService.signup(req);
    }

    @PostMapping("/refresh")
    public ResponseData<JsonWebTokenResponse> refresh(@Validated @RequestBody RefreshTokenReq refreshTokenRequest) {
        return memberService.refresh(refreshTokenRequest.getRefreshToken());
    }

}
