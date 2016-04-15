package pl.ekpir.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Created by Krystian on 2016-03-26.
 */

@RestController
@RequestMapping(value = "${api.prefix}/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<UserEntity> getUsersList() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserEntity getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/login/{login}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserEntity getUserByLogin(@PathVariable("login") String login) {
        return userService.getUserByLogin(login);
    }

    @RequestMapping(value = "/{userId}/password", method = RequestMethod.PUT, consumes = "application/json")
    public void changePassword(@PathVariable("userId") Long userId, @RequestBody Map<String, String> passwordsMap,
                               HttpServletResponse response) {
        String oldPassword = passwordsMap.get("oldPassword");
        String newPassword = passwordsMap.get("newPassword");
        if (!userService.changePassword(userId, oldPassword, newPassword)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Principal user(Principal user) {
        return user;
    }
}
