package com.codegym.demospringboot.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //id,user,pass,fullname,phone,email,avatar
//    @NotNull
    private String user_name;
    private String password;
    private String full_name;
    private String phone;
//    @NotNull
//    @Pattern(regexp ="\\w*@\\w*\\d*\\S*\\w*", message = "Email is abc@abc.abc")
    private String email;
    private String avatar;
    @ManyToOne
    private Role role;

}
