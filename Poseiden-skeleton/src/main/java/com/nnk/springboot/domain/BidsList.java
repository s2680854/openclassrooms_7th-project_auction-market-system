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
    private Timestamp creationDate;
    @Size(max=35)
    private String revisionName;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public double getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(double askQuantity) {
        this.askQuantity = askQuantity;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getBidsListDate() {
        return bidsListDate;
    }

    public void setBidsListDate(Timestamp bidsListDate) {
        this.bidsListDate = bidsListDate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getRevisionName() {
        return revisionName;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    public Timestamp getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Timestamp revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return "BidsList{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", type='" + type + '\'' +
                ", bidQuantity=" + bidQuantity +
                ", askQuantity=" + askQuantity +
                ", bid=" + bid +
                ", ask=" + ask +
                ", benchmark='" + benchmark + '\'' +
                ", bidsListDate=" + bidsListDate +
                ", commentary='" + commentary + '\'' +
                ", security='" + security + '\'' +
                ", status='" + status + '\'' +
                ", trader='" + trader + '\'' +
                ", book='" + book + '\'' +
                ", creationName='" + creationName + '\'' +
                ", creationDate=" + creationDate +
                ", revisionName='" + revisionName + '\'' +
                ", revisionDate=" + revisionDate +
                ", dealName='" + dealName + '\'' +
                ", dealType='" + dealType + '\'' +
                ", sourceListId='" + sourceListId + '\'' +
                ", side='" + side + '\'' +
                '}';
    }
}
