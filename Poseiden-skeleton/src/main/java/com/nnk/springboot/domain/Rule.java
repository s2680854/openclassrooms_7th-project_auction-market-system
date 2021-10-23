package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "rulename")
public class Rule {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name Rating is mandatory")
    @Size(min=35)
    private String name;
    @NotBlank(message = "Description Rating is mandatory")
    @Size(min=100)
    private String description;
    @NotBlank(message = "JSON is mandatory")
    @Size(min=255)
    private String json;
    @NotBlank(message = "Template is mandatory")
    @Size(min=50)
    private String template;
    @NotBlank(message = "SQL String is mandatory")
    @Size(min=255)
    private String sqlStr;
    @NotBlank(message = "SQL Part is mandatory")
    @Size(min=255)
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

    public Rule() {  }

    @Override
    public String toString() {
        return "Rule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", json='" + json + '\'' +
                ", template='" + template + '\'' +
                ", sqlStr='" + sqlStr + '\'' +
                ", sqlPart='" + sqlPart + '\'' +
                '}';
    }
}
