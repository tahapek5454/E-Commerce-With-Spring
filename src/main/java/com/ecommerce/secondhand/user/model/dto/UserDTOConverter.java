package com.ecommerce.secondhand.user.model.dto;

import com.ecommerce.secondhand.user.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserDTO> convertList(List<User> fromList){
        return fromList.stream().map(from ->
                new UserDTO(
                        from.getMail(),
                        from.getFirstName(),
                        from.getLastName(),
                        from.getMiddleName()
                )).collect(Collectors.toList());
    }
}
