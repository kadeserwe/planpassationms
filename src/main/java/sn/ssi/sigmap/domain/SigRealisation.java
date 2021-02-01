package sn.ssi.sigmap.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A SigRealisation.
 */
@Entity
@Table(name = "sig_realisation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SigRealisation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "date_attribution")
    private LocalDate dateAttribution;

    @Column(name = "delaiexecution")
    private Integer delaiexecution;

    @NotNull
    @Column(name = "objet", nullable = false)
    private String objet;

    @Column(name = "montant", precision = 21, scale = 2)
    private BigDecimal montant;

    @Column(name = "examen_dncmp")
    private Integer examenDncmp;

    @Column(name = "examen_ptf")
    private Integer examenPtf;

    @Column(name = "chapitre_imputation")
    private String chapitreImputation;

    @Column(name = "autorisation_engagement")
    private String autorisationEngagement;

    @Column(name = "date_reception_dossier")
    private LocalDate dateReceptionDossier;

    @Column(name = "date_non_objection")
    private LocalDate dateNonObjection;

    @Column(name = "date_autorisation")
    private LocalDate dateAutorisation;

    @Column(name = "date_recep_non_objection")
    private LocalDate dateRecepNonObjection;

    @Column(name = "date_recep_doss_corrige")
    private LocalDate dateRecepDossCorrige;

    @Column(name = "date_pub_par_prmp")
    private LocalDate datePubParPrmp;

    @Column(name = "date_ouverture_plis")
    private LocalDate dateOuverturePlis;

    @Column(name = "date_recep_non_object_ocmp")
    private LocalDate dateRecepNonObjectOcmp;

    @Column(name = "date_recep_rapport_eva")
    private LocalDate dateRecepRapportEva;

    @Column(name = "date_recep_non_object_ptf")
    private LocalDate dateRecepNonObjectPtf;

    @Column(name = "date_examen_juridique")
    private LocalDate dateExamenJuridique;

    @Column(name = "date_notif_contrat")
    private LocalDate dateNotifContrat;

    @Column(name = "date_approbation_contrat")
    private LocalDate dateApprobationContrat;

    @ManyToOne
    @JsonIgnoreProperties(value = "sigRealisations", allowSetters = true)
    private PlanPassation planPassation;

    public SigRealisation() {

    }

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public SigRealisation libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public LocalDate getDateAttribution() {
        return dateAttribution;
    }

    public SigRealisation dateAttribution(LocalDate dateAttribution) {
        this.dateAttribution = dateAttribution;
        return this;
    }

    public void setDateAttribution(LocalDate dateAttribution) {
        this.dateAttribution = dateAttribution;
    }

    public Integer getDelaiexecution() {
        return delaiexecution;
    }

    public SigRealisation delaiexecution(Integer delaiexecution) {
        this.delaiexecution = delaiexecution;
        return this;
    }

    public void setDelaiexecution(Integer delaiexecution) {
        this.delaiexecution = delaiexecution;
    }

    public String getObjet() {
        return objet;
    }

    public SigRealisation objet(String objet) {
        this.objet = objet;
        return this;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public SigRealisation montant(BigDecimal montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Integer getExamenDncmp() {
        return examenDncmp;
    }

    public SigRealisation examenDncmp(Integer examenDncmp) {
        this.examenDncmp = examenDncmp;
        return this;
    }

    public void setExamenDncmp(Integer examenDncmp) {
        this.examenDncmp = examenDncmp;
    }

    public Integer getExamenPtf() {
        return examenPtf;
    }

    public SigRealisation examenPtf(Integer examenPtf) {
        this.examenPtf = examenPtf;
        return this;
    }

    public void setExamenPtf(Integer examenPtf) {
        this.examenPtf = examenPtf;
    }

    public String getChapitreImputation() {
        return chapitreImputation;
    }

    public SigRealisation chapitreImputation(String chapitreImputation) {
        this.chapitreImputation = chapitreImputation;
        return this;
    }

    public void setChapitreImputation(String chapitreImputation) {
        this.chapitreImputation = chapitreImputation;
    }

    public String getAutorisationEngagement() {
        return autorisationEngagement;
    }

    public SigRealisation autorisationEngagement(String autorisationEngagement) {
        this.autorisationEngagement = autorisationEngagement;
        return this;
    }

    public void setAutorisationEngagement(String autorisationEngagement) {
        this.autorisationEngagement = autorisationEngagement;
    }

    public LocalDate getDateReceptionDossier() {
        return dateReceptionDossier;
    }

    public SigRealisation dateReceptionDossier(LocalDate dateReceptionDossier) {
        this.dateReceptionDossier = dateReceptionDossier;
        return this;
    }

    public void setDateReceptionDossier(LocalDate dateReceptionDossier) {
        this.dateReceptionDossier = dateReceptionDossier;
    }

    public LocalDate getDateNonObjection() {
        return dateNonObjection;
    }

    public SigRealisation dateNonObjection(LocalDate dateNonObjection) {
        this.dateNonObjection = dateNonObjection;
        return this;
    }

    public void setDateNonObjection(LocalDate dateNonObjection) {
        this.dateNonObjection = dateNonObjection;
    }

    public LocalDate getDateAutorisation() {
        return dateAutorisation;
    }

    public SigRealisation dateAutorisation(LocalDate dateAutorisation) {
        this.dateAutorisation = dateAutorisation;
        return this;
    }

    public void setDateAutorisation(LocalDate dateAutorisation) {
        this.dateAutorisation = dateAutorisation;
    }

    public LocalDate getDateRecepNonObjection() {
        return dateRecepNonObjection;
    }

    public SigRealisation dateRecepNonObjection(LocalDate dateRecepNonObjection) {
        this.dateRecepNonObjection = dateRecepNonObjection;
        return this;
    }

    public void setDateRecepNonObjection(LocalDate dateRecepNonObjection) {
        this.dateRecepNonObjection = dateRecepNonObjection;
    }

    public LocalDate getDateRecepDossCorrige() {
        return dateRecepDossCorrige;
    }

    public SigRealisation dateRecepDossCorrige(LocalDate dateRecepDossCorrige) {
        this.dateRecepDossCorrige = dateRecepDossCorrige;
        return this;
    }

    public void setDateRecepDossCorrige(LocalDate dateRecepDossCorrige) {
        this.dateRecepDossCorrige = dateRecepDossCorrige;
    }

    public LocalDate getDatePubParPrmp() {
        return datePubParPrmp;
    }

    public SigRealisation datePubParPrmp(LocalDate datePubParPrmp) {
        this.datePubParPrmp = datePubParPrmp;
        return this;
    }

    public void setDatePubParPrmp(LocalDate datePubParPrmp) {
        this.datePubParPrmp = datePubParPrmp;
    }

    public LocalDate getDateOuverturePlis() {
        return dateOuverturePlis;
    }

    public SigRealisation dateOuverturePlis(LocalDate dateOuverturePlis) {
        this.dateOuverturePlis = dateOuverturePlis;
        return this;
    }

    public void setDateOuverturePlis(LocalDate dateOuverturePlis) {
        this.dateOuverturePlis = dateOuverturePlis;
    }

    public LocalDate getDateRecepNonObjectOcmp() {
        return dateRecepNonObjectOcmp;
    }

    public SigRealisation dateRecepNonObjectOcmp(LocalDate dateRecepNonObjectOcmp) {
        this.dateRecepNonObjectOcmp = dateRecepNonObjectOcmp;
        return this;
    }

    public void setDateRecepNonObjectOcmp(LocalDate dateRecepNonObjectOcmp) {
        this.dateRecepNonObjectOcmp = dateRecepNonObjectOcmp;
    }

    public LocalDate getDateRecepRapportEva() {
        return dateRecepRapportEva;
    }

    public SigRealisation dateRecepRapportEva(LocalDate dateRecepRapportEva) {
        this.dateRecepRapportEva = dateRecepRapportEva;
        return this;
    }

    public void setDateRecepRapportEva(LocalDate dateRecepRapportEva) {
        this.dateRecepRapportEva = dateRecepRapportEva;
    }

    public LocalDate getDateRecepNonObjectPtf() {
        return dateRecepNonObjectPtf;
    }

    public SigRealisation dateRecepNonObjectPtf(LocalDate dateRecepNonObjectPtf) {
        this.dateRecepNonObjectPtf = dateRecepNonObjectPtf;
        return this;
    }

    public void setDateRecepNonObjectPtf(LocalDate dateRecepNonObjectPtf) {
        this.dateRecepNonObjectPtf = dateRecepNonObjectPtf;
    }

    public LocalDate getDateExamenJuridique() {
        return dateExamenJuridique;
    }

    public SigRealisation dateExamenJuridique(LocalDate dateExamenJuridique) {
        this.dateExamenJuridique = dateExamenJuridique;
        return this;
    }

    public void setDateExamenJuridique(LocalDate dateExamenJuridique) {
        this.dateExamenJuridique = dateExamenJuridique;
    }

    public LocalDate getDateNotifContrat() {
        return dateNotifContrat;
    }

    public SigRealisation dateNotifContrat(LocalDate dateNotifContrat) {
        this.dateNotifContrat = dateNotifContrat;
        return this;
    }

    public void setDateNotifContrat(LocalDate dateNotifContrat) {
        this.dateNotifContrat = dateNotifContrat;
    }

    public LocalDate getDateApprobationContrat() {
        return dateApprobationContrat;
    }

    public SigRealisation dateApprobationContrat(LocalDate dateApprobationContrat) {
        this.dateApprobationContrat = dateApprobationContrat;
        return this;
    }

    public void setDateApprobationContrat(LocalDate dateApprobationContrat) {
        this.dateApprobationContrat = dateApprobationContrat;
    }

    public PlanPassation getPlanPassation() {
        return planPassation;
    }

    public SigRealisation planPassation(PlanPassation planPassation) {
        this.planPassation = planPassation;
        return this;
    }

    public void setPlanPassation(PlanPassation planPassation) {
        this.planPassation = planPassation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SigRealisation)) {
            return false;
        }
        return id != null && id.equals(((SigRealisation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SigRealisation{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", dateAttribution='" + getDateAttribution() + "'" +
            ", delaiexecution=" + getDelaiexecution() +
            ", objet='" + getObjet() + "'" +
            ", montant=" + getMontant() +
            ", examenDncmp=" + getExamenDncmp() +
            ", examenPtf=" + getExamenPtf() +
            ", chapitreImputation='" + getChapitreImputation() + "'" +
            ", autorisationEngagement='" + getAutorisationEngagement() + "'" +
            ", dateReceptionDossier='" + getDateReceptionDossier() + "'" +
            ", dateNonObjection='" + getDateNonObjection() + "'" +
            ", dateAutorisation='" + getDateAutorisation() + "'" +
            ", dateRecepNonObjection='" + getDateRecepNonObjection() + "'" +
            ", dateRecepDossCorrige='" + getDateRecepDossCorrige() + "'" +
            ", datePubParPrmp='" + getDatePubParPrmp() + "'" +
            ", dateOuverturePlis='" + getDateOuverturePlis() + "'" +
            ", dateRecepNonObjectOcmp='" + getDateRecepNonObjectOcmp() + "'" +
            ", dateRecepRapportEva='" + getDateRecepRapportEva() + "'" +
            ", dateRecepNonObjectPtf='" + getDateRecepNonObjectPtf() + "'" +
            ", dateExamenJuridique='" + getDateExamenJuridique() + "'" +
            ", dateNotifContrat='" + getDateNotifContrat() + "'" +
            ", dateApprobationContrat='" + getDateApprobationContrat() + "'" +
            "}";
    }

    public SigRealisation(Long id, @NotNull String libelle, LocalDate dateAttribution, Integer delaiexecution, @NotNull String objet, BigDecimal montant, Integer examenDncmp, Integer examenPtf, String chapitreImputation, String autorisationEngagement, LocalDate dateReceptionDossier, LocalDate dateNonObjection, LocalDate dateAutorisation, LocalDate dateRecepNonObjection, LocalDate dateRecepDossCorrige, LocalDate datePubParPrmp, LocalDate dateOuverturePlis, LocalDate dateRecepNonObjectOcmp, LocalDate dateRecepRapportEva, LocalDate dateRecepNonObjectPtf, LocalDate dateExamenJuridique, LocalDate dateNotifContrat, LocalDate dateApprobationContrat, PlanPassation planPassation) {
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
    }

}
