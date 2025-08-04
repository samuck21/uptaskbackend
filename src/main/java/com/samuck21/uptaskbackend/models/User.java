package com.samuck21.uptaskbackend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name ="users")
public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     @Column(length = 255,nullable = false)
    private String name;
     @Column(length = 255, nullable = false)
    private String lastname;
     @Column(length = 255, nullable = false,unique = true)
    private String email;
     @Column(length = 255,nullable = true)
    private  String image;
    @Column(length = 255,nullable = false)
    private  String password;
    @Column(length = 255, nullable = false)
    private String phone;
     @Column(name ="notification_token",length = 255,nullable = true)
    private String notificationToken;

    @Column(name="create_at",nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();
    @Column(name="update_at",nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<UserHasRoles> roles = new HashSet<>();

    public User(){

    }
    @PreUpdate
    public void onUpdate(){
        this.updateAt = LocalDateTime.now();
    }


}
