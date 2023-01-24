package com.ecommerce.secondhand.user.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NonNull
    String mail;
    @NonNull
    String firstName;
    @NonNull
    String lastName;
    @NonNull
    String middleName;
    List<UserDetailsDTO> userDetailsDTOList;
}
