package com.ecommerce.secondhand.user.controller;

import com.ecommerce.secondhand.user.model.dto.CreateUserExtraRequest;
import com.ecommerce.secondhand.user.model.dto.UpdateUserExtraRequest;
import com.ecommerce.secondhand.user.model.dto.UserExtraDTO;
import com.ecommerce.secondhand.user.service.UserExtraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/userdetails")
public class UserExtraController {

    private final UserExtraService userExtraService;

    public UserExtraController(UserExtraService userExtraService) {
        this.userExtraService = userExtraService;
    }

    @PostMapping
    public ResponseEntity<UserExtraDTO> addUserDetails(@RequestBody CreateUserExtraRequest createUserExtraRequest){

        return ResponseEntity.ok(this.userExtraService.createUserDetails(createUserExtraRequest));

    }

    @PutMapping("/{userDetailsId}")
    public ResponseEntity<UserExtraDTO> updateUserDetailsById(
            @PathVariable("userDetailsId") Long userDetailsId,
            @RequestBody UpdateUserExtraRequest updateUserExtraRequest
            ){
        return ResponseEntity.ok(this.userExtraService.updateUserDetails(userDetailsId, updateUserExtraRequest));
    }

    @DeleteMapping("/{userDetailsId}")
    public ResponseEntity<Void> deleteUserDetailsById(@PathVariable("userDetailsId") Long userDetailsId){
        this.userExtraService.deleteUserDetailsById(userDetailsId);
        return ResponseEntity.ok().build();
    }

}
