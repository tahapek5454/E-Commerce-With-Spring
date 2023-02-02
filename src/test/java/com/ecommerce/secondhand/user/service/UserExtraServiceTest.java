package com.ecommerce.secondhand.user.service;

import com.ecommerce.secondhand.TestSupport;
import com.ecommerce.secondhand.user.exception.UserExtraNotFoundException;
import com.ecommerce.secondhand.user.model.dto.CreateUserExtraRequest;
import com.ecommerce.secondhand.user.model.dto.UpdateUserExtraRequest;
import com.ecommerce.secondhand.user.model.dto.UserExtraDTO;
import com.ecommerce.secondhand.user.model.dto.UserExtraDTOConverter;
import com.ecommerce.secondhand.user.model.entity.User;
import com.ecommerce.secondhand.user.model.entity.UserExtra;
import com.ecommerce.secondhand.user.repository.UserExtraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserExtraServiceTest extends TestSupport {

    private UserExtraDTOConverter userExtraDTOConverter;

    private UserService userService;
    private UserExtraRepository userExtraRepository;
    private UserExtraService userExtraService;

    @BeforeEach
    public void setUp(){
        userExtraDTOConverter = Mockito.mock(UserExtraDTOConverter.class);
        userService = Mockito.mock(UserService.class);
        userExtraRepository = Mockito.mock(UserExtraRepository.class);

        userExtraService = new UserExtraService(
                userExtraRepository,
                userService,
                userExtraDTOConverter);
    }


    @Test
    public void testCreateUserDetails_whenUserIdExist_itShouldReturnUserDetailsDTO(){
        CreateUserExtraRequest createUserExtraRequest = new CreateUserExtraRequest(
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

        UserExtra userExtra = new UserExtra(
                "000",
                "address",
                "city",
                "country",
                "postCode",
                user
        );

        UserExtra savedUserExtra = new UserExtra(
                userDetailsId,
                "000",
                "address",
                "city",
                "country",
                "postCode",
                user
        );

        UserExtraDTO userExtraDTO = new UserExtraDTO(
                "000",
                "address",
                "city",
                "country",
                "postCode"
        );


        Mockito.when(userService.findUserById(createUserExtraRequest.getUserId()))
                .thenReturn(user);
        Mockito.when(userExtraRepository.save(userExtra)).thenReturn(savedUserExtra);
        Mockito.when(userExtraDTOConverter.convert(savedUserExtra)).thenReturn(userExtraDTO);

        UserExtraDTO result = userExtraService.createUserDetails(createUserExtraRequest);

        assertEquals(userExtraDTO, result);

        Mockito.verify(userService).findUserById(createUserExtraRequest.getUserId());
        Mockito.verify(userExtraRepository).save(userExtra);
        Mockito.verify(userExtraDTOConverter).convert(savedUserExtra);




    }

    // whenUserIDNotExist

    @Test
    public void testUpdateUserDetails_whenUserDetailsIdNotExist_itShoulThrowUserDetailsNotFoundException(){

        UpdateUserExtraRequest updateUserExtraRequest = new UpdateUserExtraRequest(
                "0002",
                "address2",
                "city2",
                "country2",
                "postCode2"
        );


        Mockito.when(userExtraRepository.findById(userDetailsId)).thenReturn(Optional.empty());

        assertThrows(UserExtraNotFoundException.class, () ->
                userExtraService.updateUserDetails(userDetailsId, updateUserExtraRequest)
        );


        Mockito.verify(userExtraRepository).findById(userDetailsId);




    }


    @Test
    public void testUpdateUserDetails_whenUserDetailsIdExist_itShoulReturnUserDetailsDTO(){

        UpdateUserExtraRequest createUserDetailsRequest = new UpdateUserExtraRequest(
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

        UserExtra userExtra = new UserExtra(
                userDetailsId,
                "000",
                "address",
                "city",
                "country",
                "postCode",
                user
        );

        UserExtra uptadedUserExtra = new UserExtra(
                userDetailsId,
                "0002",
                "address2",
                "city2",
                "country2",
                "postCode2",
                user
        );

        UserExtraDTO userExtraDTO = new UserExtraDTO(
                "0002",
                "address2",
                "city2",
                "country2",
                "postCode2"
        );


        Mockito.when(userExtraRepository.findById(userDetailsId)).thenReturn(Optional.of(userExtra));
        Mockito.when(userExtraRepository.save(uptadedUserExtra)).thenReturn(uptadedUserExtra);
        Mockito.when(userExtraDTOConverter.convert(uptadedUserExtra)).thenReturn(userExtraDTO);

        UserExtraDTO result = userExtraService.updateUserDetails(userDetailsId, createUserDetailsRequest);

        assertEquals(userExtraDTO, result);

        Mockito.verify(userExtraRepository).findById(userDetailsId);
        Mockito.verify(userExtraRepository).save(uptadedUserExtra);
        Mockito.verify(userExtraDTOConverter).convert(uptadedUserExtra);


    }




    @Test
    public void testDeleteUserDetails_whenUserDetailsIdNotExist_itShoulThrowUserDetailsNotFoundException(){



        Mockito.when(userExtraRepository.findById(userDetailsId)).thenReturn(Optional.empty());

        assertThrows(UserExtraNotFoundException.class, () ->
                userExtraService.deleteUserDetailsById(userDetailsId)
        );


        Mockito.verify(userExtraRepository).findById(userDetailsId);




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

        UserExtra userExtra = new UserExtra(
                userDetailsId,
                "000",
                "address",
                "city",
                "country",
                "postCode",
                user
        );

        Mockito.when(userExtraRepository.findById(userDetailsId)).thenReturn(Optional.of(userExtra));


       userExtraService.deleteUserDetailsById(userDetailsId);



        Mockito.verify(userExtraRepository).findById(userDetailsId);
        Mockito.verify(userExtraRepository).deleteById(userDetailsId);


    }





}