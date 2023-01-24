package com.ecommerce.secondhand.user.service;

import com.ecommerce.secondhand.user.exception.UserDetailsNotFoundException;
import com.ecommerce.secondhand.user.model.dto.CreateUserDetailsRequest;
import com.ecommerce.secondhand.user.model.dto.UpdateUserDetailsRequest;
import com.ecommerce.secondhand.user.model.dto.UserDetailsDTO;
import com.ecommerce.secondhand.user.model.dto.UserDetailsDTOConverter;
import com.ecommerce.secondhand.user.model.entity.User;
import com.ecommerce.secondhand.user.model.entity.UserDetails;
import com.ecommerce.secondhand.user.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;
    private final UserService userService;
    private final UserDetailsDTOConverter userDetailsDTOConverter;

    public UserDetailsService(UserDetailsRepository userDetailsRepository, UserService userService, UserDetailsDTOConverter userDetailsDTOConverter) {
        this.userDetailsRepository = userDetailsRepository;
        this.userService = userService;
        this.userDetailsDTOConverter = userDetailsDTOConverter;
    }

    public UserDetailsDTO createUserDetails(CreateUserDetailsRequest createUserDetailsRequest){
        User user = userService.findUserById(createUserDetailsRequest.getUserId());
        UserDetails userDetails = new UserDetails(
                createUserDetailsRequest.getPhoneNumber(),
                createUserDetailsRequest.getAddress(),
                createUserDetailsRequest.getCity(),
                createUserDetailsRequest.getCountry(),
                createUserDetailsRequest.getPostCode(),
                user
        );

        return userDetailsDTOConverter.convert(userDetailsRepository.save(userDetails));
    }

    public UserDetailsDTO updateUserDetails (Long userDetailsId, UpdateUserDetailsRequest updateUserDetailsRequest){
        UserDetails updatedUserDetails = findUserDetailsById(userDetailsId);

        updatedUserDetails.setAddress(updateUserDetailsRequest.getAddress());
        updatedUserDetails.setCity(updateUserDetailsRequest.getCity());
        updatedUserDetails.setCountry(updateUserDetailsRequest.getCountry());
        updatedUserDetails.setPhoneNumber(updateUserDetailsRequest.getPhoneNumber());
        updatedUserDetails.setPostCode(updateUserDetailsRequest.getPostCode());

        return userDetailsDTOConverter.convert(userDetailsRepository.save(updatedUserDetails));
    }

    public void deleteUserDetailsById(Long userDetailsId){
        findUserDetailsById(userDetailsId);

        this.userDetailsRepository.deleteById(userDetailsId);
    }

    private UserDetails findUserDetailsById(Long userDetailsId){

        return userDetailsRepository.findById(userDetailsId)
                .orElseThrow(()-> new UserDetailsNotFoundException("UserDetails Couldn't be found by following id : "+ userDetailsId));


    }


}
