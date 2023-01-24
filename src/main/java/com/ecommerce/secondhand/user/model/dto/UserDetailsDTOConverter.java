package com.ecommerce.secondhand.user.model.dto;

import com.ecommerce.secondhand.user.model.entity.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsDTOConverter {

    public UserDetailsDTO convert (UserDetails from){
        return new UserDetailsDTO(
                from.getPhoneNumber(),
                from.getAddress(),
                from.getCity(),
                from.getCountry(),
                from.getPostCode()
        );
    }

    public List<UserDetailsDTO> convert(List<UserDetails> fromList){
        return fromList.stream().map(from ->
                new UserDetailsDTO(
                        from.getPhoneNumber(),
                        from.getAddress(),
                        from.getCity(),
                        from.getCountry(),
                        from.getPostCode()
                )).collect(Collectors.toList());
    }


}
