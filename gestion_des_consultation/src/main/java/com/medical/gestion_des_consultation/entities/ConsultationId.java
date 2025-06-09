package com.medical.gestion_des_consultation.entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class ConsultationId implements Serializable {

    private Long numP;
    private Long numM;
    private LocalDate dateC;

    public ConsultationId() {}

    public ConsultationId(Long numP, Long numM, LocalDate dateC) {
        this.numP = numP;
        this.numM = numM;
        this.dateC = dateC;
    }

    // Getters & Setters
    public Long getNumP() { return numP; }
    public void setNumP(Long numP) { this.numP = numP; }

    public Long getNumM() { return numM; }
    public void setNumM(Long numM) { this.numM = numM; }

    public LocalDate getDateC() { return dateC; }
    public void setDateC(LocalDate dateC) { this.dateC = dateC; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsultationId that)) return false;
        return Objects.equals(numP, that.numP) &&
                Objects.equals(numM, that.numM) &&
                Objects.equals(dateC, that.dateC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numP, numM, dateC);
    }
}
