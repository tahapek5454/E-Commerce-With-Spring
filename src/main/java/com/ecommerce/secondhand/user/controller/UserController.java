package com.ecommerce.secondhand.user.controller;

import com.ecommerce.secondhand.user.model.dto.CreateUserRequest;
import com.ecommerce.secondhand.user.model.dto.UpdateUserRequest;
import com.ecommerce.secondhand.user.model.dto.UserDTO;
import com.ecommerce.secondhand.user.model.entity.User;
import com.ecommerce.secondhand.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.userService.getById(id));
    }

    @GetMapping("/getUserByMail/{mail}")
    public ResponseEntity<UserDTO> getByMail(@PathVariable("mail") String mail){
        return ResponseEntity.ok(this.userService.getByMail(mail));
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody CreateUserRequest createUserRequest){
        return ResponseEntity.ok((this.userService.addUser(createUserRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UpdateUserRequest updateUserRequest){

        return ResponseEntity.ok(this.userService.updateUser(id, updateUserRequest));
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<Void> deactiveUser (@PathVariable("id") Long id){
//        this.userService.deactiveUser(id);
//        return  ResponseEntity.ok().build();
//    }
//
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable("id") Long id){
        this.userService.deleteUser(id);
        return  ResponseEntity.ok().build();
    }




}
