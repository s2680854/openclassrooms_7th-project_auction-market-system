package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Account is mandatory")
    @Email
    private String account;
    @NotBlank(message = "Type is mandatory")
    @Size(min=35)
    private String type;

    @Size(min=0)
    private double buyQuantity;
    @Size(min=0)
    private double sellQuantity;
    @Size(min=0)
    private double buyPrice;
    @Size(min=0)
    private double sellPrice;
    private String benchmark;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp tradeDate;
    private String security;
    private String status;
    private String trader;
    private String book;
    private String creationName;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp creationDate;
    private String revisionName;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp revisionDate;
    private String dealName;
    private String dealType;
    private String sourceListId;
    private String side;

    public Trade(@NotBlank(message = "Account is mandatory") String account,
                 @NotBlank(message = "Type is mandatory") String type) {
        this.account = account;
        this.type = type;
    }

    public Trade() {  };
}
