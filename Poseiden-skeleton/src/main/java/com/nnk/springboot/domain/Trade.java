package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Objects;

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
    private Timestamp tradeDate;
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

    public Trade(@NotBlank(message = "Account is mandatory") String account,
                 @NotBlank(message = "Type is mandatory") String type) {
        this.account = account;
        this.type = type;
    }

    public Trade() {  }

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

    public double getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(double buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public double getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(double sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Timestamp tradeDate) {
        this.tradeDate = tradeDate;
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
        return "Trade{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", type='" + type + '\'' +
                ", buyQuantity=" + buyQuantity +
                ", sellQuantity=" + sellQuantity +
                ", buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                ", benchmark='" + benchmark + '\'' +
                ", tradeDate=" + tradeDate +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return Double.compare(trade.buyQuantity, buyQuantity) == 0 && Double.compare(trade.sellQuantity, sellQuantity) == 0 && Double.compare(trade.buyPrice, buyPrice) == 0 && Double.compare(trade.sellPrice, sellPrice) == 0 && Objects.equals(account, trade.account) && Objects.equals(type, trade.type) && Objects.equals(benchmark, trade.benchmark) && Objects.equals(tradeDate, trade.tradeDate) && Objects.equals(security, trade.security) && Objects.equals(status, trade.status) && Objects.equals(trader, trade.trader) && Objects.equals(book, trade.book) && Objects.equals(creationName, trade.creationName) && Objects.equals(creationDate, trade.creationDate) && Objects.equals(revisionName, trade.revisionName) && Objects.equals(revisionDate, trade.revisionDate) && Objects.equals(dealName, trade.dealName) && Objects.equals(dealType, trade.dealType) && Objects.equals(sourceListId, trade.sourceListId) && Objects.equals(side, trade.side);
    }
}
