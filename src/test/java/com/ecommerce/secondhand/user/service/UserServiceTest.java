package com.ecommerce.secondhand.user.service;

import com.ecommerce.secondhand.TestSupport;
import com.ecommerce.secondhand.user.model.dto.UserDTO;
import com.ecommerce.secondhand.user.model.dto.UserDTOConverter;
import com.ecommerce.secondhand.user.model.entity.User;
import com.ecommerce.secondhand.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest extends TestSupport {
    private UserDTOConverter userDTOConverter;
    private UserRepository userRepository;
    private UserService userService;

    @BeforeAll
    public void setUp(){
        // alt bagimliliklar mockito ile cozulur
        userDTOConverter = Mockito.mock(UserDTOConverter.class);
        userRepository = Mockito.mock(UserRepository.class);

        userService = new UserService(userRepository, userDTOConverter);

    }

    @Test
    public void testGetAllUsers_itShouldReturnUserDTOList(){
        List<User> userList = generateUsers();
        List<UserDTO> userDTOList = generateUserDTOList(userList);
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Mockito.when(userDTOConverter.convertList(userList)).thenReturn(userDTOList);

        List<UserDTO> result = userService.getAllUsers();

        assertEquals(userDTOList, result);
        verify(userRepository).findAll();
        verify(userDTOConverter).convertList(userList);


    }

}