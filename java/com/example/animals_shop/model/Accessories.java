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
@Table(name = "accessories")
public class Accessories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String size;
    @Column
    private String material;
    @Column
    private String color;
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
    private String price;
    @ManyToOne
    private AccessoriesCategory accessoriesCategory;
}
