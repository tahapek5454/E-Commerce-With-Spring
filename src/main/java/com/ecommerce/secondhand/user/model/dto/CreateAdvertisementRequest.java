package com.ecommerce.secondhand.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdvertisementRequest {
    String title;
    String description;
    Double price;
    Long userId;
}
