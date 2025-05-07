package com.africom.facturation.web.rest;

import com.africom.facturation.FacturationServiceApp;
import com.africom.facturation.domain.FraisMission;
import com.africom.facturation.repository.FraisMissionRepository;
import com.africom.facturation.service.FraisMissionService;
import com.africom.facturation.service.dto.FraisMissionDTO;
import com.africom.facturation.service.mapper.FraisMissionMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FraisMissionResource} REST controller.
 */
@SpringBootTest(classes = FacturationServiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FraisMissionResourceIT {

    private static final LocalDate DEFAULT_DATE_DEBUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEBUT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_MONTANT_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_TOTAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AVANCE_RECUE = new BigDecimal(1);
    private static final BigDecimal UPDATED_AVANCE_RECUE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_NET_A_PAYER = new BigDecimal(1);
    private static final BigDecimal UPDATED_NET_A_PAYER = new BigDecimal(2);

    @Autowired
    private FraisMissionRepository fraisMissionRepository;

    @Autowired
    private FraisMissionMapper fraisMissionMapper;

    @Autowired
    private FraisMissionService fraisMissionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFraisMissionMockMvc;

    private FraisMission fraisMission;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FraisMission createEntity(EntityManager em) {
        FraisMission fraisMission = new FraisMission()
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN)
            .montantTotal(DEFAULT_MONTANT_TOTAL)
            .avanceRecue(DEFAULT_AVANCE_RECUE)
            .netAPayer(DEFAULT_NET_A_PAYER);
        return fraisMission;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FraisMission createUpdatedEntity(EntityManager em) {
        FraisMission fraisMission = new FraisMission()
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .montantTotal(UPDATED_MONTANT_TOTAL)
            .avanceRecue(UPDATED_AVANCE_RECUE)
            .netAPayer(UPDATED_NET_A_PAYER);
        return fraisMission;
    }

    @BeforeEach
    public void initTest() {
        fraisMission = createEntity(em);
    }

    @Test
    @Transactional
    public void createFraisMission() throws Exception {
        int databaseSizeBeforeCreate = fraisMissionRepository.findAll().size();
        // Create the FraisMission
        FraisMissionDTO fraisMissionDTO = fraisMissionMapper.toDto(fraisMission);
        restFraisMissionMockMvc.perform(post("/api/frais-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fraisMissionDTO)))
            .andExpect(status().isCreated());

        // Validate the FraisMission in the database
        List<FraisMission> fraisMissionList = fraisMissionRepository.findAll();
        assertThat(fraisMissionList).hasSize(databaseSizeBeforeCreate + 1);
        FraisMission testFraisMission = fraisMissionList.get(fraisMissionList.size() - 1);
        assertThat(testFraisMission.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testFraisMission.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
        assertThat(testFraisMission.getMontantTotal()).isEqualTo(DEFAULT_MONTANT_TOTAL);
        assertThat(testFraisMission.getAvanceRecue()).isEqualTo(DEFAULT_AVANCE_RECUE);
        assertThat(testFraisMission.getNetAPayer()).isEqualTo(DEFAULT_NET_A_PAYER);
    }

    @Test
    @Transactional
    public void createFraisMissionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fraisMissionRepository.findAll().size();

        // Create the FraisMission with an existing ID
        fraisMission.setId(1L);
        FraisMissionDTO fraisMissionDTO = fraisMissionMapper.toDto(fraisMission);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFraisMissionMockMvc.perform(post("/api/frais-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fraisMissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FraisMission in the database
        List<FraisMission> fraisMissionList = fraisMissionRepository.findAll();
        assertThat(fraisMissionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateDebutIsRequired() throws Exception {
        int databaseSizeBeforeTest = fraisMissionRepository.findAll().size();
        // set the field null
        fraisMission.setDateDebut(null);

        // Create the FraisMission, which fails.
        FraisMissionDTO fraisMissionDTO = fraisMissionMapper.toDto(fraisMission);


        restFraisMissionMockMvc.perform(post("/api/frais-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fraisMissionDTO)))
            .andExpect(status().isBadRequest());

        List<FraisMission> fraisMissionList = fraisMissionRepository.findAll();
        assertThat(fraisMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateFinIsRequired() throws Exception {
        int databaseSizeBeforeTest = fraisMissionRepository.findAll().size();
        // set the field null
        fraisMission.setDateFin(null);

        // Create the FraisMission, which fails.
        FraisMissionDTO fraisMissionDTO = fraisMissionMapper.toDto(fraisMission);


        restFraisMissionMockMvc.perform(post("/api/frais-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fraisMissionDTO)))
            .andExpect(status().isBadRequest());

        List<FraisMission> fraisMissionList = fraisMissionRepository.findAll();
        assertThat(fraisMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = fraisMissionRepository.findAll().size();
        // set the field null
        fraisMission.setMontantTotal(null);

        // Create the FraisMission, which fails.
        FraisMissionDTO fraisMissionDTO = fraisMissionMapper.toDto(fraisMission);


        restFraisMissionMockMvc.perform(post("/api/frais-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fraisMissionDTO)))
            .andExpect(status().isBadRequest());

        List<FraisMission> fraisMissionList = fraisMissionRepository.findAll();
        assertThat(fraisMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAvanceRecueIsRequired() throws Exception {
        int databaseSizeBeforeTest = fraisMissionRepository.findAll().size();
        // set the field null
        fraisMission.setAvanceRecue(null);

        // Create the FraisMission, which fails.
        FraisMissionDTO fraisMissionDTO = fraisMissionMapper.toDto(fraisMission);


        restFraisMissionMockMvc.perform(post("/api/frais-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fraisMissionDTO)))
            .andExpect(status().isBadRequest());

        List<FraisMission> fraisMissionList = fraisMissionRepository.findAll();
        assertThat(fraisMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNetAPayerIsRequired() throws Exception {
        int databaseSizeBeforeTest = fraisMissionRepository.findAll().size();
        // set the field null
        fraisMission.setNetAPayer(null);

        // Create the FraisMission, which fails.
        FraisMissionDTO fraisMissionDTO = fraisMissionMapper.toDto(fraisMission);


        restFraisMissionMockMvc.perform(post("/api/frais-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fraisMissionDTO)))
            .andExpect(status().isBadRequest());

        List<FraisMission> fraisMissionList = fraisMissionRepository.findAll();
        assertThat(fraisMissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFraisMissions() throws Exception {
        // Initialize the database
        fraisMissionRepository.saveAndFlush(fraisMission);

        // Get all the fraisMissionList
        restFraisMissionMockMvc.perform(get("/api/frais-missions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fraisMission.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(DEFAULT_DATE_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(DEFAULT_DATE_FIN.toString())))
            .andExpect(jsonPath("$.[*].montantTotal").value(hasItem(DEFAULT_MONTANT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].avanceRecue").value(hasItem(DEFAULT_AVANCE_RECUE.intValue())))
            .andExpect(jsonPath("$.[*].netAPayer").value(hasItem(DEFAULT_NET_A_PAYER.intValue())));
    }
    
    @Test
    @Transactional
    public void getFraisMission() throws Exception {
        // Initialize the database
        fraisMissionRepository.saveAndFlush(fraisMission);

        // Get the fraisMission
        restFraisMissionMockMvc.perform(get("/api/frais-missions/{id}", fraisMission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fraisMission.getId().intValue()))
            .andExpect(jsonPath("$.dateDebut").value(DEFAULT_DATE_DEBUT.toString()))
            .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()))
            .andExpect(jsonPath("$.montantTotal").value(DEFAULT_MONTANT_TOTAL.intValue()))
            .andExpect(jsonPath("$.avanceRecue").value(DEFAULT_AVANCE_RECUE.intValue()))
            .andExpect(jsonPath("$.netAPayer").value(DEFAULT_NET_A_PAYER.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingFraisMission() throws Exception {
        // Get the fraisMission
        restFraisMissionMockMvc.perform(get("/api/frais-missions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFraisMission() throws Exception {
        // Initialize the database
        fraisMissionRepository.saveAndFlush(fraisMission);

        int databaseSizeBeforeUpdate = fraisMissionRepository.findAll().size();

        // Update the fraisMission
        FraisMission updatedFraisMission = fraisMissionRepository.findById(fraisMission.getId()).get();
        // Disconnect from session so that the updates on updatedFraisMission are not directly saved in db
        em.detach(updatedFraisMission);
        updatedFraisMission
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .montantTotal(UPDATED_MONTANT_TOTAL)
            .avanceRecue(UPDATED_AVANCE_RECUE)
            .netAPayer(UPDATED_NET_A_PAYER);
        FraisMissionDTO fraisMissionDTO = fraisMissionMapper.toDto(updatedFraisMission);

        restFraisMissionMockMvc.perform(put("/api/frais-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fraisMissionDTO)))
            .andExpect(status().isOk());

        // Validate the FraisMission in the database
        List<FraisMission> fraisMissionList = fraisMissionRepository.findAll();
        assertThat(fraisMissionList).hasSize(databaseSizeBeforeUpdate);
        FraisMission testFraisMission = fraisMissionList.get(fraisMissionList.size() - 1);
        assertThat(testFraisMission.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testFraisMission.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testFraisMission.getMontantTotal()).isEqualTo(UPDATED_MONTANT_TOTAL);
        assertThat(testFraisMission.getAvanceRecue()).isEqualTo(UPDATED_AVANCE_RECUE);
        assertThat(testFraisMission.getNetAPayer()).isEqualTo(UPDATED_NET_A_PAYER);
    }

    @Test
    @Transactional
    public void updateNonExistingFraisMission() throws Exception {
        int databaseSizeBeforeUpdate = fraisMissionRepository.findAll().size();

        // Create the FraisMission
        FraisMissionDTO fraisMissionDTO = fraisMissionMapper.toDto(fraisMission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFraisMissionMockMvc.perform(put("/api/frais-missions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fraisMissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FraisMission in the database
        List<FraisMission> fraisMissionList = fraisMissionRepository.findAll();
        assertThat(fraisMissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFraisMission() throws Exception {
        // Initialize the database
        fraisMissionRepository.saveAndFlush(fraisMission);

        int databaseSizeBeforeDelete = fraisMissionRepository.findAll().size();

        // Delete the fraisMission
        restFraisMissionMockMvc.perform(delete("/api/frais-missions/{id}", fraisMission.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FraisMission> fraisMissionList = fraisMissionRepository.findAll();
        assertThat(fraisMissionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
