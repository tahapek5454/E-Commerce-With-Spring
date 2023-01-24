package com.ecommerce.secondhand.user.model.dto;

import com.ecommerce.secondhand.user.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
//Neden Component Dedik
public class UserDTOConverter {

    private final UserDetailsDTOConverter userDetailsDTOConverter;

    public UserDTOConverter(UserDetailsDTOConverter userDetailsDTOConverter) {
        this.userDetailsDTOConverter = userDetailsDTOConverter;
    }

    public  UserDTO convert(User from){

        return new UserDTO(
                from.getMail(),
                from.getFirstName(),
                from.getLastName(),
                from.getMiddleName(),
                userDetailsDTOConverter.convert(from.getUserDetailsList())
        );


    }

    public List<UserDTO> convertList(List<User> fromList){
        return fromList.stream().map(from ->{

                    return new UserDTO(
                            from.getMail(),
                            from.getFirstName(),
                            from.getLastName(),
                            from.getMiddleName(),
                            userDetailsDTOConverter.convert(from.getUserDetailsList())
                    );



                }
               ).collect(Collectors.toList());
    }
}
