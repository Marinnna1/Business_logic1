package ru.itmo.bussiness_logic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.itmo.bussiness_logic.dto.UserDto;
import ru.itmo.bussiness_logic.security.JwtProvider;
import ru.itmo.bussiness_logic.services.UserService;


@RestController
@RequestMapping("user/")
public class UserController {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserService userService;

    AuthenticationManager authenticationManager;

    @GetMapping(path="lol")
    public String test(){
        return "success";
    }


    @PostMapping(path="login")
    public UserDto doSignIn(@RequestBody UserDto userDto) {
        try {
            return userService.signIn(userDto);
        }
        catch (Exception e){
            return new UserDto(userDto.getLogin(), "", "", "", "", "Transaction rollback Login failed");
        }
    }


    @PostMapping("reg")
    public UserDto doSignUp(@RequestBody UserDto userDto){
       // final String token = jwtProvider.generateToken(userDto.getLogin());
     //   userDto.setToken(token);
        return userService.registr(userDto);
    }

}
