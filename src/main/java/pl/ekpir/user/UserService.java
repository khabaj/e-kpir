package pl.ekpir.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ekpir.company.CompanyEntity;

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
        user.setCompany(new CompanyEntity(user));
        userRepository.save(user);
    }

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findOne(userId);
    }
    
    public boolean changePassword(Long userId, String oldPassword, String newPassword) {
    	UserEntity user = getUserById(userId);
    	if(user.getPassword().equals(oldPassword)) {
    		user.setPassword(newPassword);
    		userRepository.save(user);
    		return true;
    	}
    	return false;
    }

    public UserEntity getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
