package com.ecommerce.secondhand.user.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "custom_user")
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    @Column(unique = true)
    String mail;
    @NonNull
    String firstName;
    @NonNull
    String lastName;
    @NonNull
    String middleName;
    @NonNull
    Boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<UserExtra> userExtraList = new ArrayList<UserExtra>();


    //for testing


    public User(Long id, @NonNull String mail, @NonNull String firstName, @NonNull String lastName, @NonNull String middleName, @NonNull Boolean isActive) {
        this.id = id;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.isActive = isActive;
    }
}
