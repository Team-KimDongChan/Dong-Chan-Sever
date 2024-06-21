package com.dongchan.babo.service;

import com.dongchan.babo.repository.ApplicantRepository;
import com.dongchan.babo.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final ApplicantRepository applicantRepository;
    private final EventRepository eventRepository;
}
