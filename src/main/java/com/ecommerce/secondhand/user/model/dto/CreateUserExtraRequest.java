package com.ecommerce.secondhand.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserExtraRequest {
    String phoneNumber;
    String address;
    String city;
    String country;
    String postCode;
    Long userId;
}
