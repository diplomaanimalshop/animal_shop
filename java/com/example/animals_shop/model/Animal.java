package com.example.animals_shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String breed;
    @Column
    private String weight;
    @Column
    private String price;
    @Column
    private String color;
    @Column
    private Date dob;
    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private String title;
    @Column(name = "short_text")
    private String shortText;
    @Column
    private String description;
    @Column(name = "created_date")
    private Date createdDate;
    @Column
    private String location;
    @ManyToOne
    private User user;
    @Column
    private String age;
    @Column
    private String size;
    @ManyToOne
    private AnimalCategory animalCategory;



}
