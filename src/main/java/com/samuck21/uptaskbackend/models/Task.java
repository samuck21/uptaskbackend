package com.samuck21.uptaskbackend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 36, nullable = false)
    private String incharge;

    @Column(length = 255, nullable = false)
    private String personnel;

    @Column(name="start_data",length = 255,nullable = false)
    private String start_data;

    @Column(name="finish_data",length = 255,nullable = false)
    private String finish_data;

    @Column(length = 255, nullable = false)
    private String description;

    @Column(name="create_at",nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name="update_at",nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    public Task(){

    }
    @PreUpdate
    public void onUpdate(){
        this.updateAt = LocalDateTime.now();
    }

}
