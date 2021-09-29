package com.nnk.springboot.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer curveId;
    private Timestamp asOfDate;
    private double term;
    private double value;
    private Timestamp creationDate;

    public CurvePoint(Integer curveId, double term, double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }
}
