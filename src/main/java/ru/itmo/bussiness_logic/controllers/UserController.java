package ru.itmo.bussiness_logic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.bussiness_logic.dto.UserDto;
import ru.itmo.bussiness_logic.services.UserService;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(path = "user/")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping(path="login")
    public void doSignIn(@RequestBody UserDto userDto) throws NoSuchAlgorithmException {
        userService.signIn(userDto);
    }


    @PostMapping("reg")
    public void doSignUp(@RequestBody UserDto userDto) throws NoSuchAlgorithmException {
        System.out.println("some");
        userService.registr(userDto);
    }


}
