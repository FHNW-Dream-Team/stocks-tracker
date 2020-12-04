package fhnw.dreamteam.stockstracker.controller;

import fhnw.dreamteam.stockstracker.data.dtos.UserList;
import fhnw.dreamteam.stockstracker.data.models.Stock;
import fhnw.dreamteam.stockstracker.data.models.User;
import fhnw.dreamteam.stockstracker.data.seeddata.Seeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import fhnw.dreamteam.stockstracker.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> postRegister(@RequestBody User user) {
        try {
            userService.createOrUpdateUser(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

    @GetMapping(path = "/user", produces = "application/json")
    public UserList getUsers() {
        UserList userList = new UserList(userService.getAll());
        return userList;
    }

    @GetMapping(path = "/clientUser", produces = "application/json")
    public User getLoggendInUser() {
        User username = userService.getCurrentUser();
        return username;
    }

}

