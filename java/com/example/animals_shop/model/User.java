package com.example.animals_shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private  String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String nickname;
    @Column
    private String email;
    @Column
    private String password;
    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.USER;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column
    private String avatar;
    @Column(name = "created_date")
    private Date createdDate;
}
