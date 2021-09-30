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
    @Size(min=50)
    @Email
    private String username;
    @NotBlank(message = "Password is mandatory")
    @Size(min=8)
    private String password;
    @NotBlank(message = "FullName is mandatory")
    @Size(min=35)
    private String fullname;
    @NotBlank(message = "Role is mandatory")
    @Size(min=35)
    private String role;

    public User() {  };
}
