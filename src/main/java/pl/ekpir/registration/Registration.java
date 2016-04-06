package pl.ekpir.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.ekpir.user.UserEntity;
import pl.ekpir.user.UserService;

@RestController
@RequestMapping(value = "${api.prefix}/registration")
public class Registration {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewUser(@RequestBody UserEntity user) {
        userService.registerNewUser(user);
    }
}
