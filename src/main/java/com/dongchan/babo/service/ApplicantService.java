package com.dongchan.babo.service;

import com.dongchan.babo.api.req.ApplicantReq;
import com.dongchan.babo.service.res.MemberRes;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplicantService {
    void saveApplicant(ApplicantReq request);
    ResponseEntity<List<MemberRes>> getApplicant(Long id);
}
