package sn.ssi.sigmap.web.rest;

import sn.ssi.sigmap.PlanpassationmsApp;
import sn.ssi.sigmap.domain.SigRealisation;
import sn.ssi.sigmap.repository.SigRealisationRepository;
import sn.ssi.sigmap.service.SigRealisationService;

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
 * Integration tests for the {@link SigRealisationResource} REST controller.
 */
@SpringBootTest(classes = PlanpassationmsApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SigRealisationResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_ATTRIBUTION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ATTRIBUTION = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_DELAIEXECUTION = 1;
    private static final Integer UPDATED_DELAIEXECUTION = 2;

    private static final String DEFAULT_OBJET = "AAAAAAAAAA";
    private static final String UPDATED_OBJET = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_MONTANT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MONTANT = new BigDecimal(2);

    private static final Integer DEFAULT_EXAMEN_DNCMP = 1;
    private static final Integer UPDATED_EXAMEN_DNCMP = 2;

    private static final Integer DEFAULT_EXAMEN_PTF = 1;
    private static final Integer UPDATED_EXAMEN_PTF = 2;

    private static final String DEFAULT_CHAPITRE_IMPUTATION = "AAAAAAAAAA";
    private static final String UPDATED_CHAPITRE_IMPUTATION = "BBBBBBBBBB";

    private static final String DEFAULT_AUTORISATION_ENGAGEMENT = "AAAAAAAAAA";
    private static final String UPDATED_AUTORISATION_ENGAGEMENT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_RECEPTION_DOSSIER = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RECEPTION_DOSSIER = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_NON_OBJECTION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_NON_OBJECTION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_AUTORISATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_AUTORISATION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_RECEP_NON_OBJECTION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RECEP_NON_OBJECTION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_RECEP_DOSS_CORRIGE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RECEP_DOSS_CORRIGE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_PUB_PAR_PRMP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_PUB_PAR_PRMP = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_OUVERTURE_PLIS = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OUVERTURE_PLIS = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_RECEP_NON_OBJECT_OCMP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RECEP_NON_OBJECT_OCMP = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_RECEP_RAPPORT_EVA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RECEP_RAPPORT_EVA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_RECEP_NON_OBJECT_PTF = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RECEP_NON_OBJECT_PTF = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_EXAMEN_JURIDIQUE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_EXAMEN_JURIDIQUE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_NOTIF_CONTRAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_NOTIF_CONTRAT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_APPROBATION_CONTRAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_APPROBATION_CONTRAT = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SigRealisationRepository sigRealisationRepository;

    @Autowired
    private SigRealisationService sigRealisationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSigRealisationMockMvc;

    private SigRealisation sigRealisation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SigRealisation createEntity(EntityManager em) {
        SigRealisation sigRealisation = new SigRealisation()
            .libelle(DEFAULT_LIBELLE)
            .dateAttribution(DEFAULT_DATE_ATTRIBUTION)
            .delaiexecution(DEFAULT_DELAIEXECUTION)
            .objet(DEFAULT_OBJET)
            .montant(DEFAULT_MONTANT)
            .examenDncmp(DEFAULT_EXAMEN_DNCMP)
            .examenPtf(DEFAULT_EXAMEN_PTF)
            .chapitreImputation(DEFAULT_CHAPITRE_IMPUTATION)
            .autorisationEngagement(DEFAULT_AUTORISATION_ENGAGEMENT)
            .dateReceptionDossier(DEFAULT_DATE_RECEPTION_DOSSIER)
            .dateNonObjection(DEFAULT_DATE_NON_OBJECTION)
            .dateAutorisation(DEFAULT_DATE_AUTORISATION)
            .dateRecepNonObjection(DEFAULT_DATE_RECEP_NON_OBJECTION)
            .dateRecepDossCorrige(DEFAULT_DATE_RECEP_DOSS_CORRIGE)
            .datePubParPrmp(DEFAULT_DATE_PUB_PAR_PRMP)
            .dateOuverturePlis(DEFAULT_DATE_OUVERTURE_PLIS)
            .dateRecepNonObjectOcmp(DEFAULT_DATE_RECEP_NON_OBJECT_OCMP)
            .dateRecepRapportEva(DEFAULT_DATE_RECEP_RAPPORT_EVA)
            .dateRecepNonObjectPtf(DEFAULT_DATE_RECEP_NON_OBJECT_PTF)
            .dateExamenJuridique(DEFAULT_DATE_EXAMEN_JURIDIQUE)
            .dateNotifContrat(DEFAULT_DATE_NOTIF_CONTRAT)
            .dateApprobationContrat(DEFAULT_DATE_APPROBATION_CONTRAT);
        return sigRealisation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SigRealisation createUpdatedEntity(EntityManager em) {
        SigRealisation sigRealisation = new SigRealisation()
            .libelle(UPDATED_LIBELLE)
            .dateAttribution(UPDATED_DATE_ATTRIBUTION)
            .delaiexecution(UPDATED_DELAIEXECUTION)
            .objet(UPDATED_OBJET)
            .montant(UPDATED_MONTANT)
            .examenDncmp(UPDATED_EXAMEN_DNCMP)
            .examenPtf(UPDATED_EXAMEN_PTF)
            .chapitreImputation(UPDATED_CHAPITRE_IMPUTATION)
            .autorisationEngagement(UPDATED_AUTORISATION_ENGAGEMENT)
            .dateReceptionDossier(UPDATED_DATE_RECEPTION_DOSSIER)
            .dateNonObjection(UPDATED_DATE_NON_OBJECTION)
            .dateAutorisation(UPDATED_DATE_AUTORISATION)
            .dateRecepNonObjection(UPDATED_DATE_RECEP_NON_OBJECTION)
            .dateRecepDossCorrige(UPDATED_DATE_RECEP_DOSS_CORRIGE)
            .datePubParPrmp(UPDATED_DATE_PUB_PAR_PRMP)
            .dateOuverturePlis(UPDATED_DATE_OUVERTURE_PLIS)
            .dateRecepNonObjectOcmp(UPDATED_DATE_RECEP_NON_OBJECT_OCMP)
            .dateRecepRapportEva(UPDATED_DATE_RECEP_RAPPORT_EVA)
            .dateRecepNonObjectPtf(UPDATED_DATE_RECEP_NON_OBJECT_PTF)
            .dateExamenJuridique(UPDATED_DATE_EXAMEN_JURIDIQUE)
            .dateNotifContrat(UPDATED_DATE_NOTIF_CONTRAT)
            .dateApprobationContrat(UPDATED_DATE_APPROBATION_CONTRAT);
        return sigRealisation;
    }

    @BeforeEach
    public void initTest() {
        sigRealisation = createEntity(em);
    }

    @Test
    @Transactional
    public void createSigRealisation() throws Exception {
        int databaseSizeBeforeCreate = sigRealisationRepository.findAll().size();
        // Create the SigRealisation
        restSigRealisationMockMvc.perform(post("/api/sig-realisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sigRealisation)))
            .andExpect(status().isCreated());

        // Validate the SigRealisation in the database
        List<SigRealisation> sigRealisationList = sigRealisationRepository.findAll();
        assertThat(sigRealisationList).hasSize(databaseSizeBeforeCreate + 1);
        SigRealisation testSigRealisation = sigRealisationList.get(sigRealisationList.size() - 1);
        assertThat(testSigRealisation.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testSigRealisation.getDateAttribution()).isEqualTo(DEFAULT_DATE_ATTRIBUTION);
        assertThat(testSigRealisation.getDelaiexecution()).isEqualTo(DEFAULT_DELAIEXECUTION);
        assertThat(testSigRealisation.getObjet()).isEqualTo(DEFAULT_OBJET);
        assertThat(testSigRealisation.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testSigRealisation.getExamenDncmp()).isEqualTo(DEFAULT_EXAMEN_DNCMP);
        assertThat(testSigRealisation.getExamenPtf()).isEqualTo(DEFAULT_EXAMEN_PTF);
        assertThat(testSigRealisation.getChapitreImputation()).isEqualTo(DEFAULT_CHAPITRE_IMPUTATION);
        assertThat(testSigRealisation.getAutorisationEngagement()).isEqualTo(DEFAULT_AUTORISATION_ENGAGEMENT);
        assertThat(testSigRealisation.getDateReceptionDossier()).isEqualTo(DEFAULT_DATE_RECEPTION_DOSSIER);
        assertThat(testSigRealisation.getDateNonObjection()).isEqualTo(DEFAULT_DATE_NON_OBJECTION);
        assertThat(testSigRealisation.getDateAutorisation()).isEqualTo(DEFAULT_DATE_AUTORISATION);
        assertThat(testSigRealisation.getDateRecepNonObjection()).isEqualTo(DEFAULT_DATE_RECEP_NON_OBJECTION);
        assertThat(testSigRealisation.getDateRecepDossCorrige()).isEqualTo(DEFAULT_DATE_RECEP_DOSS_CORRIGE);
        assertThat(testSigRealisation.getDatePubParPrmp()).isEqualTo(DEFAULT_DATE_PUB_PAR_PRMP);
        assertThat(testSigRealisation.getDateOuverturePlis()).isEqualTo(DEFAULT_DATE_OUVERTURE_PLIS);
        assertThat(testSigRealisation.getDateRecepNonObjectOcmp()).isEqualTo(DEFAULT_DATE_RECEP_NON_OBJECT_OCMP);
        assertThat(testSigRealisation.getDateRecepRapportEva()).isEqualTo(DEFAULT_DATE_RECEP_RAPPORT_EVA);
        assertThat(testSigRealisation.getDateRecepNonObjectPtf()).isEqualTo(DEFAULT_DATE_RECEP_NON_OBJECT_PTF);
        assertThat(testSigRealisation.getDateExamenJuridique()).isEqualTo(DEFAULT_DATE_EXAMEN_JURIDIQUE);
        assertThat(testSigRealisation.getDateNotifContrat()).isEqualTo(DEFAULT_DATE_NOTIF_CONTRAT);
        assertThat(testSigRealisation.getDateApprobationContrat()).isEqualTo(DEFAULT_DATE_APPROBATION_CONTRAT);
    }

    @Test
    @Transactional
    public void createSigRealisationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sigRealisationRepository.findAll().size();

        // Create the SigRealisation with an existing ID
        sigRealisation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSigRealisationMockMvc.perform(post("/api/sig-realisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sigRealisation)))
            .andExpect(status().isBadRequest());

        // Validate the SigRealisation in the database
        List<SigRealisation> sigRealisationList = sigRealisationRepository.findAll();
        assertThat(sigRealisationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = sigRealisationRepository.findAll().size();
        // set the field null
        sigRealisation.setLibelle(null);

        // Create the SigRealisation, which fails.


        restSigRealisationMockMvc.perform(post("/api/sig-realisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sigRealisation)))
            .andExpect(status().isBadRequest());

        List<SigRealisation> sigRealisationList = sigRealisationRepository.findAll();
        assertThat(sigRealisationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObjetIsRequired() throws Exception {
        int databaseSizeBeforeTest = sigRealisationRepository.findAll().size();
        // set the field null
        sigRealisation.setObjet(null);

        // Create the SigRealisation, which fails.


        restSigRealisationMockMvc.perform(post("/api/sig-realisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sigRealisation)))
            .andExpect(status().isBadRequest());

        List<SigRealisation> sigRealisationList = sigRealisationRepository.findAll();
        assertThat(sigRealisationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSigRealisations() throws Exception {
        // Initialize the database
        sigRealisationRepository.saveAndFlush(sigRealisation);

        // Get all the sigRealisationList
        restSigRealisationMockMvc.perform(get("/api/sig-realisations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sigRealisation.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].dateAttribution").value(hasItem(DEFAULT_DATE_ATTRIBUTION.toString())))
            .andExpect(jsonPath("$.[*].delaiexecution").value(hasItem(DEFAULT_DELAIEXECUTION)))
            .andExpect(jsonPath("$.[*].objet").value(hasItem(DEFAULT_OBJET)))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].examenDncmp").value(hasItem(DEFAULT_EXAMEN_DNCMP)))
            .andExpect(jsonPath("$.[*].examenPtf").value(hasItem(DEFAULT_EXAMEN_PTF)))
            .andExpect(jsonPath("$.[*].chapitreImputation").value(hasItem(DEFAULT_CHAPITRE_IMPUTATION)))
            .andExpect(jsonPath("$.[*].autorisationEngagement").value(hasItem(DEFAULT_AUTORISATION_ENGAGEMENT)))
            .andExpect(jsonPath("$.[*].dateReceptionDossier").value(hasItem(DEFAULT_DATE_RECEPTION_DOSSIER.toString())))
            .andExpect(jsonPath("$.[*].dateNonObjection").value(hasItem(DEFAULT_DATE_NON_OBJECTION.toString())))
            .andExpect(jsonPath("$.[*].dateAutorisation").value(hasItem(DEFAULT_DATE_AUTORISATION.toString())))
            .andExpect(jsonPath("$.[*].dateRecepNonObjection").value(hasItem(DEFAULT_DATE_RECEP_NON_OBJECTION.toString())))
            .andExpect(jsonPath("$.[*].dateRecepDossCorrige").value(hasItem(DEFAULT_DATE_RECEP_DOSS_CORRIGE.toString())))
            .andExpect(jsonPath("$.[*].datePubParPrmp").value(hasItem(DEFAULT_DATE_PUB_PAR_PRMP.toString())))
            .andExpect(jsonPath("$.[*].dateOuverturePlis").value(hasItem(DEFAULT_DATE_OUVERTURE_PLIS.toString())))
            .andExpect(jsonPath("$.[*].dateRecepNonObjectOcmp").value(hasItem(DEFAULT_DATE_RECEP_NON_OBJECT_OCMP.toString())))
            .andExpect(jsonPath("$.[*].dateRecepRapportEva").value(hasItem(DEFAULT_DATE_RECEP_RAPPORT_EVA.toString())))
            .andExpect(jsonPath("$.[*].dateRecepNonObjectPtf").value(hasItem(DEFAULT_DATE_RECEP_NON_OBJECT_PTF.toString())))
            .andExpect(jsonPath("$.[*].dateExamenJuridique").value(hasItem(DEFAULT_DATE_EXAMEN_JURIDIQUE.toString())))
            .andExpect(jsonPath("$.[*].dateNotifContrat").value(hasItem(DEFAULT_DATE_NOTIF_CONTRAT.toString())))
            .andExpect(jsonPath("$.[*].dateApprobationContrat").value(hasItem(DEFAULT_DATE_APPROBATION_CONTRAT.toString())));
    }
    
    @Test
    @Transactional
    public void getSigRealisation() throws Exception {
        // Initialize the database
        sigRealisationRepository.saveAndFlush(sigRealisation);

        // Get the sigRealisation
        restSigRealisationMockMvc.perform(get("/api/sig-realisations/{id}", sigRealisation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sigRealisation.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.dateAttribution").value(DEFAULT_DATE_ATTRIBUTION.toString()))
            .andExpect(jsonPath("$.delaiexecution").value(DEFAULT_DELAIEXECUTION))
            .andExpect(jsonPath("$.objet").value(DEFAULT_OBJET))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.intValue()))
            .andExpect(jsonPath("$.examenDncmp").value(DEFAULT_EXAMEN_DNCMP))
            .andExpect(jsonPath("$.examenPtf").value(DEFAULT_EXAMEN_PTF))
            .andExpect(jsonPath("$.chapitreImputation").value(DEFAULT_CHAPITRE_IMPUTATION))
            .andExpect(jsonPath("$.autorisationEngagement").value(DEFAULT_AUTORISATION_ENGAGEMENT))
            .andExpect(jsonPath("$.dateReceptionDossier").value(DEFAULT_DATE_RECEPTION_DOSSIER.toString()))
            .andExpect(jsonPath("$.dateNonObjection").value(DEFAULT_DATE_NON_OBJECTION.toString()))
            .andExpect(jsonPath("$.dateAutorisation").value(DEFAULT_DATE_AUTORISATION.toString()))
            .andExpect(jsonPath("$.dateRecepNonObjection").value(DEFAULT_DATE_RECEP_NON_OBJECTION.toString()))
            .andExpect(jsonPath("$.dateRecepDossCorrige").value(DEFAULT_DATE_RECEP_DOSS_CORRIGE.toString()))
            .andExpect(jsonPath("$.datePubParPrmp").value(DEFAULT_DATE_PUB_PAR_PRMP.toString()))
            .andExpect(jsonPath("$.dateOuverturePlis").value(DEFAULT_DATE_OUVERTURE_PLIS.toString()))
            .andExpect(jsonPath("$.dateRecepNonObjectOcmp").value(DEFAULT_DATE_RECEP_NON_OBJECT_OCMP.toString()))
            .andExpect(jsonPath("$.dateRecepRapportEva").value(DEFAULT_DATE_RECEP_RAPPORT_EVA.toString()))
            .andExpect(jsonPath("$.dateRecepNonObjectPtf").value(DEFAULT_DATE_RECEP_NON_OBJECT_PTF.toString()))
            .andExpect(jsonPath("$.dateExamenJuridique").value(DEFAULT_DATE_EXAMEN_JURIDIQUE.toString()))
            .andExpect(jsonPath("$.dateNotifContrat").value(DEFAULT_DATE_NOTIF_CONTRAT.toString()))
            .andExpect(jsonPath("$.dateApprobationContrat").value(DEFAULT_DATE_APPROBATION_CONTRAT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSigRealisation() throws Exception {
        // Get the sigRealisation
        restSigRealisationMockMvc.perform(get("/api/sig-realisations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSigRealisation() throws Exception {
        // Initialize the database
        sigRealisationService.save(sigRealisation);

        int databaseSizeBeforeUpdate = sigRealisationRepository.findAll().size();

        // Update the sigRealisation
        SigRealisation updatedSigRealisation = sigRealisationRepository.findById(sigRealisation.getId()).get();
        // Disconnect from session so that the updates on updatedSigRealisation are not directly saved in db
        em.detach(updatedSigRealisation);
        updatedSigRealisation
            .libelle(UPDATED_LIBELLE)
            .dateAttribution(UPDATED_DATE_ATTRIBUTION)
            .delaiexecution(UPDATED_DELAIEXECUTION)
            .objet(UPDATED_OBJET)
            .montant(UPDATED_MONTANT)
            .examenDncmp(UPDATED_EXAMEN_DNCMP)
            .examenPtf(UPDATED_EXAMEN_PTF)
            .chapitreImputation(UPDATED_CHAPITRE_IMPUTATION)
            .autorisationEngagement(UPDATED_AUTORISATION_ENGAGEMENT)
            .dateReceptionDossier(UPDATED_DATE_RECEPTION_DOSSIER)
            .dateNonObjection(UPDATED_DATE_NON_OBJECTION)
            .dateAutorisation(UPDATED_DATE_AUTORISATION)
            .dateRecepNonObjection(UPDATED_DATE_RECEP_NON_OBJECTION)
            .dateRecepDossCorrige(UPDATED_DATE_RECEP_DOSS_CORRIGE)
            .datePubParPrmp(UPDATED_DATE_PUB_PAR_PRMP)
            .dateOuverturePlis(UPDATED_DATE_OUVERTURE_PLIS)
            .dateRecepNonObjectOcmp(UPDATED_DATE_RECEP_NON_OBJECT_OCMP)
            .dateRecepRapportEva(UPDATED_DATE_RECEP_RAPPORT_EVA)
            .dateRecepNonObjectPtf(UPDATED_DATE_RECEP_NON_OBJECT_PTF)
            .dateExamenJuridique(UPDATED_DATE_EXAMEN_JURIDIQUE)
            .dateNotifContrat(UPDATED_DATE_NOTIF_CONTRAT)
            .dateApprobationContrat(UPDATED_DATE_APPROBATION_CONTRAT);

        restSigRealisationMockMvc.perform(put("/api/sig-realisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSigRealisation)))
            .andExpect(status().isOk());

        // Validate the SigRealisation in the database
        List<SigRealisation> sigRealisationList = sigRealisationRepository.findAll();
        assertThat(sigRealisationList).hasSize(databaseSizeBeforeUpdate);
        SigRealisation testSigRealisation = sigRealisationList.get(sigRealisationList.size() - 1);
        assertThat(testSigRealisation.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testSigRealisation.getDateAttribution()).isEqualTo(UPDATED_DATE_ATTRIBUTION);
        assertThat(testSigRealisation.getDelaiexecution()).isEqualTo(UPDATED_DELAIEXECUTION);
        assertThat(testSigRealisation.getObjet()).isEqualTo(UPDATED_OBJET);
        assertThat(testSigRealisation.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testSigRealisation.getExamenDncmp()).isEqualTo(UPDATED_EXAMEN_DNCMP);
        assertThat(testSigRealisation.getExamenPtf()).isEqualTo(UPDATED_EXAMEN_PTF);
        assertThat(testSigRealisation.getChapitreImputation()).isEqualTo(UPDATED_CHAPITRE_IMPUTATION);
        assertThat(testSigRealisation.getAutorisationEngagement()).isEqualTo(UPDATED_AUTORISATION_ENGAGEMENT);
        assertThat(testSigRealisation.getDateReceptionDossier()).isEqualTo(UPDATED_DATE_RECEPTION_DOSSIER);
        assertThat(testSigRealisation.getDateNonObjection()).isEqualTo(UPDATED_DATE_NON_OBJECTION);
        assertThat(testSigRealisation.getDateAutorisation()).isEqualTo(UPDATED_DATE_AUTORISATION);
        assertThat(testSigRealisation.getDateRecepNonObjection()).isEqualTo(UPDATED_DATE_RECEP_NON_OBJECTION);
        assertThat(testSigRealisation.getDateRecepDossCorrige()).isEqualTo(UPDATED_DATE_RECEP_DOSS_CORRIGE);
        assertThat(testSigRealisation.getDatePubParPrmp()).isEqualTo(UPDATED_DATE_PUB_PAR_PRMP);
        assertThat(testSigRealisation.getDateOuverturePlis()).isEqualTo(UPDATED_DATE_OUVERTURE_PLIS);
        assertThat(testSigRealisation.getDateRecepNonObjectOcmp()).isEqualTo(UPDATED_DATE_RECEP_NON_OBJECT_OCMP);
        assertThat(testSigRealisation.getDateRecepRapportEva()).isEqualTo(UPDATED_DATE_RECEP_RAPPORT_EVA);
        assertThat(testSigRealisation.getDateRecepNonObjectPtf()).isEqualTo(UPDATED_DATE_RECEP_NON_OBJECT_PTF);
        assertThat(testSigRealisation.getDateExamenJuridique()).isEqualTo(UPDATED_DATE_EXAMEN_JURIDIQUE);
        assertThat(testSigRealisation.getDateNotifContrat()).isEqualTo(UPDATED_DATE_NOTIF_CONTRAT);
        assertThat(testSigRealisation.getDateApprobationContrat()).isEqualTo(UPDATED_DATE_APPROBATION_CONTRAT);
    }

    @Test
    @Transactional
    public void updateNonExistingSigRealisation() throws Exception {
        int databaseSizeBeforeUpdate = sigRealisationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSigRealisationMockMvc.perform(put("/api/sig-realisations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sigRealisation)))
            .andExpect(status().isBadRequest());

        // Validate the SigRealisation in the database
        List<SigRealisation> sigRealisationList = sigRealisationRepository.findAll();
        assertThat(sigRealisationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSigRealisation() throws Exception {
        // Initialize the database
        sigRealisationService.save(sigRealisation);

        int databaseSizeBeforeDelete = sigRealisationRepository.findAll().size();

        // Delete the sigRealisation
        restSigRealisationMockMvc.perform(delete("/api/sig-realisations/{id}", sigRealisation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SigRealisation> sigRealisationList = sigRealisationRepository.findAll();
        assertThat(sigRealisationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
