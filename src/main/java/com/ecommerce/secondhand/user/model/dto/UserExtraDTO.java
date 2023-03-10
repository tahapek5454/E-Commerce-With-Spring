package com.ecommerce.secondhand.user.model.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserExtraDTO {
    String phoneNumber;
    String address;
    String city;
    String country;
    String postCode;
}
