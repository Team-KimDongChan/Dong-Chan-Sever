package com.dongchan.babo.service.impl;

import com.dongchan.babo.domain.entity.ApplicantEntity;
import com.dongchan.babo.domain.entity.EventEntity;
import com.dongchan.babo.domain.entity.MemberEntity;
import com.dongchan.babo.api.req.ApplicantReq;
import com.dongchan.babo.repository.ApplicantRepository;
import com.dongchan.babo.repository.EventRepository;
import com.dongchan.babo.repository.MemberRepository;
import com.dongchan.babo.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final MemberRepository memberRepository;
    private final EventRepository eventRepository;

    @Autowired
    public ApplicantServiceImpl(ApplicantRepository applicantRepository, MemberRepository memberRepository, EventRepository eventRepository) {
        this.applicantRepository = applicantRepository;
        this.memberRepository = memberRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional
    public void saveApplicant(ApplicantReq request) {
        MemberEntity member = (MemberEntity) memberRepository.findById(String.valueOf(request.getMemberId()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID: " + request.getMemberId()));
        EventEntity event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid event ID: " + request.getEventId()));

        ApplicantEntity applicant = ApplicantEntity.builder()
                .member(member)
                .event(event)
                .build();

        applicantRepository.save(applicant);
    }
}
