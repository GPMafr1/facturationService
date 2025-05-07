package com.africom.facturation.web.rest;

import com.africom.facturation.service.FraisMissionService;
import com.africom.facturation.web.rest.errors.BadRequestAlertException;
import com.africom.facturation.service.dto.FraisMissionDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.africom.facturation.domain.FraisMission}.
 */
@RestController
@RequestMapping("/api")
public class FraisMissionResource {

    private final Logger log = LoggerFactory.getLogger(FraisMissionResource.class);

    private static final String ENTITY_NAME = "facturationServiceFraisMission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FraisMissionService fraisMissionService;

    public FraisMissionResource(FraisMissionService fraisMissionService) {
        this.fraisMissionService = fraisMissionService;
    }

    /**
     * {@code POST  /frais-missions} : Create a new fraisMission.
     *
     * @param fraisMissionDTO the fraisMissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fraisMissionDTO, or with status {@code 400 (Bad Request)} if the fraisMission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/frais-missions")
    public ResponseEntity<FraisMissionDTO> createFraisMission(@Valid @RequestBody FraisMissionDTO fraisMissionDTO) throws URISyntaxException {
        log.debug("REST request to save FraisMission : {}", fraisMissionDTO);
        if (fraisMissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new fraisMission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FraisMissionDTO result = fraisMissionService.save(fraisMissionDTO);
        return ResponseEntity.created(new URI("/api/frais-missions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /frais-missions} : Updates an existing fraisMission.
     *
     * @param fraisMissionDTO the fraisMissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fraisMissionDTO,
     * or with status {@code 400 (Bad Request)} if the fraisMissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fraisMissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/frais-missions")
    public ResponseEntity<FraisMissionDTO> updateFraisMission(@Valid @RequestBody FraisMissionDTO fraisMissionDTO) throws URISyntaxException {
        log.debug("REST request to update FraisMission : {}", fraisMissionDTO);
        if (fraisMissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FraisMissionDTO result = fraisMissionService.save(fraisMissionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fraisMissionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /frais-missions} : get all the fraisMissions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fraisMissions in body.
     */
    @GetMapping("/frais-missions")
    public ResponseEntity<List<FraisMissionDTO>> getAllFraisMissions(Pageable pageable) {
        log.debug("REST request to get a page of FraisMissions");
        Page<FraisMissionDTO> page = fraisMissionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /frais-missions/:id} : get the "id" fraisMission.
     *
     * @param id the id of the fraisMissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fraisMissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/frais-missions/{id}")
    public ResponseEntity<FraisMissionDTO> getFraisMission(@PathVariable Long id) {
        log.debug("REST request to get FraisMission : {}", id);
        Optional<FraisMissionDTO> fraisMissionDTO = fraisMissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fraisMissionDTO);
    }

    /**
     * {@code DELETE  /frais-missions/:id} : delete the "id" fraisMission.
     *
     * @param id the id of the fraisMissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/frais-missions/{id}")
    public ResponseEntity<Void> deleteFraisMission(@PathVariable Long id) {
        log.debug("REST request to delete FraisMission : {}", id);
        fraisMissionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
