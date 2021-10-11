package com.nnk.springboot.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "bidlist")
public class BidsList {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "bidlist_id")
    private Long id;

    @NotBlank(message = "Account is mandatory")
    @Size(max=50)
    @Email
    private String account;
    @NotBlank(message = "Type is mandatory")
    @Size(max=35)
    private String type;
    @NotBlank(message = "Quantity is mandatory")
    @Size(min=0)
    private double bidQuantity;

    @Size(min=0)
    private double askQuantity;
    @Size(min=0)
    private double bid;
    @Size(min=0)
    private double ask;
    @Size(max=35)
    private String benchmark;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp bidsListDate;
    @Size(max=35)
    private String commentary;
    @Size(max=35)
    private String security;
    @Size(max=35)
    private String status;
    @Size(max=35)
    private String trader;
    @Size(max=35)
    private String book;
    @Size(max=35)
    private String creationName;
    @Size(max=35)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp creationDate;
    @Size(max=35)
    private String revisionName;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp revisionDate;
    @Size(max=35)
    private String dealName;
    @Size(max=35)
    private String dealType;
    @Size(max=35)
    private String sourceListId;
    @Size(max=35)
    private String side;

    public BidsList(@NotBlank(message = "Account is mandatory") String account,
                    @NotBlank(message = "Type is mandatory") String type,
                    @NotBlank(message = "Quantity is mandatory") double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    public BidsList() {  }
}
