package com.ecommerce.secondhand.user.model.dto;

import com.ecommerce.secondhand.user.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
//Neden Component Dedik
public class UserDTOConverter {

    private final UserExtraDTOConverter userExtraDTOConverter;

    public UserDTOConverter(UserExtraDTOConverter userExtraDTOConverter) {
        this.userExtraDTOConverter = userExtraDTOConverter;
    }

    public  UserDTO convert(User from){

        return new UserDTO(
                from.getMail(),
                from.getFirstName(),
                from.getLastName(),
                from.getMiddleName(),
                from.getPassword(),
                userExtraDTOConverter.convert(from.getUserExtraList())
        );


    }

    public List<UserDTO> convertList(List<User> fromList){
        return fromList.stream().map(from ->{

                    return new UserDTO(
                            from.getMail(),
                            from.getFirstName(),
                            from.getLastName(),
                            from.getMiddleName(),
                            from.getPassword(),
                            userExtraDTOConverter.convert(from.getUserExtraList())
                    );



                }
               ).collect(Collectors.toList());
    }
}
