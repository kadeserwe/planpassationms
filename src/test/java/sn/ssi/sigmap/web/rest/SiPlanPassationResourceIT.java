package sn.ssi.sigmap.web.rest;

import sn.ssi.sigmap.PlanpassationmsApp;
import sn.ssi.sigmap.domain.SiPlanPassation;
import sn.ssi.sigmap.repository.SiPlanPassationRepository;
import sn.ssi.sigmap.service.SiPlanPassationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SiPlanPassationResource} REST controller.
 */
@SpringBootTest(classes = PlanpassationmsApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SiPlanPassationResourceIT {

    private static final String DEFAULT_NUMERO_PLAN = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_PLAN = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_DEBUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEBUT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COMMENTAIRE = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTAIRE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ANNEE = 1;
    private static final Integer UPDATED_ANNEE = 2;

    private static final LocalDate DEFAULT_DATE_CREATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CREATION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_MISE_VALIDATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_MISE_VALIDATION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_VALIDATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_VALIDATION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_STATUT = "AAAAAAAAAA";
    private static final String UPDATED_STATUT = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTAIRE_MISE_EN_VALIDATION_AC = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTAIRE_MISE_EN_VALIDATION_AC = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE_MISE_VALIDATION_AC = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_MISE_VALIDATION_AC = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FICHIER_MISE_VALIDATION_AC = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FICHIER_MISE_VALIDATION_AC = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FICHIER_MISE_VALIDATION_AC_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FICHIER_MISE_VALIDATION_AC_CONTENT_TYPE = "image/png";

    private static final LocalDate DEFAULT_DATE_MISE_EN_VALIDATION_CCMP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_MISE_EN_VALIDATION_CCMP = LocalDate.now(ZoneId.systemDefault());

    private static final byte[] DEFAULT_FICHIER_MISE_VALIDATION_CCMP = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FICHIER_MISE_VALIDATION_CCMP = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FICHIER_MISE_VALIDATION_CCMP_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FICHIER_MISE_VALIDATION_CCMP_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_REFERENCE_MISE_VALIDATION_CCMP = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_MISE_VALIDATION_CCMP = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_VALIDATION_1 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_VALIDATION_1 = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COMMENTAIRE_VALIDATION = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTAIRE_VALIDATION = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE_VALIDATION = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_VALIDATION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FICHIER_VALIDATION = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FICHIER_VALIDATION = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FICHIER_VALIDATION_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FICHIER_VALIDATION_CONTENT_TYPE = "image/png";

    private static final LocalDate DEFAULT_DATE_VALIDATION_2 = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_VALIDATION_2 = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_REJET = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_REJET = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_PUBLICATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_PUBLICATION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COMMENTAIRE_PUBLICATION = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTAIRE_PUBLICATION = "BBBBBBBBBB";

    @Autowired
    private SiPlanPassationRepository siPlanPassationRepository;

    @Autowired
    private SiPlanPassationService siPlanPassationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSiPlanPassationMockMvc;

    private SiPlanPassation siPlanPassation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SiPlanPassation createEntity(EntityManager em) {
        SiPlanPassation siPlanPassation = new SiPlanPassation()
            .numeroPlan(DEFAULT_NUMERO_PLAN)
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN)
            .commentaire(DEFAULT_COMMENTAIRE)
            .annee(DEFAULT_ANNEE)
            .dateCreation(DEFAULT_DATE_CREATION)
            .dateMiseValidation(DEFAULT_DATE_MISE_VALIDATION)
            .dateValidation(DEFAULT_DATE_VALIDATION)
            .statut(DEFAULT_STATUT)
            .commentaireMiseEnValidationAC(DEFAULT_COMMENTAIRE_MISE_EN_VALIDATION_AC)
            .referenceMiseValidationAC(DEFAULT_REFERENCE_MISE_VALIDATION_AC)
            .fichierMiseValidationAC(DEFAULT_FICHIER_MISE_VALIDATION_AC)
            .fichierMiseValidationACContentType(DEFAULT_FICHIER_MISE_VALIDATION_AC_CONTENT_TYPE)
            .dateMiseEnValidationCcmp(DEFAULT_DATE_MISE_EN_VALIDATION_CCMP)
            .fichierMiseValidationCcmp(DEFAULT_FICHIER_MISE_VALIDATION_CCMP)
            .fichierMiseValidationCcmpContentType(DEFAULT_FICHIER_MISE_VALIDATION_CCMP_CONTENT_TYPE)
            .referenceMiseValidationCcmp(DEFAULT_REFERENCE_MISE_VALIDATION_CCMP)
            .dateValidation1(DEFAULT_DATE_VALIDATION_1)
            .commentaireValidation(DEFAULT_COMMENTAIRE_VALIDATION)
            .referenceValidation(DEFAULT_REFERENCE_VALIDATION)
            .fichierValidation(DEFAULT_FICHIER_VALIDATION)
            .fichierValidationContentType(DEFAULT_FICHIER_VALIDATION_CONTENT_TYPE)
            .dateValidation2(DEFAULT_DATE_VALIDATION_2)
            .dateRejet(DEFAULT_DATE_REJET)
            .datePublication(DEFAULT_DATE_PUBLICATION)
            .commentairePublication(DEFAULT_COMMENTAIRE_PUBLICATION);
        return siPlanPassation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SiPlanPassation createUpdatedEntity(EntityManager em) {
        SiPlanPassation siPlanPassation = new SiPlanPassation()
            .numeroPlan(UPDATED_NUMERO_PLAN)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .commentaire(UPDATED_COMMENTAIRE)
            .annee(UPDATED_ANNEE)
            .dateCreation(UPDATED_DATE_CREATION)
            .dateMiseValidation(UPDATED_DATE_MISE_VALIDATION)
            .dateValidation(UPDATED_DATE_VALIDATION)
            .statut(UPDATED_STATUT)
            .commentaireMiseEnValidationAC(UPDATED_COMMENTAIRE_MISE_EN_VALIDATION_AC)
            .referenceMiseValidationAC(UPDATED_REFERENCE_MISE_VALIDATION_AC)
            .fichierMiseValidationAC(UPDATED_FICHIER_MISE_VALIDATION_AC)
            .fichierMiseValidationACContentType(UPDATED_FICHIER_MISE_VALIDATION_AC_CONTENT_TYPE)
            .dateMiseEnValidationCcmp(UPDATED_DATE_MISE_EN_VALIDATION_CCMP)
            .fichierMiseValidationCcmp(UPDATED_FICHIER_MISE_VALIDATION_CCMP)
            .fichierMiseValidationCcmpContentType(UPDATED_FICHIER_MISE_VALIDATION_CCMP_CONTENT_TYPE)
            .referenceMiseValidationCcmp(UPDATED_REFERENCE_MISE_VALIDATION_CCMP)
            .dateValidation1(UPDATED_DATE_VALIDATION_1)
            .commentaireValidation(UPDATED_COMMENTAIRE_VALIDATION)
            .referenceValidation(UPDATED_REFERENCE_VALIDATION)
            .fichierValidation(UPDATED_FICHIER_VALIDATION)
            .fichierValidationContentType(UPDATED_FICHIER_VALIDATION_CONTENT_TYPE)
            .dateValidation2(UPDATED_DATE_VALIDATION_2)
            .dateRejet(UPDATED_DATE_REJET)
            .datePublication(UPDATED_DATE_PUBLICATION)
            .commentairePublication(UPDATED_COMMENTAIRE_PUBLICATION);
        return siPlanPassation;
    }

    @BeforeEach
    public void initTest() {
        siPlanPassation = createEntity(em);
    }

    @Test
    @Transactional
    public void createSiPlanPassation() throws Exception {
        int databaseSizeBeforeCreate = siPlanPassationRepository.findAll().size();
        // Create the SiPlanPassation
        restSiPlanPassationMockMvc.perform(post("/api/si-plan-passations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(siPlanPassation)))
            .andExpect(status().isCreated());

        // Validate the SiPlanPassation in the database
        List<SiPlanPassation> siPlanPassationList = siPlanPassationRepository.findAll();
        assertThat(siPlanPassationList).hasSize(databaseSizeBeforeCreate + 1);
        SiPlanPassation testSiPlanPassation = siPlanPassationList.get(siPlanPassationList.size() - 1);
        assertThat(testSiPlanPassation.getNumeroPlan()).isEqualTo(DEFAULT_NUMERO_PLAN);
        assertThat(testSiPlanPassation.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testSiPlanPassation.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
        assertThat(testSiPlanPassation.getCommentaire()).isEqualTo(DEFAULT_COMMENTAIRE);
        assertThat(testSiPlanPassation.getAnnee()).isEqualTo(DEFAULT_ANNEE);
        assertThat(testSiPlanPassation.getDateCreation()).isEqualTo(DEFAULT_DATE_CREATION);
        assertThat(testSiPlanPassation.getDateMiseValidation()).isEqualTo(DEFAULT_DATE_MISE_VALIDATION);
        assertThat(testSiPlanPassation.getDateValidation()).isEqualTo(DEFAULT_DATE_VALIDATION);
        assertThat(testSiPlanPassation.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testSiPlanPassation.getCommentaireMiseEnValidationAC()).isEqualTo(DEFAULT_COMMENTAIRE_MISE_EN_VALIDATION_AC);
        assertThat(testSiPlanPassation.getReferenceMiseValidationAC()).isEqualTo(DEFAULT_REFERENCE_MISE_VALIDATION_AC);
        assertThat(testSiPlanPassation.getFichierMiseValidationAC()).isEqualTo(DEFAULT_FICHIER_MISE_VALIDATION_AC);
        assertThat(testSiPlanPassation.getFichierMiseValidationACContentType()).isEqualTo(DEFAULT_FICHIER_MISE_VALIDATION_AC_CONTENT_TYPE);
        assertThat(testSiPlanPassation.getDateMiseEnValidationCcmp()).isEqualTo(DEFAULT_DATE_MISE_EN_VALIDATION_CCMP);
        assertThat(testSiPlanPassation.getFichierMiseValidationCcmp()).isEqualTo(DEFAULT_FICHIER_MISE_VALIDATION_CCMP);
        assertThat(testSiPlanPassation.getFichierMiseValidationCcmpContentType()).isEqualTo(DEFAULT_FICHIER_MISE_VALIDATION_CCMP_CONTENT_TYPE);
        assertThat(testSiPlanPassation.getReferenceMiseValidationCcmp()).isEqualTo(DEFAULT_REFERENCE_MISE_VALIDATION_CCMP);
        assertThat(testSiPlanPassation.getDateValidation1()).isEqualTo(DEFAULT_DATE_VALIDATION_1);
        assertThat(testSiPlanPassation.getCommentaireValidation()).isEqualTo(DEFAULT_COMMENTAIRE_VALIDATION);
        assertThat(testSiPlanPassation.getReferenceValidation()).isEqualTo(DEFAULT_REFERENCE_VALIDATION);
        assertThat(testSiPlanPassation.getFichierValidation()).isEqualTo(DEFAULT_FICHIER_VALIDATION);
        assertThat(testSiPlanPassation.getFichierValidationContentType()).isEqualTo(DEFAULT_FICHIER_VALIDATION_CONTENT_TYPE);
        assertThat(testSiPlanPassation.getDateValidation2()).isEqualTo(DEFAULT_DATE_VALIDATION_2);
        assertThat(testSiPlanPassation.getDateRejet()).isEqualTo(DEFAULT_DATE_REJET);
        assertThat(testSiPlanPassation.getDatePublication()).isEqualTo(DEFAULT_DATE_PUBLICATION);
        assertThat(testSiPlanPassation.getCommentairePublication()).isEqualTo(DEFAULT_COMMENTAIRE_PUBLICATION);
    }

    @Test
    @Transactional
    public void createSiPlanPassationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = siPlanPassationRepository.findAll().size();

        // Create the SiPlanPassation with an existing ID
        siPlanPassation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSiPlanPassationMockMvc.perform(post("/api/si-plan-passations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(siPlanPassation)))
            .andExpect(status().isBadRequest());

        // Validate the SiPlanPassation in the database
        List<SiPlanPassation> siPlanPassationList = siPlanPassationRepository.findAll();
        assertThat(siPlanPassationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNumeroPlanIsRequired() throws Exception {
        int databaseSizeBeforeTest = siPlanPassationRepository.findAll().size();
        // set the field null
        siPlanPassation.setNumeroPlan(null);

        // Create the SiPlanPassation, which fails.


        restSiPlanPassationMockMvc.perform(post("/api/si-plan-passations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(siPlanPassation)))
            .andExpect(status().isBadRequest());

        List<SiPlanPassation> siPlanPassationList = siPlanPassationRepository.findAll();
        assertThat(siPlanPassationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAnneeIsRequired() throws Exception {
        int databaseSizeBeforeTest = siPlanPassationRepository.findAll().size();
        // set the field null
        siPlanPassation.setAnnee(null);

        // Create the SiPlanPassation, which fails.


        restSiPlanPassationMockMvc.perform(post("/api/si-plan-passations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(siPlanPassation)))
            .andExpect(status().isBadRequest());

        List<SiPlanPassation> siPlanPassationList = siPlanPassationRepository.findAll();
        assertThat(siPlanPassationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateCreationIsRequired() throws Exception {
        int databaseSizeBeforeTest = siPlanPassationRepository.findAll().size();
        // set the field null
        siPlanPassation.setDateCreation(null);

        // Create the SiPlanPassation, which fails.


        restSiPlanPassationMockMvc.perform(post("/api/si-plan-passations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(siPlanPassation)))
            .andExpect(status().isBadRequest());

        List<SiPlanPassation> siPlanPassationList = siPlanPassationRepository.findAll();
        assertThat(siPlanPassationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSiPlanPassations() throws Exception {
        // Initialize the database
        siPlanPassationRepository.saveAndFlush(siPlanPassation);

        // Get all the siPlanPassationList
        restSiPlanPassationMockMvc.perform(get("/api/si-plan-passations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(siPlanPassation.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroPlan").value(hasItem(DEFAULT_NUMERO_PLAN)))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(DEFAULT_DATE_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(DEFAULT_DATE_FIN.toString())))
            .andExpect(jsonPath("$.[*].commentaire").value(hasItem(DEFAULT_COMMENTAIRE)))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)))
            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(DEFAULT_DATE_CREATION.toString())))
            .andExpect(jsonPath("$.[*].dateMiseValidation").value(hasItem(DEFAULT_DATE_MISE_VALIDATION.toString())))
            .andExpect(jsonPath("$.[*].dateValidation").value(hasItem(DEFAULT_DATE_VALIDATION.toString())))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT)))
            .andExpect(jsonPath("$.[*].commentaireMiseEnValidationAC").value(hasItem(DEFAULT_COMMENTAIRE_MISE_EN_VALIDATION_AC)))
            .andExpect(jsonPath("$.[*].referenceMiseValidationAC").value(hasItem(DEFAULT_REFERENCE_MISE_VALIDATION_AC)))
            .andExpect(jsonPath("$.[*].fichierMiseValidationACContentType").value(hasItem(DEFAULT_FICHIER_MISE_VALIDATION_AC_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fichierMiseValidationAC").value(hasItem(Base64Utils.encodeToString(DEFAULT_FICHIER_MISE_VALIDATION_AC))))
            .andExpect(jsonPath("$.[*].dateMiseEnValidationCcmp").value(hasItem(DEFAULT_DATE_MISE_EN_VALIDATION_CCMP.toString())))
            .andExpect(jsonPath("$.[*].fichierMiseValidationCcmpContentType").value(hasItem(DEFAULT_FICHIER_MISE_VALIDATION_CCMP_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fichierMiseValidationCcmp").value(hasItem(Base64Utils.encodeToString(DEFAULT_FICHIER_MISE_VALIDATION_CCMP))))
            .andExpect(jsonPath("$.[*].referenceMiseValidationCcmp").value(hasItem(DEFAULT_REFERENCE_MISE_VALIDATION_CCMP)))
            .andExpect(jsonPath("$.[*].dateValidation1").value(hasItem(DEFAULT_DATE_VALIDATION_1.toString())))
            .andExpect(jsonPath("$.[*].commentaireValidation").value(hasItem(DEFAULT_COMMENTAIRE_VALIDATION)))
            .andExpect(jsonPath("$.[*].referenceValidation").value(hasItem(DEFAULT_REFERENCE_VALIDATION)))
            .andExpect(jsonPath("$.[*].fichierValidationContentType").value(hasItem(DEFAULT_FICHIER_VALIDATION_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fichierValidation").value(hasItem(Base64Utils.encodeToString(DEFAULT_FICHIER_VALIDATION))))
            .andExpect(jsonPath("$.[*].dateValidation2").value(hasItem(DEFAULT_DATE_VALIDATION_2.toString())))
            .andExpect(jsonPath("$.[*].dateRejet").value(hasItem(DEFAULT_DATE_REJET.toString())))
            .andExpect(jsonPath("$.[*].datePublication").value(hasItem(DEFAULT_DATE_PUBLICATION.toString())))
            .andExpect(jsonPath("$.[*].commentairePublication").value(hasItem(DEFAULT_COMMENTAIRE_PUBLICATION)));
    }
    
    @Test
    @Transactional
    public void getSiPlanPassation() throws Exception {
        // Initialize the database
        siPlanPassationRepository.saveAndFlush(siPlanPassation);

        // Get the siPlanPassation
        restSiPlanPassationMockMvc.perform(get("/api/si-plan-passations/{id}", siPlanPassation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(siPlanPassation.getId().intValue()))
            .andExpect(jsonPath("$.numeroPlan").value(DEFAULT_NUMERO_PLAN))
            .andExpect(jsonPath("$.dateDebut").value(DEFAULT_DATE_DEBUT.toString()))
            .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()))
            .andExpect(jsonPath("$.commentaire").value(DEFAULT_COMMENTAIRE))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE))
            .andExpect(jsonPath("$.dateCreation").value(DEFAULT_DATE_CREATION.toString()))
            .andExpect(jsonPath("$.dateMiseValidation").value(DEFAULT_DATE_MISE_VALIDATION.toString()))
            .andExpect(jsonPath("$.dateValidation").value(DEFAULT_DATE_VALIDATION.toString()))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT))
            .andExpect(jsonPath("$.commentaireMiseEnValidationAC").value(DEFAULT_COMMENTAIRE_MISE_EN_VALIDATION_AC))
            .andExpect(jsonPath("$.referenceMiseValidationAC").value(DEFAULT_REFERENCE_MISE_VALIDATION_AC))
            .andExpect(jsonPath("$.fichierMiseValidationACContentType").value(DEFAULT_FICHIER_MISE_VALIDATION_AC_CONTENT_TYPE))
            .andExpect(jsonPath("$.fichierMiseValidationAC").value(Base64Utils.encodeToString(DEFAULT_FICHIER_MISE_VALIDATION_AC)))
            .andExpect(jsonPath("$.dateMiseEnValidationCcmp").value(DEFAULT_DATE_MISE_EN_VALIDATION_CCMP.toString()))
            .andExpect(jsonPath("$.fichierMiseValidationCcmpContentType").value(DEFAULT_FICHIER_MISE_VALIDATION_CCMP_CONTENT_TYPE))
            .andExpect(jsonPath("$.fichierMiseValidationCcmp").value(Base64Utils.encodeToString(DEFAULT_FICHIER_MISE_VALIDATION_CCMP)))
            .andExpect(jsonPath("$.referenceMiseValidationCcmp").value(DEFAULT_REFERENCE_MISE_VALIDATION_CCMP))
            .andExpect(jsonPath("$.dateValidation1").value(DEFAULT_DATE_VALIDATION_1.toString()))
            .andExpect(jsonPath("$.commentaireValidation").value(DEFAULT_COMMENTAIRE_VALIDATION))
            .andExpect(jsonPath("$.referenceValidation").value(DEFAULT_REFERENCE_VALIDATION))
            .andExpect(jsonPath("$.fichierValidationContentType").value(DEFAULT_FICHIER_VALIDATION_CONTENT_TYPE))
            .andExpect(jsonPath("$.fichierValidation").value(Base64Utils.encodeToString(DEFAULT_FICHIER_VALIDATION)))
            .andExpect(jsonPath("$.dateValidation2").value(DEFAULT_DATE_VALIDATION_2.toString()))
            .andExpect(jsonPath("$.dateRejet").value(DEFAULT_DATE_REJET.toString()))
            .andExpect(jsonPath("$.datePublication").value(DEFAULT_DATE_PUBLICATION.toString()))
            .andExpect(jsonPath("$.commentairePublication").value(DEFAULT_COMMENTAIRE_PUBLICATION));
    }
    @Test
    @Transactional
    public void getNonExistingSiPlanPassation() throws Exception {
        // Get the siPlanPassation
        restSiPlanPassationMockMvc.perform(get("/api/si-plan-passations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSiPlanPassation() throws Exception {
        // Initialize the database
        siPlanPassationService.save(siPlanPassation);

        int databaseSizeBeforeUpdate = siPlanPassationRepository.findAll().size();

        // Update the siPlanPassation
        SiPlanPassation updatedSiPlanPassation = siPlanPassationRepository.findById(siPlanPassation.getId()).get();
        // Disconnect from session so that the updates on updatedSiPlanPassation are not directly saved in db
        em.detach(updatedSiPlanPassation);
        updatedSiPlanPassation
            .numeroPlan(UPDATED_NUMERO_PLAN)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .commentaire(UPDATED_COMMENTAIRE)
            .annee(UPDATED_ANNEE)
            .dateCreation(UPDATED_DATE_CREATION)
            .dateMiseValidation(UPDATED_DATE_MISE_VALIDATION)
            .dateValidation(UPDATED_DATE_VALIDATION)
            .statut(UPDATED_STATUT)
            .commentaireMiseEnValidationAC(UPDATED_COMMENTAIRE_MISE_EN_VALIDATION_AC)
            .referenceMiseValidationAC(UPDATED_REFERENCE_MISE_VALIDATION_AC)
            .fichierMiseValidationAC(UPDATED_FICHIER_MISE_VALIDATION_AC)
            .fichierMiseValidationACContentType(UPDATED_FICHIER_MISE_VALIDATION_AC_CONTENT_TYPE)
            .dateMiseEnValidationCcmp(UPDATED_DATE_MISE_EN_VALIDATION_CCMP)
            .fichierMiseValidationCcmp(UPDATED_FICHIER_MISE_VALIDATION_CCMP)
            .fichierMiseValidationCcmpContentType(UPDATED_FICHIER_MISE_VALIDATION_CCMP_CONTENT_TYPE)
            .referenceMiseValidationCcmp(UPDATED_REFERENCE_MISE_VALIDATION_CCMP)
            .dateValidation1(UPDATED_DATE_VALIDATION_1)
            .commentaireValidation(UPDATED_COMMENTAIRE_VALIDATION)
            .referenceValidation(UPDATED_REFERENCE_VALIDATION)
            .fichierValidation(UPDATED_FICHIER_VALIDATION)
            .fichierValidationContentType(UPDATED_FICHIER_VALIDATION_CONTENT_TYPE)
            .dateValidation2(UPDATED_DATE_VALIDATION_2)
            .dateRejet(UPDATED_DATE_REJET)
            .datePublication(UPDATED_DATE_PUBLICATION)
            .commentairePublication(UPDATED_COMMENTAIRE_PUBLICATION);

        restSiPlanPassationMockMvc.perform(put("/api/si-plan-passations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSiPlanPassation)))
            .andExpect(status().isOk());

        // Validate the SiPlanPassation in the database
        List<SiPlanPassation> siPlanPassationList = siPlanPassationRepository.findAll();
        assertThat(siPlanPassationList).hasSize(databaseSizeBeforeUpdate);
        SiPlanPassation testSiPlanPassation = siPlanPassationList.get(siPlanPassationList.size() - 1);
        assertThat(testSiPlanPassation.getNumeroPlan()).isEqualTo(UPDATED_NUMERO_PLAN);
        assertThat(testSiPlanPassation.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testSiPlanPassation.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testSiPlanPassation.getCommentaire()).isEqualTo(UPDATED_COMMENTAIRE);
        assertThat(testSiPlanPassation.getAnnee()).isEqualTo(UPDATED_ANNEE);
        assertThat(testSiPlanPassation.getDateCreation()).isEqualTo(UPDATED_DATE_CREATION);
        assertThat(testSiPlanPassation.getDateMiseValidation()).isEqualTo(UPDATED_DATE_MISE_VALIDATION);
        assertThat(testSiPlanPassation.getDateValidation()).isEqualTo(UPDATED_DATE_VALIDATION);
        assertThat(testSiPlanPassation.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testSiPlanPassation.getCommentaireMiseEnValidationAC()).isEqualTo(UPDATED_COMMENTAIRE_MISE_EN_VALIDATION_AC);
        assertThat(testSiPlanPassation.getReferenceMiseValidationAC()).isEqualTo(UPDATED_REFERENCE_MISE_VALIDATION_AC);
        assertThat(testSiPlanPassation.getFichierMiseValidationAC()).isEqualTo(UPDATED_FICHIER_MISE_VALIDATION_AC);
        assertThat(testSiPlanPassation.getFichierMiseValidationACContentType()).isEqualTo(UPDATED_FICHIER_MISE_VALIDATION_AC_CONTENT_TYPE);
        assertThat(testSiPlanPassation.getDateMiseEnValidationCcmp()).isEqualTo(UPDATED_DATE_MISE_EN_VALIDATION_CCMP);
        assertThat(testSiPlanPassation.getFichierMiseValidationCcmp()).isEqualTo(UPDATED_FICHIER_MISE_VALIDATION_CCMP);
        assertThat(testSiPlanPassation.getFichierMiseValidationCcmpContentType()).isEqualTo(UPDATED_FICHIER_MISE_VALIDATION_CCMP_CONTENT_TYPE);
        assertThat(testSiPlanPassation.getReferenceMiseValidationCcmp()).isEqualTo(UPDATED_REFERENCE_MISE_VALIDATION_CCMP);
        assertThat(testSiPlanPassation.getDateValidation1()).isEqualTo(UPDATED_DATE_VALIDATION_1);
        assertThat(testSiPlanPassation.getCommentaireValidation()).isEqualTo(UPDATED_COMMENTAIRE_VALIDATION);
        assertThat(testSiPlanPassation.getReferenceValidation()).isEqualTo(UPDATED_REFERENCE_VALIDATION);
        assertThat(testSiPlanPassation.getFichierValidation()).isEqualTo(UPDATED_FICHIER_VALIDATION);
        assertThat(testSiPlanPassation.getFichierValidationContentType()).isEqualTo(UPDATED_FICHIER_VALIDATION_CONTENT_TYPE);
        assertThat(testSiPlanPassation.getDateValidation2()).isEqualTo(UPDATED_DATE_VALIDATION_2);
        assertThat(testSiPlanPassation.getDateRejet()).isEqualTo(UPDATED_DATE_REJET);
        assertThat(testSiPlanPassation.getDatePublication()).isEqualTo(UPDATED_DATE_PUBLICATION);
        assertThat(testSiPlanPassation.getCommentairePublication()).isEqualTo(UPDATED_COMMENTAIRE_PUBLICATION);
    }

    @Test
    @Transactional
    public void updateNonExistingSiPlanPassation() throws Exception {
        int databaseSizeBeforeUpdate = siPlanPassationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSiPlanPassationMockMvc.perform(put("/api/si-plan-passations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(siPlanPassation)))
            .andExpect(status().isBadRequest());

        // Validate the SiPlanPassation in the database
        List<SiPlanPassation> siPlanPassationList = siPlanPassationRepository.findAll();
        assertThat(siPlanPassationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSiPlanPassation() throws Exception {
        // Initialize the database
        siPlanPassationService.save(siPlanPassation);

        int databaseSizeBeforeDelete = siPlanPassationRepository.findAll().size();

        // Delete the siPlanPassation
        restSiPlanPassationMockMvc.perform(delete("/api/si-plan-passations/{id}", siPlanPassation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SiPlanPassation> siPlanPassationList = siPlanPassationRepository.findAll();
        assertThat(siPlanPassationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
