package com.dongchan.babo.api;

import com.dongchan.babo.api.req.ApplicantReq;
import com.dongchan.babo.service.ApplicantService;
import com.dongchan.babo.service.res.MemberRes;
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
    public ResponseEntity<String> createApplicant(@RequestBody ApplicantReq request) {
        applicantService.saveApplicant(request);
        return ResponseEntity.ok("Applicant saved successfully");
    }

    @GetMapping
    public ResponseEntity<List<MemberRes>> getApplicant(@RequestParam Long id) {
        return applicantService.getApplicant(id);
    }

    @DeleteMapping
    public void deleteApplicant(@RequestBody ApplicantReq req){
        applicantService.cancelApplicant(req);
    }
}
