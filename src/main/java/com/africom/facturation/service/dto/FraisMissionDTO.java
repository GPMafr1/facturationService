package com.africom.facturation.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.africom.facturation.domain.FraisMission} entity.
 */
public class FraisMissionDTO implements Serializable {
    
    private Long id;

    @NotNull
    private LocalDate dateDebut;

    @NotNull
    private LocalDate dateFin;

    @NotNull
    private BigDecimal montantTotal;

    @NotNull
    private BigDecimal avanceRecue;

    @NotNull
    private BigDecimal netAPayer;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public BigDecimal getAvanceRecue() {
        return avanceRecue;
    }

    public void setAvanceRecue(BigDecimal avanceRecue) {
        this.avanceRecue = avanceRecue;
    }

    public BigDecimal getNetAPayer() {
        return netAPayer;
    }

    public void setNetAPayer(BigDecimal netAPayer) {
        this.netAPayer = netAPayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FraisMissionDTO)) {
            return false;
        }

        return id != null && id.equals(((FraisMissionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FraisMissionDTO{" +
            "id=" + getId() +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", montantTotal=" + getMontantTotal() +
            ", avanceRecue=" + getAvanceRecue() +
            ", netAPayer=" + getNetAPayer() +
            "}";
    }
}
