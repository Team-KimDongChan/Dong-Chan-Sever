package com.dongchan.babo.service.impl;

import com.dongchan.babo.api.req.ApplicantReq;
import com.dongchan.babo.common.Response;
import com.dongchan.babo.common.ResponseData;
import com.dongchan.babo.domain.entity.ApplicantEntity;
import com.dongchan.babo.domain.entity.EventEntity;
import com.dongchan.babo.domain.entity.MemberEntity;
import com.dongchan.babo.repository.ApplicantRepository;
import com.dongchan.babo.repository.EventRepository;
import com.dongchan.babo.security.MemberMapper;
import com.dongchan.babo.security.UserSecurity;
import com.dongchan.babo.service.ApplicantService;
import com.dongchan.babo.service.res.MemberRes;
import lombok.RequiredArgsConstructor;
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
    public Response saveApplicant(ApplicantReq request) {
        MemberEntity member = memberMapper.toEntity(userSecurity.getUser());
        EventEntity event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid event ID: " + request.getEventId()));
        checkExistApplicant(member,event);
        applicantRepository.save(buildApplicant(member, event));
        return Response.created("이벤트 지원 성공");
    }

    private void checkExistApplicant(MemberEntity member, EventEntity event){
        if (applicantRepository.existsByMemberAndEvent(member, event)) throw new RuntimeException("존재하는 지원자");
    }

    private ApplicantEntity buildApplicant(MemberEntity member, EventEntity event){
        return ApplicantEntity.builder()
                .member(member)
                .event(event)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseData<List<MemberRes>> getApplicant(Long id) {
        List<ApplicantEntity> applicantList = applicantRepository.findByEventId(id);
        List<MemberRes> applicantMemberList = applicantList.stream()
                .map(applicant -> MemberRes.of(memberMapper.toUser(applicant.getMember())))
                .toList();
        return ResponseData.ok("eventId로 지원자 조회 성공", applicantMemberList);
    }

    @Override
    @Transactional
    public Response cancelApplicant(ApplicantReq req) {
        MemberEntity curMember = memberMapper.toEntity(userSecurity.getUser());
        EventEntity event = eventRepository.findById(req.getEventId()).get();
        applicantRepository.deleteByMemberAndEvent(curMember, event);
        return Response.ok("eventId로 지원 취소 성공");
    }

}
