package com.africom.facturation.web.rest;

import com.africom.facturation.FacturationServiceApp;
import com.africom.facturation.domain.OrdreFacturation;
import com.africom.facturation.repository.OrdreFacturationRepository;
import com.africom.facturation.service.OrdreFacturationService;
import com.africom.facturation.service.dto.OrdreFacturationDTO;
import com.africom.facturation.service.mapper.OrdreFacturationMapper;

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
 * Integration tests for the {@link OrdreFacturationResource} REST controller.
 */
@SpringBootTest(classes = FacturationServiceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OrdreFacturationResourceIT {

    private static final String DEFAULT_DEVIS = "AAAAAAAAAA";
    private static final String UPDATED_DEVIS = "BBBBBBBBBB";

    private static final String DEFAULT_BON_DE_COMMANDE = "AAAAAAAAAA";
    private static final String UPDATED_BON_DE_COMMANDE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_FACTURE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_FACTURE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_MONTANT_FACTURE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT_FACTURE = new BigDecimal(2);

    private static final LocalDate DEFAULT_DATE_FACTURE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FACTURE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_ECHEANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ECHEANCE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_DECHARGE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DECHARGE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private OrdreFacturationRepository ordreFacturationRepository;

    @Autowired
    private OrdreFacturationMapper ordreFacturationMapper;

    @Autowired
    private OrdreFacturationService ordreFacturationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrdreFacturationMockMvc;

    private OrdreFacturation ordreFacturation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrdreFacturation createEntity(EntityManager em) {
        OrdreFacturation ordreFacturation = new OrdreFacturation()
            .devis(DEFAULT_DEVIS)
            .bonDeCommande(DEFAULT_BON_DE_COMMANDE)
            .numeroFacture(DEFAULT_NUMERO_FACTURE)
            .montantFacture(DEFAULT_MONTANT_FACTURE)
            .dateFacture(DEFAULT_DATE_FACTURE)
            .dateEcheance(DEFAULT_DATE_ECHEANCE)
            .dateDecharge(DEFAULT_DATE_DECHARGE);
        return ordreFacturation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrdreFacturation createUpdatedEntity(EntityManager em) {
        OrdreFacturation ordreFacturation = new OrdreFacturation()
            .devis(UPDATED_DEVIS)
            .bonDeCommande(UPDATED_BON_DE_COMMANDE)
            .numeroFacture(UPDATED_NUMERO_FACTURE)
            .montantFacture(UPDATED_MONTANT_FACTURE)
            .dateFacture(UPDATED_DATE_FACTURE)
            .dateEcheance(UPDATED_DATE_ECHEANCE)
            .dateDecharge(UPDATED_DATE_DECHARGE);
        return ordreFacturation;
    }

    @BeforeEach
    public void initTest() {
        ordreFacturation = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrdreFacturation() throws Exception {
        int databaseSizeBeforeCreate = ordreFacturationRepository.findAll().size();
        // Create the OrdreFacturation
        OrdreFacturationDTO ordreFacturationDTO = ordreFacturationMapper.toDto(ordreFacturation);
        restOrdreFacturationMockMvc.perform(post("/api/ordre-facturations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordreFacturationDTO)))
            .andExpect(status().isCreated());

        // Validate the OrdreFacturation in the database
        List<OrdreFacturation> ordreFacturationList = ordreFacturationRepository.findAll();
        assertThat(ordreFacturationList).hasSize(databaseSizeBeforeCreate + 1);
        OrdreFacturation testOrdreFacturation = ordreFacturationList.get(ordreFacturationList.size() - 1);
        assertThat(testOrdreFacturation.getDevis()).isEqualTo(DEFAULT_DEVIS);
        assertThat(testOrdreFacturation.getBonDeCommande()).isEqualTo(DEFAULT_BON_DE_COMMANDE);
        assertThat(testOrdreFacturation.getNumeroFacture()).isEqualTo(DEFAULT_NUMERO_FACTURE);
        assertThat(testOrdreFacturation.getMontantFacture()).isEqualTo(DEFAULT_MONTANT_FACTURE);
        assertThat(testOrdreFacturation.getDateFacture()).isEqualTo(DEFAULT_DATE_FACTURE);
        assertThat(testOrdreFacturation.getDateEcheance()).isEqualTo(DEFAULT_DATE_ECHEANCE);
        assertThat(testOrdreFacturation.getDateDecharge()).isEqualTo(DEFAULT_DATE_DECHARGE);
    }

    @Test
    @Transactional
    public void createOrdreFacturationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ordreFacturationRepository.findAll().size();

        // Create the OrdreFacturation with an existing ID
        ordreFacturation.setId(1L);
        OrdreFacturationDTO ordreFacturationDTO = ordreFacturationMapper.toDto(ordreFacturation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrdreFacturationMockMvc.perform(post("/api/ordre-facturations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordreFacturationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrdreFacturation in the database
        List<OrdreFacturation> ordreFacturationList = ordreFacturationRepository.findAll();
        assertThat(ordreFacturationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDevisIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordreFacturationRepository.findAll().size();
        // set the field null
        ordreFacturation.setDevis(null);

        // Create the OrdreFacturation, which fails.
        OrdreFacturationDTO ordreFacturationDTO = ordreFacturationMapper.toDto(ordreFacturation);


        restOrdreFacturationMockMvc.perform(post("/api/ordre-facturations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordreFacturationDTO)))
            .andExpect(status().isBadRequest());

        List<OrdreFacturation> ordreFacturationList = ordreFacturationRepository.findAll();
        assertThat(ordreFacturationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBonDeCommandeIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordreFacturationRepository.findAll().size();
        // set the field null
        ordreFacturation.setBonDeCommande(null);

        // Create the OrdreFacturation, which fails.
        OrdreFacturationDTO ordreFacturationDTO = ordreFacturationMapper.toDto(ordreFacturation);


        restOrdreFacturationMockMvc.perform(post("/api/ordre-facturations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordreFacturationDTO)))
            .andExpect(status().isBadRequest());

        List<OrdreFacturation> ordreFacturationList = ordreFacturationRepository.findAll();
        assertThat(ordreFacturationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroFactureIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordreFacturationRepository.findAll().size();
        // set the field null
        ordreFacturation.setNumeroFacture(null);

        // Create the OrdreFacturation, which fails.
        OrdreFacturationDTO ordreFacturationDTO = ordreFacturationMapper.toDto(ordreFacturation);


        restOrdreFacturationMockMvc.perform(post("/api/ordre-facturations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordreFacturationDTO)))
            .andExpect(status().isBadRequest());

        List<OrdreFacturation> ordreFacturationList = ordreFacturationRepository.findAll();
        assertThat(ordreFacturationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantFactureIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordreFacturationRepository.findAll().size();
        // set the field null
        ordreFacturation.setMontantFacture(null);

        // Create the OrdreFacturation, which fails.
        OrdreFacturationDTO ordreFacturationDTO = ordreFacturationMapper.toDto(ordreFacturation);


        restOrdreFacturationMockMvc.perform(post("/api/ordre-facturations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordreFacturationDTO)))
            .andExpect(status().isBadRequest());

        List<OrdreFacturation> ordreFacturationList = ordreFacturationRepository.findAll();
        assertThat(ordreFacturationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateFactureIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordreFacturationRepository.findAll().size();
        // set the field null
        ordreFacturation.setDateFacture(null);

        // Create the OrdreFacturation, which fails.
        OrdreFacturationDTO ordreFacturationDTO = ordreFacturationMapper.toDto(ordreFacturation);


        restOrdreFacturationMockMvc.perform(post("/api/ordre-facturations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordreFacturationDTO)))
            .andExpect(status().isBadRequest());

        List<OrdreFacturation> ordreFacturationList = ordreFacturationRepository.findAll();
        assertThat(ordreFacturationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateEcheanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordreFacturationRepository.findAll().size();
        // set the field null
        ordreFacturation.setDateEcheance(null);

        // Create the OrdreFacturation, which fails.
        OrdreFacturationDTO ordreFacturationDTO = ordreFacturationMapper.toDto(ordreFacturation);


        restOrdreFacturationMockMvc.perform(post("/api/ordre-facturations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordreFacturationDTO)))
            .andExpect(status().isBadRequest());

        List<OrdreFacturation> ordreFacturationList = ordreFacturationRepository.findAll();
        assertThat(ordreFacturationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateDechargeIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordreFacturationRepository.findAll().size();
        // set the field null
        ordreFacturation.setDateDecharge(null);

        // Create the OrdreFacturation, which fails.
        OrdreFacturationDTO ordreFacturationDTO = ordreFacturationMapper.toDto(ordreFacturation);


        restOrdreFacturationMockMvc.perform(post("/api/ordre-facturations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordreFacturationDTO)))
            .andExpect(status().isBadRequest());

        List<OrdreFacturation> ordreFacturationList = ordreFacturationRepository.findAll();
        assertThat(ordreFacturationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrdreFacturations() throws Exception {
        // Initialize the database
        ordreFacturationRepository.saveAndFlush(ordreFacturation);

        // Get all the ordreFacturationList
        restOrdreFacturationMockMvc.perform(get("/api/ordre-facturations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ordreFacturation.getId().intValue())))
            .andExpect(jsonPath("$.[*].devis").value(hasItem(DEFAULT_DEVIS)))
            .andExpect(jsonPath("$.[*].bonDeCommande").value(hasItem(DEFAULT_BON_DE_COMMANDE)))
            .andExpect(jsonPath("$.[*].numeroFacture").value(hasItem(DEFAULT_NUMERO_FACTURE)))
            .andExpect(jsonPath("$.[*].montantFacture").value(hasItem(DEFAULT_MONTANT_FACTURE.intValue())))
            .andExpect(jsonPath("$.[*].dateFacture").value(hasItem(DEFAULT_DATE_FACTURE.toString())))
            .andExpect(jsonPath("$.[*].dateEcheance").value(hasItem(DEFAULT_DATE_ECHEANCE.toString())))
            .andExpect(jsonPath("$.[*].dateDecharge").value(hasItem(DEFAULT_DATE_DECHARGE.toString())));
    }
    
    @Test
    @Transactional
    public void getOrdreFacturation() throws Exception {
        // Initialize the database
        ordreFacturationRepository.saveAndFlush(ordreFacturation);

        // Get the ordreFacturation
        restOrdreFacturationMockMvc.perform(get("/api/ordre-facturations/{id}", ordreFacturation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ordreFacturation.getId().intValue()))
            .andExpect(jsonPath("$.devis").value(DEFAULT_DEVIS))
            .andExpect(jsonPath("$.bonDeCommande").value(DEFAULT_BON_DE_COMMANDE))
            .andExpect(jsonPath("$.numeroFacture").value(DEFAULT_NUMERO_FACTURE))
            .andExpect(jsonPath("$.montantFacture").value(DEFAULT_MONTANT_FACTURE.intValue()))
            .andExpect(jsonPath("$.dateFacture").value(DEFAULT_DATE_FACTURE.toString()))
            .andExpect(jsonPath("$.dateEcheance").value(DEFAULT_DATE_ECHEANCE.toString()))
            .andExpect(jsonPath("$.dateDecharge").value(DEFAULT_DATE_DECHARGE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingOrdreFacturation() throws Exception {
        // Get the ordreFacturation
        restOrdreFacturationMockMvc.perform(get("/api/ordre-facturations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrdreFacturation() throws Exception {
        // Initialize the database
        ordreFacturationRepository.saveAndFlush(ordreFacturation);

        int databaseSizeBeforeUpdate = ordreFacturationRepository.findAll().size();

        // Update the ordreFacturation
        OrdreFacturation updatedOrdreFacturation = ordreFacturationRepository.findById(ordreFacturation.getId()).get();
        // Disconnect from session so that the updates on updatedOrdreFacturation are not directly saved in db
        em.detach(updatedOrdreFacturation);
        updatedOrdreFacturation
            .devis(UPDATED_DEVIS)
            .bonDeCommande(UPDATED_BON_DE_COMMANDE)
            .numeroFacture(UPDATED_NUMERO_FACTURE)
            .montantFacture(UPDATED_MONTANT_FACTURE)
            .dateFacture(UPDATED_DATE_FACTURE)
            .dateEcheance(UPDATED_DATE_ECHEANCE)
            .dateDecharge(UPDATED_DATE_DECHARGE);
        OrdreFacturationDTO ordreFacturationDTO = ordreFacturationMapper.toDto(updatedOrdreFacturation);

        restOrdreFacturationMockMvc.perform(put("/api/ordre-facturations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordreFacturationDTO)))
            .andExpect(status().isOk());

        // Validate the OrdreFacturation in the database
        List<OrdreFacturation> ordreFacturationList = ordreFacturationRepository.findAll();
        assertThat(ordreFacturationList).hasSize(databaseSizeBeforeUpdate);
        OrdreFacturation testOrdreFacturation = ordreFacturationList.get(ordreFacturationList.size() - 1);
        assertThat(testOrdreFacturation.getDevis()).isEqualTo(UPDATED_DEVIS);
        assertThat(testOrdreFacturation.getBonDeCommande()).isEqualTo(UPDATED_BON_DE_COMMANDE);
        assertThat(testOrdreFacturation.getNumeroFacture()).isEqualTo(UPDATED_NUMERO_FACTURE);
        assertThat(testOrdreFacturation.getMontantFacture()).isEqualTo(UPDATED_MONTANT_FACTURE);
        assertThat(testOrdreFacturation.getDateFacture()).isEqualTo(UPDATED_DATE_FACTURE);
        assertThat(testOrdreFacturation.getDateEcheance()).isEqualTo(UPDATED_DATE_ECHEANCE);
        assertThat(testOrdreFacturation.getDateDecharge()).isEqualTo(UPDATED_DATE_DECHARGE);
    }

    @Test
    @Transactional
    public void updateNonExistingOrdreFacturation() throws Exception {
        int databaseSizeBeforeUpdate = ordreFacturationRepository.findAll().size();

        // Create the OrdreFacturation
        OrdreFacturationDTO ordreFacturationDTO = ordreFacturationMapper.toDto(ordreFacturation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrdreFacturationMockMvc.perform(put("/api/ordre-facturations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordreFacturationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrdreFacturation in the database
        List<OrdreFacturation> ordreFacturationList = ordreFacturationRepository.findAll();
        assertThat(ordreFacturationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrdreFacturation() throws Exception {
        // Initialize the database
        ordreFacturationRepository.saveAndFlush(ordreFacturation);

        int databaseSizeBeforeDelete = ordreFacturationRepository.findAll().size();

        // Delete the ordreFacturation
        restOrdreFacturationMockMvc.perform(delete("/api/ordre-facturations/{id}", ordreFacturation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrdreFacturation> ordreFacturationList = ordreFacturationRepository.findAll();
        assertThat(ordreFacturationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
