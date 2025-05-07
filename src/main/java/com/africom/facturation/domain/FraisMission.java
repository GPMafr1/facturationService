package com.africom.facturation.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A FraisMission.
 */
@Entity
@Table(name = "frais_mission")
public class FraisMission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @NotNull
    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;

    @NotNull
    @Column(name = "montant_total", precision = 21, scale = 2, nullable = false)
    private BigDecimal montantTotal;

    @NotNull
    @Column(name = "avance_recue", precision = 21, scale = 2, nullable = false)
    private BigDecimal avanceRecue;

    @NotNull
    @Column(name = "net_a_payer", precision = 21, scale = 2, nullable = false)
    private BigDecimal netAPayer;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public FraisMission dateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public FraisMission dateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public FraisMission montantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
        return this;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public BigDecimal getAvanceRecue() {
        return avanceRecue;
    }

    public FraisMission avanceRecue(BigDecimal avanceRecue) {
        this.avanceRecue = avanceRecue;
        return this;
    }

    public void setAvanceRecue(BigDecimal avanceRecue) {
        this.avanceRecue = avanceRecue;
    }

    public BigDecimal getNetAPayer() {
        return netAPayer;
    }

    public FraisMission netAPayer(BigDecimal netAPayer) {
        this.netAPayer = netAPayer;
        return this;
    }

    public void setNetAPayer(BigDecimal netAPayer) {
        this.netAPayer = netAPayer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FraisMission)) {
            return false;
        }
        return id != null && id.equals(((FraisMission) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FraisMission{" +
            "id=" + getId() +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", montantTotal=" + getMontantTotal() +
            ", avanceRecue=" + getAvanceRecue() +
            ", netAPayer=" + getNetAPayer() +
            "}";
    }
}
