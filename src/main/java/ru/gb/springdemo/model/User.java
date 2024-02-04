package ru.gb.springdemo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

  @Id
  private Long id;

  @Column(name = "login")
  private String login;

  @Column(name = "password")
  private String password;

 // @Column(name = "role")
  //private String role;
 @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
 @JoinTable (name = "user_role"
         ,joinColumns = @JoinColumn(name = "user_id")
         ,inverseJoinColumns = @JoinColumn(name = "role_id"))
 private List<Role> roles ;

}
