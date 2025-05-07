package com.africom.facturation.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A OrdreFacturation.
 */
@Entity
@Table(name = "ordre_facturation")
public class OrdreFacturation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "devis", nullable = false)
    private String devis;

    @NotNull
    @Column(name = "bon_de_commande", nullable = false)
    private String bonDeCommande;

    @NotNull
    @Column(name = "numero_facture", nullable = false)
    private String numeroFacture;

    @NotNull
    @Column(name = "montant_facture", precision = 21, scale = 2, nullable = false)
    private BigDecimal montantFacture;

    @NotNull
    @Column(name = "date_facture", nullable = false)
    private LocalDate dateFacture;

    @NotNull
    @Column(name = "date_echeance", nullable = false)
    private LocalDate dateEcheance;

    @NotNull
    @Column(name = "date_decharge", nullable = false)
    private LocalDate dateDecharge;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevis() {
        return devis;
    }

    public OrdreFacturation devis(String devis) {
        this.devis = devis;
        return this;
    }

    public void setDevis(String devis) {
        this.devis = devis;
    }

    public String getBonDeCommande() {
        return bonDeCommande;
    }

    public OrdreFacturation bonDeCommande(String bonDeCommande) {
        this.bonDeCommande = bonDeCommande;
        return this;
    }

    public void setBonDeCommande(String bonDeCommande) {
        this.bonDeCommande = bonDeCommande;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public OrdreFacturation numeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
        return this;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public BigDecimal getMontantFacture() {
        return montantFacture;
    }

    public OrdreFacturation montantFacture(BigDecimal montantFacture) {
        this.montantFacture = montantFacture;
        return this;
    }

    public void setMontantFacture(BigDecimal montantFacture) {
        this.montantFacture = montantFacture;
    }

    public LocalDate getDateFacture() {
        return dateFacture;
    }

    public OrdreFacturation dateFacture(LocalDate dateFacture) {
        this.dateFacture = dateFacture;
        return this;
    }

    public void setDateFacture(LocalDate dateFacture) {
        this.dateFacture = dateFacture;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public OrdreFacturation dateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
        return this;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public LocalDate getDateDecharge() {
        return dateDecharge;
    }

    public OrdreFacturation dateDecharge(LocalDate dateDecharge) {
        this.dateDecharge = dateDecharge;
        return this;
    }

    public void setDateDecharge(LocalDate dateDecharge) {
        this.dateDecharge = dateDecharge;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrdreFacturation)) {
            return false;
        }
        return id != null && id.equals(((OrdreFacturation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrdreFacturation{" +
            "id=" + getId() +
            ", devis='" + getDevis() + "'" +
            ", bonDeCommande='" + getBonDeCommande() + "'" +
            ", numeroFacture='" + getNumeroFacture() + "'" +
            ", montantFacture=" + getMontantFacture() +
            ", dateFacture='" + getDateFacture() + "'" +
            ", dateEcheance='" + getDateEcheance() + "'" +
            ", dateDecharge='" + getDateDecharge() + "'" +
            "}";
    }
}
