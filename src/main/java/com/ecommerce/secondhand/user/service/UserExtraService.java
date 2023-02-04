package com.ecommerce.secondhand.user.service;

import com.ecommerce.secondhand.user.exception.UserExtraNotFoundException;
import com.ecommerce.secondhand.user.model.dto.CreateUserExtraRequest;
import com.ecommerce.secondhand.user.model.dto.UpdateUserExtraRequest;
import com.ecommerce.secondhand.user.model.dto.UserExtraDTO;
import com.ecommerce.secondhand.user.model.dto.UserExtraDTOConverter;
import com.ecommerce.secondhand.user.model.entity.User;
import com.ecommerce.secondhand.user.model.entity.UserExtra;
import com.ecommerce.secondhand.user.repository.UserExtraRepository;
import org.springframework.stereotype.Service;

@Service
public class UserExtraService {
    private final UserExtraRepository userExtraRepository;
    private final UserService userService;
    private final UserExtraDTOConverter userExtraDTOConverter;

    public UserExtraService(UserExtraRepository userExtraRepository, UserService userService, UserExtraDTOConverter userExtraDTOConverter) {
        this.userExtraRepository = userExtraRepository;
        this.userService = userService;
        this.userExtraDTOConverter = userExtraDTOConverter;
    }

    public UserExtraDTO createUserDetails(CreateUserExtraRequest createUserExtraRequest){
        User user = userService.findUserById(createUserExtraRequest.getUserId());
        UserExtra userExtra = new UserExtra(
                createUserExtraRequest.getPhoneNumber(),
                createUserExtraRequest.getAddress(),
                createUserExtraRequest.getCity(),
                createUserExtraRequest.getCountry(),
                createUserExtraRequest.getPostCode(),
                user
        );

        return userExtraDTOConverter.convert(userExtraRepository.save(userExtra));
    }

    public UserExtraDTO updateUserDetails (Long userDetailsId, UpdateUserExtraRequest updateUserExtraRequest){
        UserExtra updatedUserExtra = findUserDetailsById(userDetailsId);

        updatedUserExtra.setAddress(updateUserExtraRequest.getAddress());
        updatedUserExtra.setCity(updateUserExtraRequest.getCity());
        updatedUserExtra.setCountry(updateUserExtraRequest.getCountry());
        updatedUserExtra.setPhoneNumber(updateUserExtraRequest.getPhoneNumber());
        updatedUserExtra.setPostCode(updateUserExtraRequest.getPostCode());

        return userExtraDTOConverter.convert(userExtraRepository.save(updatedUserExtra));
    }

    public void deleteUserDetailsById(Long userDetailsId){
        findUserDetailsById(userDetailsId);

        this.userExtraRepository.deleteById(userDetailsId);
    }

    private UserExtra findUserDetailsById(Long userDetailsId){

        return userExtraRepository.findById(userDetailsId)
                .orElseThrow(()-> new UserExtraNotFoundException("UserDetails Couldn't be found by following id : "+ userDetailsId));


    }


}
