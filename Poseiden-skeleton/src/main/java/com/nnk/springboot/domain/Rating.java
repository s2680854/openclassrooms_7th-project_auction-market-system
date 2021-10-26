package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Moody's Rating is mandatory")
    @Size(min=50)
    private String moodysRating;
    @NotBlank(message = "Standard & Poor's Rating is mandatory")
    @Size(min=50)
    private String sandPRating;
    @NotBlank(message = "Fitch Rating is mandatory")
    @Size(min=50)
    private String fitchRating;
    @NotBlank(message = "Order Number is mandatory")
    @Size(min=0)
    private int orderNumber;

    public Rating(@NotBlank(message = "Moody's Rating is mandatory") String moodysRating,
                  @NotBlank(message = "Standard & Poor's Rating is mandatory") String sandPRating,
                  @NotBlank(message = "Fitch Rating is mandatory") String fitchRating,
                  @NotBlank(message = "Order Number is mandatory") int orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    public Rating() {  }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public String getSandPRating() {
        return sandPRating;
    }

    public void setSandPRating(String sandPRating) {
        this.sandPRating = sandPRating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", moodysRating='" + moodysRating + '\'' +
                ", sandPRating='" + sandPRating + '\'' +
                ", fitchRating='" + fitchRating + '\'' +
                ", orderNumber=" + orderNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return orderNumber == rating.orderNumber && Objects.equals(moodysRating, rating.moodysRating) && Objects.equals(sandPRating, rating.sandPRating) && Objects.equals(fitchRating, rating.fitchRating);
    }
}
