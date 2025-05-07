package com.africom.facturation.repository;

import com.africom.facturation.domain.OrdreFacturation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OrdreFacturation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrdreFacturationRepository extends JpaRepository<OrdreFacturation, Long> {
}
