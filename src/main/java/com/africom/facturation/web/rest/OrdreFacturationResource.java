package com.africom.facturation.web.rest;

import com.africom.facturation.service.OrdreFacturationService;
import com.africom.facturation.web.rest.errors.BadRequestAlertException;
import com.africom.facturation.service.dto.OrdreFacturationDTO;

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
 * REST controller for managing {@link com.africom.facturation.domain.OrdreFacturation}.
 */
@RestController
@RequestMapping("/api")
public class OrdreFacturationResource {

    private final Logger log = LoggerFactory.getLogger(OrdreFacturationResource.class);

    private static final String ENTITY_NAME = "facturationServiceOrdreFacturation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrdreFacturationService ordreFacturationService;

    public OrdreFacturationResource(OrdreFacturationService ordreFacturationService) {
        this.ordreFacturationService = ordreFacturationService;
    }

    /**
     * {@code POST  /ordre-facturations} : Create a new ordreFacturation.
     *
     * @param ordreFacturationDTO the ordreFacturationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ordreFacturationDTO, or with status {@code 400 (Bad Request)} if the ordreFacturation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ordre-facturations")
    public ResponseEntity<OrdreFacturationDTO> createOrdreFacturation(@Valid @RequestBody OrdreFacturationDTO ordreFacturationDTO) throws URISyntaxException {
        log.debug("REST request to save OrdreFacturation : {}", ordreFacturationDTO);
        if (ordreFacturationDTO.getId() != null) {
            throw new BadRequestAlertException("A new ordreFacturation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrdreFacturationDTO result = ordreFacturationService.save(ordreFacturationDTO);
        return ResponseEntity.created(new URI("/api/ordre-facturations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ordre-facturations} : Updates an existing ordreFacturation.
     *
     * @param ordreFacturationDTO the ordreFacturationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ordreFacturationDTO,
     * or with status {@code 400 (Bad Request)} if the ordreFacturationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ordreFacturationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ordre-facturations")
    public ResponseEntity<OrdreFacturationDTO> updateOrdreFacturation(@Valid @RequestBody OrdreFacturationDTO ordreFacturationDTO) throws URISyntaxException {
        log.debug("REST request to update OrdreFacturation : {}", ordreFacturationDTO);
        if (ordreFacturationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrdreFacturationDTO result = ordreFacturationService.save(ordreFacturationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ordreFacturationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ordre-facturations} : get all the ordreFacturations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ordreFacturations in body.
     */
    @GetMapping("/ordre-facturations")
    public ResponseEntity<List<OrdreFacturationDTO>> getAllOrdreFacturations(Pageable pageable) {
        log.debug("REST request to get a page of OrdreFacturations");
        Page<OrdreFacturationDTO> page = ordreFacturationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ordre-facturations/:id} : get the "id" ordreFacturation.
     *
     * @param id the id of the ordreFacturationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ordreFacturationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ordre-facturations/{id}")
    public ResponseEntity<OrdreFacturationDTO> getOrdreFacturation(@PathVariable Long id) {
        log.debug("REST request to get OrdreFacturation : {}", id);
        Optional<OrdreFacturationDTO> ordreFacturationDTO = ordreFacturationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ordreFacturationDTO);
    }

    /**
     * {@code DELETE  /ordre-facturations/:id} : delete the "id" ordreFacturation.
     *
     * @param id the id of the ordreFacturationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ordre-facturations/{id}")
    public ResponseEntity<Void> deleteOrdreFacturation(@PathVariable Long id) {
        log.debug("REST request to delete OrdreFacturation : {}", id);
        ordreFacturationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
