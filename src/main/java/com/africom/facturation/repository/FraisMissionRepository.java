package com.africom.facturation.repository;

import com.africom.facturation.domain.FraisMission;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FraisMission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FraisMissionRepository extends JpaRepository<FraisMission, Long> {
}
