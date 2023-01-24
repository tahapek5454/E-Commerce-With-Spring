package com.ecommerce.secondhand.user.controller;

import com.ecommerce.secondhand.user.model.dto.CreateUserDetailsRequest;
import com.ecommerce.secondhand.user.model.dto.UpdateUserDetailsRequest;
import com.ecommerce.secondhand.user.model.dto.UserDetailsDTO;
import com.ecommerce.secondhand.user.service.UserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/userdetails")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public ResponseEntity<UserDetailsDTO> addUserDetails(@RequestBody CreateUserDetailsRequest createUserDetailsRequest){

        return ResponseEntity.ok(this.userDetailsService.createUserDetails(createUserDetailsRequest));

    }

    @PutMapping("/{userDetailsId}")
    public ResponseEntity<UserDetailsDTO> updateUserDetailsById(
            @PathVariable("userDetailsId") Long userDetailsId,
            @RequestBody UpdateUserDetailsRequest updateUserDetailsRequest
            ){
        return ResponseEntity.ok(this.userDetailsService.updateUserDetails(userDetailsId, updateUserDetailsRequest));
    }

    @DeleteMapping("/{userDetailsId}")
    public ResponseEntity<Void> deleteUserDetailsById(@PathVariable("userDetailsId") Long userDetailsId){
        this.userDetailsService.deleteUserDetailsById(userDetailsId);
        return ResponseEntity.ok().build();
    }

}
