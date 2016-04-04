package pl.ekpir.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Krystian on 2016-03-26.
 */

@RestController
@RequestMapping(value = "${api.prefix}/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("true")
    public void registerNewUser(@RequestBody UserEntity user) {
        userService.registerNewUser(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<UserEntity> getUsersList() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Principal user(Principal user) {
        return user;
    }
}
