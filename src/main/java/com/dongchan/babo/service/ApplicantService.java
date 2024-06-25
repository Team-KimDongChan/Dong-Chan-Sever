package com.dongchan.babo.service;

import com.dongchan.babo.api.req.ApplicantReq;
import com.dongchan.babo.common.Response;
import com.dongchan.babo.common.ResponseData;
import com.dongchan.babo.service.res.MemberRes;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplicantService {
    Response saveApplicant(ApplicantReq request);
    ResponseData<List<MemberRes>> getApplicant(Long id);
    Response cancelApplicant(ApplicantReq req);
}
