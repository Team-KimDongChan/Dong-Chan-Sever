package com.dongchan.babo.api;

import com.dongchan.babo.api.req.ApplicantReq;
import com.dongchan.babo.common.Response;
import com.dongchan.babo.common.ResponseData;
import com.dongchan.babo.service.ApplicantService;
import com.dongchan.babo.service.res.MemberRes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/applicants")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping
    public Response createApplicant(@RequestBody @Valid ApplicantReq request) {
        return applicantService.saveApplicant(request);
    }

    @GetMapping
    public ResponseData<List<MemberRes>> getApplicant(@RequestParam @Valid Long id) {
        return applicantService.getApplicant(id);
    }

    @DeleteMapping
    public Response deleteApplicant(@RequestBody @Valid ApplicantReq req){
        return applicantService.cancelApplicant(req);
    }
}
