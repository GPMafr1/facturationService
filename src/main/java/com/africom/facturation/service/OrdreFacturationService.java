package com.africom.facturation.service;

import com.africom.facturation.domain.OrdreFacturation;
import com.africom.facturation.repository.OrdreFacturationRepository;
import com.africom.facturation.service.dto.OrdreFacturationDTO;
import com.africom.facturation.service.mapper.OrdreFacturationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OrdreFacturation}.
 */
@Service
@Transactional
public class OrdreFacturationService {

    private final Logger log = LoggerFactory.getLogger(OrdreFacturationService.class);

    private final OrdreFacturationRepository ordreFacturationRepository;

    private final OrdreFacturationMapper ordreFacturationMapper;

    public OrdreFacturationService(OrdreFacturationRepository ordreFacturationRepository, OrdreFacturationMapper ordreFacturationMapper) {
        this.ordreFacturationRepository = ordreFacturationRepository;
        this.ordreFacturationMapper = ordreFacturationMapper;
    }

    /**
     * Save a ordreFacturation.
     *
     * @param ordreFacturationDTO the entity to save.
     * @return the persisted entity.
     */
    public OrdreFacturationDTO save(OrdreFacturationDTO ordreFacturationDTO) {
        log.debug("Request to save OrdreFacturation : {}", ordreFacturationDTO);
        OrdreFacturation ordreFacturation = ordreFacturationMapper.toEntity(ordreFacturationDTO);
        ordreFacturation = ordreFacturationRepository.save(ordreFacturation);
        return ordreFacturationMapper.toDto(ordreFacturation);
    }

    /**
     * Get all the ordreFacturations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OrdreFacturationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrdreFacturations");
        return ordreFacturationRepository.findAll(pageable)
            .map(ordreFacturationMapper::toDto);
    }


    /**
     * Get one ordreFacturation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrdreFacturationDTO> findOne(Long id) {
        log.debug("Request to get OrdreFacturation : {}", id);
        return ordreFacturationRepository.findById(id)
            .map(ordreFacturationMapper::toDto);
    }

    /**
     * Delete the ordreFacturation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OrdreFacturation : {}", id);
        ordreFacturationRepository.deleteById(id);
    }
}
