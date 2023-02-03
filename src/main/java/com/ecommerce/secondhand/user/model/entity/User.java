package com.ecommerce.secondhand.user.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "custom_user")
@EqualsAndHashCode
public class User implements UserDetails {
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

    @NonNull
    String password;
    @Enumerated(EnumType.STRING)
    Role role = Role.USER;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
