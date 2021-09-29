package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "rule")
public class Rule {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name Rating is mandatory")
    private String name;
    @NotBlank(message = "Description Rating is mandatory")
    private String description;
    @NotBlank(message = "JSON is mandatory")
    private String json;
    @NotBlank(message = "Template is mandatory")
    private String template;
    @NotBlank(message = "SQL String is mandatory")
    private String sqlStr;
    @NotBlank(message = "SQL Part is mandatory")
    private String sqlPart;

    public Rule(@NotBlank(message = "Name Rating is mandatory") String name,
                @NotBlank(message = "Description Rating is mandatory") String description,
                @NotBlank(message = "JSON is mandatory") String json,
                @NotBlank(message = "Template is mandatory") String template,
                @NotBlank(message = "SQL String is mandatory") String sqlStr,
                @NotBlank(message = "SQL Part is mandatory") String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }

    public Rule() {  };
}
