package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "ID is mandatory")
    private int curveId;
    @NotBlank(message = "Term is mandatory")
    private double term;
    @NotBlank(message = "Value is mandatory")
    private double value;

    private Timestamp asOfDate;
    private Timestamp creationDate;

    public CurvePoint(@NotBlank(message = "ID is mandatory") int curveId,
                      @NotBlank(message = "Term is mandatory") double term,
                      @NotBlank(message = "Value is mandatory") double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    public CurvePoint() {  }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCurveId() {
        return curveId;
    }

    public void setCurveId(int curveId) {
        this.curveId = curveId;
    }

    public double getTerm() {
        return term;
    }

    public void setTerm(double term) {
        this.term = term;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Timestamp getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = asOfDate;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "CurvePoint{" +
                "id=" + id +
                ", curveId=" + curveId +
                ", term=" + term +
                ", value=" + value +
                ", asOfDate=" + asOfDate +
                ", creationDate=" + creationDate +
                '}';
    }
}
