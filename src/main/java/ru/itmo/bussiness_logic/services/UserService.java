package ru.itmo.bussiness_logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.bussiness_logic.dao.UserDao;
import ru.itmo.bussiness_logic.dto.UserDto;
import ru.itmo.bussiness_logic.encrypt.EncryptionUtil;
import ru.itmo.bussiness_logic.entities.User;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserDto registr(UserDto userDto){
        try {
            if (userDto.getLogin() == null  || userDto.getPassword() == null  || userDto.getEmail() == null) {
                return new UserDto("Bad request some parameters are missing");
            }
            UserDto currentUser = userDao.save(new User(userDto.getLogin(), EncryptionUtil.encrypt(userDto.getPassword()), userDto.getEmail()));
            if (currentUser.getLogin() != null) {
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

    public UserDto signIn(UserDto userDto){
        try {
            UserDto currentUser = userDao.findUser(userDto.getLogin(), EncryptionUtil.encrypt(userDto.getPassword()));
            if(currentUser.getLogin()==null){
                currentUser.setMsg("Login failed");
                return currentUser;
            }
            else {
                currentUser.setMsg("Login Success");
                return currentUser;
            }
        }
        catch (NoSuchAlgorithmException e){
            return null;
        }

    }

}
