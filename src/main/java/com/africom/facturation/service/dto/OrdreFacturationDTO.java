package com.africom.facturation.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.africom.facturation.domain.OrdreFacturation} entity.
 */
public class OrdreFacturationDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String devis;

    @NotNull
    private String bonDeCommande;

    @NotNull
    private String numeroFacture;

    @NotNull
    private BigDecimal montantFacture;

    @NotNull
    private LocalDate dateFacture;

    @NotNull
    private LocalDate dateEcheance;

    @NotNull
    private LocalDate dateDecharge;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevis() {
        return devis;
    }

    public void setDevis(String devis) {
        this.devis = devis;
    }

    public String getBonDeCommande() {
        return bonDeCommande;
    }

    public void setBonDeCommande(String bonDeCommande) {
        this.bonDeCommande = bonDeCommande;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public BigDecimal getMontantFacture() {
        return montantFacture;
    }

    public void setMontantFacture(BigDecimal montantFacture) {
        this.montantFacture = montantFacture;
    }

    public LocalDate getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(LocalDate dateFacture) {
        this.dateFacture = dateFacture;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public LocalDate getDateDecharge() {
        return dateDecharge;
    }

    public void setDateDecharge(LocalDate dateDecharge) {
        this.dateDecharge = dateDecharge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrdreFacturationDTO)) {
            return false;
        }

        return id != null && id.equals(((OrdreFacturationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrdreFacturationDTO{" +
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
