package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Username is mandatory")
    @Size(max=50)
    @Email
    private String username;
    @NotBlank(message = "Password is mandatory")
    @Size(min=8, max=72)
    private String password;
    @NotBlank(message = "FullName is mandatory")
    @Size(max=100)
    private String fullname;
    @NotBlank(message = "Role is mandatory")
    @Size(max=35)
    private String role;

    public User() {  };
}
