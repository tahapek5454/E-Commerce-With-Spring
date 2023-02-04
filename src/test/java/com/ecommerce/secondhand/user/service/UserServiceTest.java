package com.ecommerce.secondhand.user.service;

import com.ecommerce.secondhand.TestSupport;
import com.ecommerce.secondhand.user.exception.UserIsNotActiveException;
import com.ecommerce.secondhand.user.exception.UserNotFoundException;
import com.ecommerce.secondhand.user.model.dto.CreateUserRequest;
import com.ecommerce.secondhand.user.model.dto.UpdateUserRequest;
import com.ecommerce.secondhand.user.model.dto.UserDTO;
import com.ecommerce.secondhand.user.model.dto.UserDTOConverter;
import com.ecommerce.secondhand.user.model.entity.User;
import com.ecommerce.secondhand.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest extends TestSupport {
    private UserDTOConverter userDTOConverter;
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
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

    @Test
    public void testGetUserByMail_whenUserMailExist_itShouldReturnUserDTO(){
        String mail = "mail@gmail.com";
        User user = generateUser(mail);
        UserDTO userDTO = generateUserDTO(mail);
        Mockito.when(userRepository.findByMail(mail)).thenReturn(Optional.of(user));
        Mockito.when(userDTOConverter.convert(user)).thenReturn(userDTO);

        UserDTO result = userService.getByMail(mail);

        assertEquals(userDTO, result);
        verify(userRepository).findByMail(mail);
        verify(userDTOConverter).convert(user);

    }

    @Test
    public void testGetUserByMail_whenUserMailDoesNotExist_itShouldThrowUserNotFoundException(){
        String mail = "mail@gmail.com";

        Mockito.when(userRepository.findByMail(mail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                userService.getByMail(mail)
        );

        verify(userRepository).findByMail(mail);
        verifyNoInteractions(userDTOConverter);

     }


    @Test
    public void testGetUserByID_whenUserIdExist_itShouldReturnUserDTO(){
        String mail = "mail@gmail.com";
        User user = new User(
                userId,
                mail,
                "firstName",
                "lastName",
                "",
                true
        );
        UserDTO userDTO = new UserDTO(
                mail,
                "firstName",
                "lastName",
                "",
                "123"
        );
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(userDTOConverter.convert(user)).thenReturn(userDTO);

        UserDTO result = userService.getById(userId);

        assertEquals(userDTO, result);
        verify(userRepository).findById(userId);
        verify(userDTOConverter).convert(user);

    }

    @Test
    public void testGetUserById_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException(){


        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                userService.getById(userId)
        );

        verify(userRepository).findById(userId);
        verifyNoInteractions(userDTOConverter);

    }

    @Test
    public void testCreateUser_itShouldReturnCreatedUserDTO(){
        String mail = "mail@gmail.com";
        CreateUserRequest createUserRequest = new CreateUserRequest(
                mail,
                "firstName",
                "lastName",
                "",
                "123"
        );

        User user = new User(
                mail,
                "firstName",
                "lastName",
                "",
                false,
                "123"
        );

        User savedUser = new User(
                1L,
                 mail,
                "firstName",
                "lastName",
                "",
                false
        );

        UserDTO userDTO = new UserDTO(
                mail,
                "firstName",
                "lastName",
                "",
                "123"
        );


        Mockito.when(userRepository.save(user)).thenReturn(savedUser);
        Mockito.when(userDTOConverter.convert(savedUser)).thenReturn(userDTO);

        UserDTO result = userService.addUser(createUserRequest);

        assertEquals(userDTO, result);

        verify(userRepository).save(user);
        verify(userDTOConverter).convert(savedUser);

    }

    @Test
    public void testUpdateUserByMail_whenUserMailExistUserIsActive_itShouldReturnUpdatedUserDTO(){
        String mail = "mail@gmail.com";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(
                "firstName2",
                "lastName2",
                "middleName2"
        );

        User user = new User(
                mail,
                "firstName",
                "lastName",
                "middleName",
                true,
                "123"
        );

        User savedUser = new User(
                1L,
                mail,
                "firstName2",
                "lastName2",
                "middleName2",
                true
        );

        UserDTO userDTO = new UserDTO(
                mail,
                "firstName2",
                "lastName2",
                "middleName2",
                "123"
        );


        Mockito.when(userRepository.findByMail(mail)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(savedUser);
        Mockito.when(userDTOConverter.convert(savedUser)).thenReturn(userDTO);

        UserDTO result = userService.updateUserByMail(mail, updateUserRequest);

        assertEquals(userDTO, result);

        verify(userRepository).save(user);
        verify(userDTOConverter).convert(savedUser);

    }

    @Test
    public void testUpdateUserByMail_whenUserMailDoesNotExist_itShoulThrowUserNotFoundException(){
        String mail = "mail@gmail.com";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(
                "firstName2",
                "lastName2",
                "middleName2"
        );

        Mockito.when(userRepository.findByMail(mail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                userService.updateUserByMail(mail, updateUserRequest)
        );

        verify(userRepository).findByMail(mail);
        verifyNoMoreInteractions(userRepository);
        verifyNoInteractions(userDTOConverter);

    }

    @Test
    public void testUpdateUserByMail_whenUserMailExistButUserIsNotActive_itShoulThrowUserIsNotActive(){
        String mail = "mail@gmail.com";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(
                "firstName2",
                "lastName2",
                "middleName2"
        );
        User user = new User(
                mail,
                "firstName",
                "lastName",
                "middleName",
                false,
                "123"
        );

        Mockito.when(userRepository.findByMail(mail)).thenReturn(Optional.of(user));

        assertThrows(UserIsNotActiveException.class, () ->
                userService.updateUserByMail(mail, updateUserRequest)
        );

        verify(userRepository).findByMail(mail);
        verifyNoMoreInteractions(userRepository);
        verifyNoInteractions(userDTOConverter);

    }



    @Test
    public void testUpdateUser_whenUserIdExistUserIsActive_itShouldReturnUpdatedUserDTO(){
        String mail = "mail@gmail.com";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(
                "firstName2",
                "lastName2",
                "middleName2"
        );

        User user = new User(
                userId,
                mail,
                "firstName",
                "lastName",
                "middleName",
                true
        );

        User savedUser = new User(
                userId,
                mail,
                "firstName2",
                "lastName2",
                "middleName2",
                true
        );

        UserDTO userDTO = new UserDTO(
                mail,
                "firstName2",
                "lastName2",
                "middleName2",
                "123"
        );


        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(savedUser);
        Mockito.when(userDTOConverter.convert(savedUser)).thenReturn(userDTO);

        UserDTO result = userService.updateUser(userId, updateUserRequest);

        assertEquals(userDTO, result);

        verify(userRepository).save(user);
        verify(userDTOConverter).convert(savedUser);

    }

    @Test
    public void testUpdateUser_whenUserIdDoesNotExist_itShoulThrowUserNotFoundException(){

        UpdateUserRequest updateUserRequest = new UpdateUserRequest(
                "firstName2",
                "lastName2",
                "middleName2"
        );

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                userService.updateUser(userId, updateUserRequest)
        );

        verify(userRepository).findById(userId);
        verifyNoMoreInteractions(userRepository);
        verifyNoInteractions(userDTOConverter);

    }

    @Test
    public void testUpdateUser_whenUserIdExistButUserIsNotActive_itShoulThrowUserIsNotActive(){
        String mail = "mail@gmail.com";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(
                "firstName2",
                "lastName2",
                "middleName2"
        );
        User user = new User(
                userId,
                mail,
                "firstName",
                "lastName",
                "middleName",
                false
        );

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        assertThrows(UserIsNotActiveException.class, () ->
                userService.updateUser(userId, updateUserRequest)
        );

        verify(userRepository).findById(userId);
        verifyNoMoreInteractions(userRepository);
        verifyNoInteractions(userDTOConverter);

    }

    @Test
    public void testDeactiveUser_whenUserIdExist_itShoulUpdateUserByActiveFalse(){
        String mail = "mail@gmail.com";

        User user = new User(
                userId,
                mail,
                "firstName",
                "lastName",
                "middleName",
                true
        );

        User savedUser = new User(
                userId,
                mail,
                "firstName",
                "lastName",
                "middleName",
                false
        );

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.deactivateUser(userId);

        verify(userRepository).findById(userId);
        verify(userRepository).save(savedUser);


    }

    @Test
    public void testDeactiveUser_whenUserIdDoesNotExist_itShoulThrowUserNotFoundException(){

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                userService.deactivateUser(userId)
        );

        verify(userRepository).findById(userId);
        verifyNoMoreInteractions(userRepository);


    }

    @Test
    public void testActiveUser_whenUserIdDoesNotExist_itShoulThrowUserNotFoundException(){

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                userService.deactivateUser(userId)
        );

        verify(userRepository).findById(userId);
        verifyNoMoreInteractions(userRepository);


    }

    @Test
    public void testActiveUser_whenUserIdExist_itShoulUpdateUserByActiveTrue(){


        String mail = "mail@gmail.com";

        User user = new User(
                userId,
                mail,
                "firstName",
                "lastName",
                "middleName",
                false
        );

        User savedUser = new User(
                userId,
                mail,
                "firstName",
                "lastName",
                "middleName",
                true
        );

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.activeUser(userId);

        verify(userRepository).findById(userId);
        verify(userRepository).save(savedUser);


    }


    @Test
    public void testDeleteUserByMail_whenUserMailDoesNotExist_itShoulThrowUserNotFoundException(){
        String mail = "mail@gmail.com";

        Mockito.when(userRepository.findByMail(mail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                userService.deleteUserByMail(mail)
        );

        verify(userRepository).findByMail(mail);
        verifyNoMoreInteractions(userRepository);


    }

    @Test
    public void testDeleteUserByMail_whenUserMailExist_itShoulDeleteUser(){


        String mail = "mail@gmail.com";

        User user = new User(
                userId,
                mail,
                "firstName",
                "lastName",
                "middleName",
                true
        );

        Mockito.when(userRepository.findByMail(mail)).thenReturn(Optional.of(user));

        userService.deleteUserByMail(user.getMail());

        verify(userRepository).findByMail(mail);
        //Burada delete fonksiyonusu referans gosteridigimde sıkıntı oldu ?
        //Servicdeki repoyu direkt degistirdim
        verify(userRepository).deleteById(user.getId());


    }


    @Test
    public void testDeleteUser_whenUserIdDoesNotExist_itShoulThrowUserNotFoundException(){

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                userService.deleteUser(userId)
        );

        verify(userRepository).findById(userId);
        verifyNoMoreInteractions(userRepository);


    }

    @Test
    public void testDeleteUser_whenUserIdExist_itShoulDeleteUser(){


        String mail = "mail@gmail.com";

        User user = new User(
                userId,
                mail,
                "firstName",
                "lastName",
                "middleName",
                true
        );

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.deleteUser(userId);

        verify(userRepository).findById(userId);
        verify(userRepository).deleteById(userId);


    }

}