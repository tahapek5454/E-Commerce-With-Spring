package com.ecommerce.secondhand.user.service;

import com.ecommerce.secondhand.user.exception.UserNotFoundException;
import com.ecommerce.secondhand.user.model.dto.CreateUserRequest;
import com.ecommerce.secondhand.user.model.dto.UpdateUserRequest;
import com.ecommerce.secondhand.user.model.dto.UserDTO;
import com.ecommerce.secondhand.user.model.dto.UserDTOConverter;
import com.ecommerce.secondhand.user.model.entity.User;
import com.ecommerce.secondhand.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDTOConverter userDTOConverter;


    public UserService(UserRepository userRepository, UserDTOConverter userDTOConverter) {
        this.userRepository = userRepository;
        this.userDTOConverter = userDTOConverter;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this.userDTOConverter::convert).collect(Collectors.toList());
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
                createUserRequest.getMiddleName()

        );

        return this.userDTOConverter.convert(this.userRepository.save(user));

    }

    public UserDTO updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = findUserById(id);

        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setMiddleName(updateUserRequest.getMiddleName());

        return this.userDTOConverter.convert(this.userRepository.save(user));

    }

    public void deactiveUser(Long id) {
    }

    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }



    private User findUserById(Long id){
        return this.userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User Couldn't be found by following id : "+ id));
    }

    private User findUserByMail(String mail){
        return this.userRepository.findByMail(mail)
                .orElseThrow(()-> new UserNotFoundException("User Couldn't be found by following mail : "+ mail));
    }


}
