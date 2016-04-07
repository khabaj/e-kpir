package pl.ekpir.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void registerNewUser(UserEntity user) {
        user.setRole(RoleEnum.USER);
        userRepository.save(user);
    }

    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }
    
    public UserEntity getUserById(Long userId){
        return userRepository.findOne(userId);
    }
}
