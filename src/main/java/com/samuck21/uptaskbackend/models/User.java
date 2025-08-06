package com.samuck21.uptaskbackend.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name ="users")
public class User implements UserDetails {
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
    private Set<UserHasRoles> userHasRoles = new HashSet<>();

    public User(){

    }
    @PreUpdate
    public void onUpdate(){
        this.updateAt = LocalDateTime.now();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
