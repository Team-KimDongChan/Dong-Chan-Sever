package com.dongchan.babo.repository;

import com.dongchan.babo.domain.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    void deleteByApplicantId(String id);
}
