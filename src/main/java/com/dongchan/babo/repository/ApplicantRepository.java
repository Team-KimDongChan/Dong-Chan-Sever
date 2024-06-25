package com.dongchan.babo.repository;

import com.dongchan.babo.domain.entity.ApplicantEntity;
import com.dongchan.babo.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<ApplicantEntity, Long> {
    List<ApplicantEntity> findByEventId(Long id);
    void deleteByEventIdAndEmail(Long id);
}
