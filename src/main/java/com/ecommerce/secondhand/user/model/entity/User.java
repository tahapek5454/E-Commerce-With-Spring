package com.ecommerce.secondhand.user.model.entity;

import jakarta.persistence.*;
import lombok.*;

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

}
