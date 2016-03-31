package pl.ekpir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.ekpir.persistence.model.User;
import pl.ekpir.persistence.repository.IUserRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Krystian on 2016-03-26.
 */
@Transactional
@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
