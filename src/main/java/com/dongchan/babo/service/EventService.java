package com.dongchan.babo.service;

import com.dongchan.babo.api.req.EventReq;
import com.dongchan.babo.api.req.EventWithIdReq;
import com.dongchan.babo.domain.entity.EventEntity;
import com.dongchan.babo.domain.entity.MemberEntity;
import com.dongchan.babo.repository.ApplicantRepository;
import com.dongchan.babo.repository.EventRepository;
import com.dongchan.babo.service.res.EventRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EventService {
    private final ApplicantRepository applicantRepository;
    private final EventRepository eventRepository;

    public void register(EventReq req){
        eventRepository.save(req.toEntity(MemberEntity.builder().build()));
    }

    public List<EventRes> getList(){
        List<EventEntity> eventList = eventRepository.findAll();
        return eventList.stream()
                .map(EventRes::of)
                .toList();
    }

    public void modify(EventWithIdReq req){
        EventEntity event = eventRepository.findById(req.id()).get();
        event.modify(req.title(),req.content(),req.location(),req.planedDt());
        eventRepository.save(event);
    }
}
