package sn.ssi.sigmap.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import sn.ssi.sigmap.domain.PlanPassation;
import sn.ssi.sigmap.domain.Realisation;
import sn.ssi.sigmap.domain.SigRealisation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SigRealisationDTO {

    private Long id;

    private String libelle;

    private LocalDate dateAttribution;

    private Integer delaiexecution;

    private String objet;

    private BigDecimal montant;

    private Integer examenDncmp;

    private Integer examenPtf;

    private String chapitreImputation;

    private String autorisationEngagement;

    private LocalDate dateReceptionDossier;

    private LocalDate dateNonObjection;

    private LocalDate dateAutorisation;

    private LocalDate dateRecepNonObjection;

    private LocalDate dateRecepDossCorrige;

    private LocalDate datePubParPrmp;

    private LocalDate dateOuverturePlis;

    private LocalDate dateRecepNonObjectOcmp;

    private LocalDate dateRecepRapportEva;

    private LocalDate dateRecepNonObjectPtf;

    private LocalDate dateExamenJuridique;

    private LocalDate dateNotifContrat;

    private LocalDate dateApprobationContrat;

    private PlanPassation planPassation;

    private List<Map<String, Object>> extended = new ArrayList<>();

    @JsonIgnore
    public SigRealisation getSigRealisation() {
        return new SigRealisation(this.id ,
            this.libelle ,
            this.dateAttribution ,
            this.delaiexecution ,
            this.objet ,
            this.montant ,
            this.examenDncmp ,
            this.examenPtf ,
            this.chapitreImputation ,
            this.autorisationEngagement ,
            this.dateReceptionDossier ,
            this.dateNonObjection ,
            this.dateAutorisation ,
            this.dateRecepNonObjection ,
            this.dateRecepDossCorrige ,
            this.datePubParPrmp ,
            this.dateOuverturePlis ,
            this.dateRecepNonObjectOcmp ,
            this.dateRecepRapportEva ,
            this.dateRecepNonObjectPtf ,
            this.dateExamenJuridique ,
            this.dateNotifContrat ,
            this.dateApprobationContrat,
            this.planPassation);
    }

    public SigRealisationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public LocalDate getDateAttribution() {
        return dateAttribution;
    }

    public void setDateAttribution(LocalDate dateAttribution) {
        this.dateAttribution = dateAttribution;
    }

    public Integer getDelaiexecution() {
        return delaiexecution;
    }

    public void setDelaiexecution(Integer delaiexecution) {
        this.delaiexecution = delaiexecution;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Integer getExamenDncmp() {
        return examenDncmp;
    }

    public void setExamenDncmp(Integer examenDncmp) {
        this.examenDncmp = examenDncmp;
    }

    public Integer getExamenPtf() {
        return examenPtf;
    }

    public void setExamenPtf(Integer examenPtf) {
        this.examenPtf = examenPtf;
    }

    public String getChapitreImputation() {
        return chapitreImputation;
    }

    public void setChapitreImputation(String chapitreImputation) {
        this.chapitreImputation = chapitreImputation;
    }

    public String getAutorisationEngagement() {
        return autorisationEngagement;
    }

    public void setAutorisationEngagement(String autorisationEngagement) {
        this.autorisationEngagement = autorisationEngagement;
    }

    public LocalDate getDateReceptionDossier() {
        return dateReceptionDossier;
    }

    public void setDateReceptionDossier(LocalDate dateReceptionDossier) {
        this.dateReceptionDossier = dateReceptionDossier;
    }

    public LocalDate getDateNonObjection() {
        return dateNonObjection;
    }

    public void setDateNonObjection(LocalDate dateNonObjection) {
        this.dateNonObjection = dateNonObjection;
    }

    public LocalDate getDateAutorisation() {
        return dateAutorisation;
    }

    public void setDateAutorisation(LocalDate dateAutorisation) {
        this.dateAutorisation = dateAutorisation;
    }

    public LocalDate getDateRecepNonObjection() {
        return dateRecepNonObjection;
    }

    public void setDateRecepNonObjection(LocalDate dateRecepNonObjection) {
        this.dateRecepNonObjection = dateRecepNonObjection;
    }

    public LocalDate getDateRecepDossCorrige() {
        return dateRecepDossCorrige;
    }

    public void setDateRecepDossCorrige(LocalDate dateRecepDossCorrige) {
        this.dateRecepDossCorrige = dateRecepDossCorrige;
    }

    public LocalDate getDatePubParPrmp() {
        return datePubParPrmp;
    }

    public void setDatePubParPrmp(LocalDate datePubParPrmp) {
        this.datePubParPrmp = datePubParPrmp;
    }

    public LocalDate getDateOuverturePlis() {
        return dateOuverturePlis;
    }

    public void setDateOuverturePlis(LocalDate dateOuverturePlis) {
        this.dateOuverturePlis = dateOuverturePlis;
    }

    public LocalDate getDateRecepNonObjectOcmp() {
        return dateRecepNonObjectOcmp;
    }

    public void setDateRecepNonObjectOcmp(LocalDate dateRecepNonObjectOcmp) {
        this.dateRecepNonObjectOcmp = dateRecepNonObjectOcmp;
    }

    public LocalDate getDateRecepRapportEva() {
        return dateRecepRapportEva;
    }

    public void setDateRecepRapportEva(LocalDate dateRecepRapportEva) {
        this.dateRecepRapportEva = dateRecepRapportEva;
    }

    public LocalDate getDateRecepNonObjectPtf() {
        return dateRecepNonObjectPtf;
    }

    public void setDateRecepNonObjectPtf(LocalDate dateRecepNonObjectPtf) {
        this.dateRecepNonObjectPtf = dateRecepNonObjectPtf;
    }

    public LocalDate getDateExamenJuridique() {
        return dateExamenJuridique;
    }

    public void setDateExamenJuridique(LocalDate dateExamenJuridique) {
        this.dateExamenJuridique = dateExamenJuridique;
    }

    public LocalDate getDateNotifContrat() {
        return dateNotifContrat;
    }

    public void setDateNotifContrat(LocalDate dateNotifContrat) {
        this.dateNotifContrat = dateNotifContrat;
    }

    public LocalDate getDateApprobationContrat() {
        return dateApprobationContrat;
    }

    public void setDateApprobationContrat(LocalDate dateApprobationContrat) {
        this.dateApprobationContrat = dateApprobationContrat;
    }

    public PlanPassation getPlanPassation() {
        return planPassation;
    }

    public void setPlanPassation(PlanPassation planPassation) {
        this.planPassation = planPassation;
    }

    public List<Map<String, Object>> getExtended() {
        return extended;
    }

    public void setExtended(List<Map<String, Object>> extended) {
        this.extended = extended;
    }

    public SigRealisationDTO(Long id, String libelle, LocalDate dateAttribution, Integer delaiexecution, String objet, BigDecimal montant, Integer examenDncmp, Integer examenPtf, String chapitreImputation, String autorisationEngagement, LocalDate dateReceptionDossier, LocalDate dateNonObjection, LocalDate dateAutorisation, LocalDate dateRecepNonObjection, LocalDate dateRecepDossCorrige, LocalDate datePubParPrmp, LocalDate dateOuverturePlis, LocalDate dateRecepNonObjectOcmp, LocalDate dateRecepRapportEva, LocalDate dateRecepNonObjectPtf, LocalDate dateExamenJuridique, LocalDate dateNotifContrat, LocalDate dateApprobationContrat, PlanPassation planPassation, List<Map<String, Object>> extended) {
        this.id = id;
        this.libelle = libelle;
        this.dateAttribution = dateAttribution;
        this.delaiexecution = delaiexecution;
        this.objet = objet;
        this.montant = montant;
        this.examenDncmp = examenDncmp;
        this.examenPtf = examenPtf;
        this.chapitreImputation = chapitreImputation;
        this.autorisationEngagement = autorisationEngagement;
        this.dateReceptionDossier = dateReceptionDossier;
        this.dateNonObjection = dateNonObjection;
        this.dateAutorisation = dateAutorisation;
        this.dateRecepNonObjection = dateRecepNonObjection;
        this.dateRecepDossCorrige = dateRecepDossCorrige;
        this.datePubParPrmp = datePubParPrmp;
        this.dateOuverturePlis = dateOuverturePlis;
        this.dateRecepNonObjectOcmp = dateRecepNonObjectOcmp;
        this.dateRecepRapportEva = dateRecepRapportEva;
        this.dateRecepNonObjectPtf = dateRecepNonObjectPtf;
        this.dateExamenJuridique = dateExamenJuridique;
        this.dateNotifContrat = dateNotifContrat;
        this.dateApprobationContrat = dateApprobationContrat;
        this.planPassation = planPassation;
        this.extended = extended;
    }
}
