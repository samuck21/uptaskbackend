package com.samuck21.uptaskbackend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String description;

    @Column(length = 255, nullable = false)
    private String status;

    @Column(length = 255, nullable = false)
    private String model;

    @Column(length = 255, nullable = false)
    private String codemd;

    @Column(length = 255, nullable = false)
    private String supplier;

    @Column(length = 255, nullable = false)
    private String market;

    @Column(length = 255, nullable = false)
    private String category;

    @Column( nullable = false)
    private double priceunitwoiva;

    @Column( nullable = false)
    private int minstock;

    @Column( nullable = false)
    private int multiplebuy;

    @Column( nullable = false)
    private int maxstock;

    @Column( nullable = false)
    private float needpermouth;

    @Column( nullable = false)
    private int  stock;

    @Column( nullable = false)
    private String  units;

    @Column( nullable = false)
    private int  end;

    @Column( nullable = false)
    private int  coststock;

    @Column( nullable = false)
    private String  deparment;


    @Column(name="create_at",nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name="update_at",nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    public Store(){

    }
    @PreUpdate
    public void onUpdate(){
        this.updateAt = LocalDateTime.now();
    }

}
