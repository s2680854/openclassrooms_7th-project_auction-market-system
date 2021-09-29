package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Moody's Rating is mandatory")
    private String moodysRating;
    @NotBlank(message = "Standard & Poor's Rating is mandatory")
    private String sandPRating;
    @NotBlank(message = "Fitch Rating is mandatory")
    private String fitchRating;
    @NotBlank(message = "Order Number is mandatory")
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

    public Rating() {  };
}
