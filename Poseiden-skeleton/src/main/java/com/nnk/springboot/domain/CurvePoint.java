package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
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

    public CurvePoint() {  };
}
