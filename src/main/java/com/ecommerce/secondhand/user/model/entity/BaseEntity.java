package com.ecommerce.secondhand.user.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    LocalDateTime createdDate=null;
    LocalDateTime updateDate=null;
}
