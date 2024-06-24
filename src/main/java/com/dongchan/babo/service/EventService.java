package com.dongchan.babo.service;

import com.dongchan.babo.api.req.EventReq;
import com.dongchan.babo.api.req.EventWithIdReq;
import com.dongchan.babo.domain.entity.EventEntity;
import com.dongchan.babo.domain.entity.MemberEntity;
import com.dongchan.babo.repository.ApplicantRepository;
import com.dongchan.babo.repository.EventRepository;
import com.dongchan.babo.security.MemberMapper;
import com.dongchan.babo.security.UserSecurity;
import com.dongchan.babo.service.res.EventRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EventService {
    private final MemberMapper memberMapper;
    private final EventRepository eventRepository;
    private final UserSecurity userSecurity;

    public void register(EventReq req){
        eventRepository.save(req.toEntity(MemberEntity.builder().build()));
    }

    public ResponseEntity<List<EventRes>> getList(){
        List<EventEntity> eventList = eventRepository.findAll();
        List<EventRes> res = eventList.stream()
                .map(EventRes::of)
                .toList();
        return ResponseEntity.ok(res);
    }

    public void modify(EventWithIdReq req){
        EventEntity event = eventRepository.findById(req.id()).get();
        checkIsMine(event.getRegisterMember());
        event.modify(req.title(),req.content(),req.location(),req.planedDt());
        eventRepository.save(event);
    }

    public void delete(Long id){
        EventEntity event = eventRepository.findById(id).get();
        checkIsMine(event.getRegisterMember());
        eventRepository.delete(event);
    }

    private void checkIsMine(MemberEntity member){
        if(userSecurity.getUser() == memberMapper.toUser(member))
            throw new RuntimeException();
    }
}
