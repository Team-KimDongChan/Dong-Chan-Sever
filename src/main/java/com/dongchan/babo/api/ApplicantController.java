package com.dongchan.babo.api;

import com.dongchan.babo.api.req.ApplicantReq;
import com.dongchan.babo.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping
    public void deleteApplicant(@RequestBody ApplicantReq req){
        applicantService.cancelApplicant(req);
    }
}
