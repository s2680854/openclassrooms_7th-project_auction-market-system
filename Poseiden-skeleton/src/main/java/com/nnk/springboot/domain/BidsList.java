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
    private double bidQuantity;

    private double askQuantity;
    private double bid;
    private double ask;
    private String benchmark;
    private Timestamp bidsListDate;
    private String commentary;
    private String security;
    private String status;
    private String trader;
    private String book;
    private String creationName;
    private Timestamp creationDate;
    private String revisionName;
    private Timestamp revisionDate;
    private String dealName;
    private String dealType;
    private String sourceListId;
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
