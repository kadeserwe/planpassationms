package sn.ssi.sigmap.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A SiPlanPassation.
 */
@Entity
@Table(name = "si_plan_passation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SiPlanPassation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "numero_plan", nullable = false)
    private String numeroPlan;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "commentaire")
    private String commentaire;

    @NotNull
    @Column(name = "annee", nullable = false)
    private Integer annee;

    @NotNull
    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;

    @Column(name = "date_mise_validation")
    private LocalDate dateMiseValidation;

    @Column(name = "date_validation")
    private LocalDate dateValidation;

    @Column(name = "statut")
    private String statut;

    @Column(name = "commentaire_mise_en_validation_ac")
    private String commentaireMiseEnValidationAC;

    @Column(name = "reference_mise_validation_ac")
    private String referenceMiseValidationAC;

    @Lob
    @Column(name = "fichier_mise_validation_ac")
    private byte[] fichierMiseValidationAC;

    @Column(name = "fichier_mise_validation_ac_content_type")
    private String fichierMiseValidationACContentType;

    @Column(name = "date_mise_en_validation_ccmp")
    private LocalDate dateMiseEnValidationCcmp;

    @Lob
    @Column(name = "fichier_mise_validation_ccmp")
    private byte[] fichierMiseValidationCcmp;

    @Column(name = "fichier_mise_validation_ccmp_content_type")
    private String fichierMiseValidationCcmpContentType;

    @Column(name = "reference_mise_validation_ccmp")
    private String referenceMiseValidationCcmp;

    @Column(name = "date_validation_1")
    private LocalDate dateValidation1;

    @Column(name = "commentaire_validation")
    private String commentaireValidation;

    @Column(name = "reference_validation")
    private String referenceValidation;

    @Lob
    @Column(name = "fichier_validation")
    private byte[] fichierValidation;

    @Column(name = "fichier_validation_content_type")
    private String fichierValidationContentType;

    @Column(name = "date_validation_2")
    private LocalDate dateValidation2;

    @Column(name = "date_rejet")
    private LocalDate dateRejet;

    @Column(name = "date_publication")
    private LocalDate datePublication;

    @Column(name = "commentaire_publication")
    private String commentairePublication;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroPlan() {
        return numeroPlan;
    }

    public SiPlanPassation numeroPlan(String numeroPlan) {
        this.numeroPlan = numeroPlan;
        return this;
    }

    public void setNumeroPlan(String numeroPlan) {
        this.numeroPlan = numeroPlan;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public SiPlanPassation dateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public SiPlanPassation dateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public SiPlanPassation commentaire(String commentaire) {
        this.commentaire = commentaire;
        return this;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Integer getAnnee() {
        return annee;
    }

    public SiPlanPassation annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public SiPlanPassation dateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateMiseValidation() {
        return dateMiseValidation;
    }

    public SiPlanPassation dateMiseValidation(LocalDate dateMiseValidation) {
        this.dateMiseValidation = dateMiseValidation;
        return this;
    }

    public void setDateMiseValidation(LocalDate dateMiseValidation) {
        this.dateMiseValidation = dateMiseValidation;
    }

    public LocalDate getDateValidation() {
        return dateValidation;
    }

    public SiPlanPassation dateValidation(LocalDate dateValidation) {
        this.dateValidation = dateValidation;
        return this;
    }

    public void setDateValidation(LocalDate dateValidation) {
        this.dateValidation = dateValidation;
    }

    public String getStatut() {
        return statut;
    }

    public SiPlanPassation statut(String statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getCommentaireMiseEnValidationAC() {
        return commentaireMiseEnValidationAC;
    }

    public SiPlanPassation commentaireMiseEnValidationAC(String commentaireMiseEnValidationAC) {
        this.commentaireMiseEnValidationAC = commentaireMiseEnValidationAC;
        return this;
    }

    public void setCommentaireMiseEnValidationAC(String commentaireMiseEnValidationAC) {
        this.commentaireMiseEnValidationAC = commentaireMiseEnValidationAC;
    }

    public String getReferenceMiseValidationAC() {
        return referenceMiseValidationAC;
    }

    public SiPlanPassation referenceMiseValidationAC(String referenceMiseValidationAC) {
        this.referenceMiseValidationAC = referenceMiseValidationAC;
        return this;
    }

    public void setReferenceMiseValidationAC(String referenceMiseValidationAC) {
        this.referenceMiseValidationAC = referenceMiseValidationAC;
    }

    public byte[] getFichierMiseValidationAC() {
        return fichierMiseValidationAC;
    }

    public SiPlanPassation fichierMiseValidationAC(byte[] fichierMiseValidationAC) {
        this.fichierMiseValidationAC = fichierMiseValidationAC;
        return this;
    }

    public void setFichierMiseValidationAC(byte[] fichierMiseValidationAC) {
        this.fichierMiseValidationAC = fichierMiseValidationAC;
    }

    public String getFichierMiseValidationACContentType() {
        return fichierMiseValidationACContentType;
    }

    public SiPlanPassation fichierMiseValidationACContentType(String fichierMiseValidationACContentType) {
        this.fichierMiseValidationACContentType = fichierMiseValidationACContentType;
        return this;
    }

    public void setFichierMiseValidationACContentType(String fichierMiseValidationACContentType) {
        this.fichierMiseValidationACContentType = fichierMiseValidationACContentType;
    }

    public LocalDate getDateMiseEnValidationCcmp() {
        return dateMiseEnValidationCcmp;
    }

    public SiPlanPassation dateMiseEnValidationCcmp(LocalDate dateMiseEnValidationCcmp) {
        this.dateMiseEnValidationCcmp = dateMiseEnValidationCcmp;
        return this;
    }

    public void setDateMiseEnValidationCcmp(LocalDate dateMiseEnValidationCcmp) {
        this.dateMiseEnValidationCcmp = dateMiseEnValidationCcmp;
    }

    public byte[] getFichierMiseValidationCcmp() {
        return fichierMiseValidationCcmp;
    }

    public SiPlanPassation fichierMiseValidationCcmp(byte[] fichierMiseValidationCcmp) {
        this.fichierMiseValidationCcmp = fichierMiseValidationCcmp;
        return this;
    }

    public void setFichierMiseValidationCcmp(byte[] fichierMiseValidationCcmp) {
        this.fichierMiseValidationCcmp = fichierMiseValidationCcmp;
    }

    public String getFichierMiseValidationCcmpContentType() {
        return fichierMiseValidationCcmpContentType;
    }

    public SiPlanPassation fichierMiseValidationCcmpContentType(String fichierMiseValidationCcmpContentType) {
        this.fichierMiseValidationCcmpContentType = fichierMiseValidationCcmpContentType;
        return this;
    }

    public void setFichierMiseValidationCcmpContentType(String fichierMiseValidationCcmpContentType) {
        this.fichierMiseValidationCcmpContentType = fichierMiseValidationCcmpContentType;
    }

    public String getReferenceMiseValidationCcmp() {
        return referenceMiseValidationCcmp;
    }

    public SiPlanPassation referenceMiseValidationCcmp(String referenceMiseValidationCcmp) {
        this.referenceMiseValidationCcmp = referenceMiseValidationCcmp;
        return this;
    }

    public void setReferenceMiseValidationCcmp(String referenceMiseValidationCcmp) {
        this.referenceMiseValidationCcmp = referenceMiseValidationCcmp;
    }

    public LocalDate getDateValidation1() {
        return dateValidation1;
    }

    public SiPlanPassation dateValidation1(LocalDate dateValidation1) {
        this.dateValidation1 = dateValidation1;
        return this;
    }

    public void setDateValidation1(LocalDate dateValidation1) {
        this.dateValidation1 = dateValidation1;
    }

    public String getCommentaireValidation() {
        return commentaireValidation;
    }

    public SiPlanPassation commentaireValidation(String commentaireValidation) {
        this.commentaireValidation = commentaireValidation;
        return this;
    }

    public void setCommentaireValidation(String commentaireValidation) {
        this.commentaireValidation = commentaireValidation;
    }

    public String getReferenceValidation() {
        return referenceValidation;
    }

    public SiPlanPassation referenceValidation(String referenceValidation) {
        this.referenceValidation = referenceValidation;
        return this;
    }

    public void setReferenceValidation(String referenceValidation) {
        this.referenceValidation = referenceValidation;
    }

    public byte[] getFichierValidation() {
        return fichierValidation;
    }

    public SiPlanPassation fichierValidation(byte[] fichierValidation) {
        this.fichierValidation = fichierValidation;
        return this;
    }

    public void setFichierValidation(byte[] fichierValidation) {
        this.fichierValidation = fichierValidation;
    }

    public String getFichierValidationContentType() {
        return fichierValidationContentType;
    }

    public SiPlanPassation fichierValidationContentType(String fichierValidationContentType) {
        this.fichierValidationContentType = fichierValidationContentType;
        return this;
    }

    public void setFichierValidationContentType(String fichierValidationContentType) {
        this.fichierValidationContentType = fichierValidationContentType;
    }

    public LocalDate getDateValidation2() {
        return dateValidation2;
    }

    public SiPlanPassation dateValidation2(LocalDate dateValidation2) {
        this.dateValidation2 = dateValidation2;
        return this;
    }

    public void setDateValidation2(LocalDate dateValidation2) {
        this.dateValidation2 = dateValidation2;
    }

    public LocalDate getDateRejet() {
        return dateRejet;
    }

    public SiPlanPassation dateRejet(LocalDate dateRejet) {
        this.dateRejet = dateRejet;
        return this;
    }

    public void setDateRejet(LocalDate dateRejet) {
        this.dateRejet = dateRejet;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public SiPlanPassation datePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
        return this;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public String getCommentairePublication() {
        return commentairePublication;
    }

    public SiPlanPassation commentairePublication(String commentairePublication) {
        this.commentairePublication = commentairePublication;
        return this;
    }

    public void setCommentairePublication(String commentairePublication) {
        this.commentairePublication = commentairePublication;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SiPlanPassation)) {
            return false;
        }
        return id != null && id.equals(((SiPlanPassation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SiPlanPassation{" +
            "id=" + getId() +
            ", numeroPlan='" + getNumeroPlan() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", commentaire='" + getCommentaire() + "'" +
            ", annee=" + getAnnee() +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateMiseValidation='" + getDateMiseValidation() + "'" +
            ", dateValidation='" + getDateValidation() + "'" +
            ", statut='" + getStatut() + "'" +
            ", commentaireMiseEnValidationAC='" + getCommentaireMiseEnValidationAC() + "'" +
            ", referenceMiseValidationAC='" + getReferenceMiseValidationAC() + "'" +
            ", fichierMiseValidationAC='" + getFichierMiseValidationAC() + "'" +
            ", fichierMiseValidationACContentType='" + getFichierMiseValidationACContentType() + "'" +
            ", dateMiseEnValidationCcmp='" + getDateMiseEnValidationCcmp() + "'" +
            ", fichierMiseValidationCcmp='" + getFichierMiseValidationCcmp() + "'" +
            ", fichierMiseValidationCcmpContentType='" + getFichierMiseValidationCcmpContentType() + "'" +
            ", referenceMiseValidationCcmp='" + getReferenceMiseValidationCcmp() + "'" +
            ", dateValidation1='" + getDateValidation1() + "'" +
            ", commentaireValidation='" + getCommentaireValidation() + "'" +
            ", referenceValidation='" + getReferenceValidation() + "'" +
            ", fichierValidation='" + getFichierValidation() + "'" +
            ", fichierValidationContentType='" + getFichierValidationContentType() + "'" +
            ", dateValidation2='" + getDateValidation2() + "'" +
            ", dateRejet='" + getDateRejet() + "'" +
            ", datePublication='" + getDatePublication() + "'" +
            ", commentairePublication='" + getCommentairePublication() + "'" +
            "}";
    }
}
