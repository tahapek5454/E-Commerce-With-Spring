package com.ecommerce.secondhand;

import com.ecommerce.secondhand.user.model.dto.UserDTO;
import com.ecommerce.secondhand.user.model.entity.User;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {

    public static Long userId = 100L;
    public static Long userDetailsId = 100L;
    public static List<User> generateUsers(){
        return IntStream.range(0, 5).mapToObj(i -> new User(
                (long) i,
                i+"@gmail.com",
                "firstname"+i,
                "lastName"+i,
                "middleName"+i,
                new Random(2).nextBoolean()
        )).collect(Collectors.toList());
    }

    public static List<UserDTO> generateUserDTOList(List<User> userList){

    return userList.stream()
        .map(
            from ->
                new UserDTO(
                    from.getMail(),
                    from.getFirstName(),
                    from.getLastName(),
                    from.getMiddleName(),
                    from.getPassword()))
        .collect(Collectors.toList());
    }

    public static User generateUser(String mail){

        return new User(
                (long) userId,
                userId+mail,
                "firstName"+userId,
                "lastName"+userId,
                "middleName"+userId,
                new Random(2).nextBoolean()

        );

    }

    public static UserDTO generateUserDTO(String mail){
        return  new UserDTO(
                userId+mail,
                "firstName"+userId,
                "lastName"+userId,
                "middleName"+userId,
                "123"
        );
    }





}
