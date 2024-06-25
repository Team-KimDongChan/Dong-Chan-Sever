package com.dongchan.babo.service.impl;

import com.dongchan.babo.domain.entity.ApplicantEntity;
import com.dongchan.babo.domain.entity.EventEntity;
import com.dongchan.babo.domain.entity.MemberEntity;
import com.dongchan.babo.api.req.ApplicantReq;
import com.dongchan.babo.repository.ApplicantRepository;
import com.dongchan.babo.repository.EventRepository;
import com.dongchan.babo.repository.MemberRepository;
import com.dongchan.babo.security.MemberMapper;
import com.dongchan.babo.security.UserSecurity;
import com.dongchan.babo.service.ApplicantService;
import com.dongchan.babo.service.res.MemberRes;
import jdk.jfr.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final UserSecurity userSecurity;
    private final EventRepository eventRepository;
    private final MemberMapper memberMapper;

    @Override
    @Transactional
    public void saveApplicant(ApplicantReq request) {
        MemberEntity member = memberMapper.toEntity(userSecurity.getUser());
        EventEntity event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid event ID: " + request.getEventId()));
        applicantRepository.save(buildApplicant(member, event));
    }

    private ApplicantEntity buildApplicant(MemberEntity member, EventEntity event){
        return ApplicantEntity.builder()
                .member(member)
                .event(event)
                .build();
    }

    @Override
    public ResponseEntity<List<MemberRes>> getApplicant(Long id) {
        List<ApplicantEntity> applicantList = applicantRepository.findByEventId(id);

        return ResponseEntity.ok(applicantList.stream()
                .map(applicant -> MemberRes.of(memberMapper.toUser(applicant.getMember())))
                .toList());
    }

    @Override
    @Transactional
    public void cancelApplicant(ApplicantReq req) {
        eventRepository.deleteByApplicantId(req.getMemberId());
    }

}
