package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
}
