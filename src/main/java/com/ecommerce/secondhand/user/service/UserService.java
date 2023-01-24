package com.ecommerce.secondhand.user.service;

import com.ecommerce.secondhand.user.exception.UserIsNotActiveException;
import com.ecommerce.secondhand.user.exception.UserNotFoundException;
import com.ecommerce.secondhand.user.model.dto.CreateUserRequest;
import com.ecommerce.secondhand.user.model.dto.UpdateUserRequest;
import com.ecommerce.secondhand.user.model.dto.UserDTO;
import com.ecommerce.secondhand.user.model.dto.UserDTOConverter;
import com.ecommerce.secondhand.user.model.entity.User;
import com.ecommerce.secondhand.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserDTOConverter userDTOConverter;


    public UserService(UserRepository userRepository, UserDTOConverter userDTOConverter) {
        this.userRepository = userRepository;
        this.userDTOConverter = userDTOConverter;
    }

    public List<UserDTO> getAllUsers() {
        return this.userDTOConverter.convertList(userRepository.findAll());

    }

    public UserDTO getById(Long id) {
        User user = findUserById(id);
        return this.userDTOConverter.convert(user);
    }

    public UserDTO getByMail(String mail) {
        User user = findUserByMail(mail);

        return this.userDTOConverter.convert(user);
    }

    public UserDTO addUser(CreateUserRequest createUserRequest){
        User user = new User(
                createUserRequest.getMail(),
                createUserRequest.getFirstName(),
                createUserRequest.getLastName(),
                createUserRequest.getMiddleName(),
                false

        );

        return this.userDTOConverter.convert(this.userRepository.save(user));

    }

    public UserDTO updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = findUserById(id);

        if(!user.getIsActive()){
            logger.warn(String.format("The user wanted update is not active, user mail: %s ", user.getMail()));
            throw new UserIsNotActiveException(UserIsNotActiveException.message + "user mail: "+ user.getMail());
        }

        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setMiddleName(updateUserRequest.getMiddleName());

        return this.userDTOConverter.convert(this.userRepository.save(user));

    }

    public UserDTO updateUserByMail(String mail, UpdateUserRequest updateUserRequest) {
        User user = findUserByMail(mail);

        if(!user.getIsActive()){
            logger.warn(String.format("The user wanted update is not active, user mail: %s ", mail));
            throw new UserIsNotActiveException(UserIsNotActiveException.message + "user mail: "+ mail);
        }

        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setMiddleName(updateUserRequest.getMiddleName());

        return this.userDTOConverter.convert(this.userRepository.save(user));

    }

    public void deleteUserByMail(String mail ){
        User user = findUserByMail(mail);
        this.userRepository.deleteById(user.getId());
    }

    public void deleteUser(Long id) {
        findUserById(id);

        this.userRepository.deleteById(id);

    }


    public void deactivateUser(Long id) {
        changeActivateUserById(id, false);
    }

    public void activeUser(Long id) {

        changeActivateUserById(id, true);
    }

    private void changeActivateUserById(Long id, Boolean status){
        User user = findUserById(id);

        user.setIsActive(status);
        this.userRepository.save(user);

    }

    protected User findUserById(Long id){
        return this.userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User Couldn't be found by following id : "+ id));
    }

    private User findUserByMail(String mail){
        return this.userRepository.findByMail(mail)
                .orElseThrow(()-> new UserNotFoundException("User Couldn't be found by following mail : "+ mail));
    }

}
