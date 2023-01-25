package com.ecommerce.secondhand.user.service;

import com.ecommerce.secondhand.TestSupport;
import com.ecommerce.secondhand.user.exception.UserDetailsNotFoundException;
import com.ecommerce.secondhand.user.exception.UserNotFoundException;
import com.ecommerce.secondhand.user.model.dto.CreateUserDetailsRequest;
import com.ecommerce.secondhand.user.model.dto.UpdateUserDetailsRequest;
import com.ecommerce.secondhand.user.model.dto.UserDetailsDTO;
import com.ecommerce.secondhand.user.model.dto.UserDetailsDTOConverter;
import com.ecommerce.secondhand.user.model.entity.User;
import com.ecommerce.secondhand.user.model.entity.UserDetails;
import com.ecommerce.secondhand.user.repository.UserDetailsRepository;
import com.ecommerce.secondhand.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDetailsServiceTest extends TestSupport {

    private UserDetailsDTOConverter userDetailsDTOConverter;

    private UserService userService;
    private UserDetailsRepository userDetailsRepository;
    private UserDetailsService userDetailsService;

    @BeforeEach
    public void setUp(){
        userDetailsDTOConverter = Mockito.mock(UserDetailsDTOConverter.class);
        userService = Mockito.mock(UserService.class);
        userDetailsRepository = Mockito.mock(UserDetailsRepository.class);

        userDetailsService = new UserDetailsService(
                userDetailsRepository,
                userService,
                userDetailsDTOConverter);
    }


    @Test
    public void testCreateUserDetails_whenUserIdExist_itShouldReturnUserDetailsDTO(){
        CreateUserDetailsRequest createUserDetailsRequest = new CreateUserDetailsRequest(
          "000",
          "address",
          "city",
          "country",
          "postCode",
          userId
        );

        User user = new User(
          userId,
          "mail",
          "firstName",
          "lastName",
          "",
          true
        );

        UserDetails userDetails = new UserDetails(
                "000",
                "address",
                "city",
                "country",
                "postCode",
                user
        );

        UserDetails savedUserDetails = new UserDetails(
                userDetailsId,
                "000",
                "address",
                "city",
                "country",
                "postCode",
                user
        );

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(
                "000",
                "address",
                "city",
                "country",
                "postCode"
        );


        Mockito.when(userService.findUserById(createUserDetailsRequest.getUserId()))
                .thenReturn(user);
        Mockito.when(userDetailsRepository.save(userDetails)).thenReturn(savedUserDetails);
        Mockito.when(userDetailsDTOConverter.convert(savedUserDetails)).thenReturn(userDetailsDTO);

        UserDetailsDTO result = userDetailsService.createUserDetails(createUserDetailsRequest);

        assertEquals(userDetailsDTO, result);

        Mockito.verify(userService).findUserById(createUserDetailsRequest.getUserId());
        Mockito.verify(userDetailsRepository).save(userDetails);
        Mockito.verify(userDetailsDTOConverter).convert(savedUserDetails);




    }

    // whenUserIDNotExist

    @Test
    public void testUpdateUserDetails_whenUserDetailsIdNotExist_itShoulThrowUserDetailsNotFoundException(){

        UpdateUserDetailsRequest updateUserDetailsRequest = new UpdateUserDetailsRequest(
                "0002",
                "address2",
                "city2",
                "country2",
                "postCode2"
        );


        Mockito.when(userDetailsRepository.findById(userDetailsId)).thenReturn(Optional.empty());

        assertThrows(UserDetailsNotFoundException.class, () ->
                userDetailsService.updateUserDetails(userDetailsId, updateUserDetailsRequest)
        );


        Mockito.verify(userDetailsRepository).findById(userDetailsId);




    }


    @Test
    public void testUpdateUserDetails_whenUserDetailsIdExist_itShoulReturnUserDetailsDTO(){

        UpdateUserDetailsRequest createUserDetailsRequest = new UpdateUserDetailsRequest(
                "0002",
                "address2",
                "city2",
                "country2",
                "postCode2"
        );

        User user = new User(
                userId,
                "mail",
                "firstName",
                "lastName",
                "",
                true
        );

        UserDetails userDetails = new UserDetails(
                userDetailsId,
                "000",
                "address",
                "city",
                "country",
                "postCode",
                user
        );

        UserDetails uptadedUserDetails = new UserDetails(
                userDetailsId,
                "0002",
                "address2",
                "city2",
                "country2",
                "postCode2",
                user
        );

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(
                "0002",
                "address2",
                "city2",
                "country2",
                "postCode2"
        );


        Mockito.when(userDetailsRepository.findById(userDetailsId)).thenReturn(Optional.of(userDetails));
        Mockito.when(userDetailsRepository.save(uptadedUserDetails)).thenReturn(uptadedUserDetails);
        Mockito.when(userDetailsDTOConverter.convert(uptadedUserDetails)).thenReturn(userDetailsDTO);

        UserDetailsDTO result = userDetailsService.updateUserDetails(userDetailsId, createUserDetailsRequest);

        assertEquals(userDetailsDTO, result);

        Mockito.verify(userDetailsRepository).findById(userDetailsId);
        Mockito.verify(userDetailsRepository).save(uptadedUserDetails);
        Mockito.verify(userDetailsDTOConverter).convert(uptadedUserDetails);


    }




    @Test
    public void testDeleteUserDetails_whenUserDetailsIdNotExist_itShoulThrowUserDetailsNotFoundException(){



        Mockito.when(userDetailsRepository.findById(userDetailsId)).thenReturn(Optional.empty());

        assertThrows(UserDetailsNotFoundException.class, () ->
                userDetailsService.deleteUserDetailsById(userDetailsId)
        );


        Mockito.verify(userDetailsRepository).findById(userDetailsId);




    }


    @Test
    public void testDeleteUserDetails_whenUserDetailsIdExist_itShoulDeleteUserDetails(){


        User user = new User(
                userId,
                "mail",
                "firstName",
                "lastName",
                "",
                true
        );

        UserDetails userDetails = new UserDetails(
                userDetailsId,
                "000",
                "address",
                "city",
                "country",
                "postCode",
                user
        );

        Mockito.when(userDetailsRepository.findById(userDetailsId)).thenReturn(Optional.of(userDetails));


       userDetailsService.deleteUserDetailsById(userDetailsId);



        Mockito.verify(userDetailsRepository).findById(userDetailsId);
        Mockito.verify(userDetailsRepository).deleteById(userDetailsId);


    }





}