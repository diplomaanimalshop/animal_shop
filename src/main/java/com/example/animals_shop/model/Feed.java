package com.example.animals_shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "feed")
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String title;
    @Column(name = "short_text")
    private String shortText;
    @Column
    private String description;
    @Column
    private String location;
    @Column
    private String weight;
    @Column
    private String ingredients;
    @Column
    private String price;
    @Column(name = "created_date")
    private Date createdDate;
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;
}
