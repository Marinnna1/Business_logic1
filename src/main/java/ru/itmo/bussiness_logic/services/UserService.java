package ru.itmo.bussiness_logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.bussiness_logic.dao.UserDao;
import ru.itmo.bussiness_logic.dto.UserDto;
import ru.itmo.bussiness_logic.entities.User;
import ru.itmo.bussiness_logic.enums.Role;
import ru.itmo.bussiness_logic.security.JwtProvider;


import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtProvider jwtProvider;

    public UserDto registr(UserDto userDto){
        try {
            if (userDto.getLogin() == null || userDto.getPassword() == null || userDto.getEmail() == null) {
                return new UserDto("Bad request some parameters are missing");
            }
            UserDto currentUser = userDao.save(new User(userDto.getLogin(), passwordEncoder.encode(userDto.getPassword()), userDto.getEmail(), userDto.getToken(), Role.USER));
            if (currentUser.getLogin() != null) {
                currentUser.setPassword("");
                currentUser.setMsg("Registration success");
                return currentUser;
            } else {
                currentUser.addMsg(" Registration failed");
                return currentUser;
            }
        }
        catch (NoSuchAlgorithmException e){
            return null;
        }


    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {NoSuchAlgorithmException.class,NullPointerException.class})
    public UserDto signIn(UserDto userDto){
        try {
            User currentUser = userDao.findUser(userDto.getLogin(), userDto.getPassword());
//            if(currentUser==null){
//                return new UserDto("Login failed");
//            }
//            else {
            try {
        //        userDao.save(new User("1","1","1")); //testTransaction
                if (passwordEncoder.matches(userDto.getPassword(), currentUser.getPassword())) {
                    String token = jwtProvider.generateToken(userDto.getLogin());
                    currentUser.setToken(token);
                    userDao.update(currentUser);
                    return new UserDto(userDto.getLogin(), "", currentUser.getRole().toString(), currentUser.getEmail(), token, "Login Success");
                } else {
                    return new UserDto(userDto.getLogin(), "", "", "", "", "Wrong password");
                }
            }
            catch (NullPointerException e){
//                throw e;
                return new UserDto(userDto.getLogin(), "", "", "", "", "Login failed");
            }
//            }
        }
        catch (NoSuchAlgorithmException e){
            return null;
        }

    }
}
