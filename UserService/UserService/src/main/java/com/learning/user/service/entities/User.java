package com.learning.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "NAME", length = 30)
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ABOUT", length = 100)
    private String about;

    // It will not store in user database
    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
