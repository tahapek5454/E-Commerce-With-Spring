package com.ecommerce.secondhand.user.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementDTO {
    String id;
    String title;
    String description;
    Double price;
    Long userId;
    Date creationDate;
    Date lastModifiedDate;
}
