package com.dongchan.babo.service;

import com.dongchan.babo.api.req.EventReq;
import com.dongchan.babo.api.req.EventWithIdReq;
import com.dongchan.babo.common.Response;
import com.dongchan.babo.common.ResponseData;
import com.dongchan.babo.domain.entity.EventEntity;
import com.dongchan.babo.domain.entity.MemberEntity;
import com.dongchan.babo.repository.EventRepository;
import com.dongchan.babo.security.MemberMapper;
import com.dongchan.babo.security.UserSecurity;
import com.dongchan.babo.service.res.EventRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EventService {
    private final MemberMapper memberMapper;
    private final EventRepository eventRepository;
    private final UserSecurity userSecurity;

    public Response register(EventReq req){
        eventRepository.save(req.toEntity(memberMapper.toEntity(userSecurity.getUser())));
        return Response.created("이벤트 생성 성공");
    }

    public ResponseData<List<EventRes>> getList(){
        List<EventEntity> eventList = eventRepository.findAll();
        List<EventRes> res = eventList.stream()
                .map(EventRes::of)
                .toList();
        return ResponseData.ok("이벤트 조회 성공",res);
    }

    public Response modify(EventWithIdReq req){
        EventEntity event = eventRepository.findById(req.id()).get();
        checkIsMine(event.getRegisterMember());
        event.modify(req.title(),req.content(),req.location(),req.planedDt());
        eventRepository.save(event);
        return Response.ok("이벤트 수정 성공");
    }

    public Response delete(Long id){
        EventEntity event = eventRepository.findById(id).get();
        checkIsMine(event.getRegisterMember());
        eventRepository.delete(event);
        return Response.ok("이벤트 삭제 성");
    }

    private void checkIsMine(MemberEntity member){
        if(userSecurity.getUser() == memberMapper.toUser(member))
            throw new RuntimeException();
    }
}
