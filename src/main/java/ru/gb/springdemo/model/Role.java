package ru.gb.springdemo.model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private Long id;

    @Column(name = "role")
    private String role;

    @ManyToMany (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable (name = "user_role"
                ,joinColumns = @JoinColumn(name = "role_id")
                ,inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

}
