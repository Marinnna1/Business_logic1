package ru.itmo.bussiness_logic.dao;


import org.springframework.stereotype.Repository;
import ru.itmo.bussiness_logic.dto.UserDto;
import ru.itmo.bussiness_logic.encrypt.EncryptionUtil;
import ru.itmo.bussiness_logic.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Transactional()
@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean isThereUserWithSuchId(int id) {
        List<User> users = (List<User>) entityManager.createQuery("From User as user where user.id =" + id ).getResultList();
        return !users.isEmpty();
    }

    public User findUserById(int id) {
        List<User> users = (List<User>) entityManager.createQuery("From User as user where user.id =" + id ).getResultList();
        if(!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    public UserDto findUser(String login, String password) throws NoSuchAlgorithmException {
        List<User> users = (List<User>) entityManager.createQuery("From User as user where user.login ='" + login + "' and user.password='" + password + "'").getResultList();
        if(!users.isEmpty()){
            System.out.println(users.get(0).getLogin());
            return new UserDto( login, password, "");
        }
        return new UserDto( null, null, "");
    }

    public boolean findByLogin(String login) {
        List<User> users = (List<User>) entityManager.createQuery("From User as user where user.login ='" + login + "'").getResultList();
        return !users.isEmpty();
    }

    public UserDto save(User user) throws NoSuchAlgorithmException {
        if(!findByLogin(user.getLogin())) {
            user.setToken(EncryptionUtil.encrypt(user.getLogin() + user.getId()));
            entityManager.persist(user);
            return new UserDto(user.getLogin(), user.getPassword(), user.getEmail(), "");
        }
        return new UserDto(null, null, null, "Cant create user with same login: user '" + user.getLogin() + "' already exists ");
    }
}



