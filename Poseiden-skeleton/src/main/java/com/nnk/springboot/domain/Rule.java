package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rule")
public class Rule {

    // TODO: Map columns in data table RULE with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;
    private String description;
    private String json;
    private String template;
    private String sqlStr;
    private String sqlPart;
}
