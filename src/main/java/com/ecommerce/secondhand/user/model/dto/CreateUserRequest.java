package com.ecommerce.secondhand.user.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    String mail;
    String firstName;
    String lastName;
    String middleName;

}
