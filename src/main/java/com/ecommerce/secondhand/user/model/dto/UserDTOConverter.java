package com.ecommerce.secondhand.user.model.dto;

import com.ecommerce.secondhand.user.model.entity.User;
import org.springframework.stereotype.Component;

@Component
//Neden Component Dedik
public class UserDTOConverter {
    public  UserDTO convert(User from){
        return new UserDTO(
                from.getMail(),
                from.getFirstName(),
                from.getLastName(),
                from.getMiddleName()
        );
    }
}
