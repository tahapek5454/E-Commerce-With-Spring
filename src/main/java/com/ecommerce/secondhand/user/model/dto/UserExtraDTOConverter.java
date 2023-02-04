package com.ecommerce.secondhand.user.model.dto;

import com.ecommerce.secondhand.user.model.entity.UserExtra;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserExtraDTOConverter {

    public UserExtraDTO convert (UserExtra from){
        return new UserExtraDTO(
                from.getPhoneNumber(),
                from.getAddress(),
                from.getCity(),
                from.getCountry(),
                from.getPostCode()
        );
    }

    public List<UserExtraDTO> convert(List<UserExtra> fromList){
        return fromList.stream().map(from ->
                new UserExtraDTO(
                        from.getPhoneNumber(),
                        from.getAddress(),
                        from.getCity(),
                        from.getCountry(),
                        from.getPostCode()
                )).collect(Collectors.toList());
    }


}
