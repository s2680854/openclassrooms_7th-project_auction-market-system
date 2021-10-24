package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Objects;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return Objects.equals(name, rule.name) && Objects.equals(description, rule.description) && Objects.equals(json, rule.json) && Objects.equals(template, rule.template) && Objects.equals(sqlStr, rule.sqlStr) && Objects.equals(sqlPart, rule.sqlPart);
    }
}
