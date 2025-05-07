package com.africom.facturation.service;

import com.africom.facturation.domain.FraisMission;
import com.africom.facturation.repository.FraisMissionRepository;
import com.africom.facturation.service.dto.FraisMissionDTO;
import com.africom.facturation.service.mapper.FraisMissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FraisMission}.
 */
@Service
@Transactional
public class FraisMissionService {

    private final Logger log = LoggerFactory.getLogger(FraisMissionService.class);

    private final FraisMissionRepository fraisMissionRepository;

    private final FraisMissionMapper fraisMissionMapper;

    public FraisMissionService(FraisMissionRepository fraisMissionRepository, FraisMissionMapper fraisMissionMapper) {
        this.fraisMissionRepository = fraisMissionRepository;
        this.fraisMissionMapper = fraisMissionMapper;
    }

    /**
     * Save a fraisMission.
     *
     * @param fraisMissionDTO the entity to save.
     * @return the persisted entity.
     */
    public FraisMissionDTO save(FraisMissionDTO fraisMissionDTO) {
        log.debug("Request to save FraisMission : {}", fraisMissionDTO);
        FraisMission fraisMission = fraisMissionMapper.toEntity(fraisMissionDTO);
        fraisMission = fraisMissionRepository.save(fraisMission);
        return fraisMissionMapper.toDto(fraisMission);
    }

    /**
     * Get all the fraisMissions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FraisMissionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FraisMissions");
        return fraisMissionRepository.findAll(pageable)
            .map(fraisMissionMapper::toDto);
    }


    /**
     * Get one fraisMission by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FraisMissionDTO> findOne(Long id) {
        log.debug("Request to get FraisMission : {}", id);
        return fraisMissionRepository.findById(id)
            .map(fraisMissionMapper::toDto);
    }

    /**
     * Delete the fraisMission by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FraisMission : {}", id);
        fraisMissionRepository.deleteById(id);
    }
}
