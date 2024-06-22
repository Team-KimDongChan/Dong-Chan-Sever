package com.dongchan.babo.repository;

import com.dongchan.babo.domain.entity.ApplicantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<ApplicantEntity, String> {
}
