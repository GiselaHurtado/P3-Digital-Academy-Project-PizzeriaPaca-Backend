package org.factoriaf5.pizzeriapaca.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/{id}")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto, 
                                           @RequestParam Set<String> roles, 
                                           @RequestParam String email) {
        User newUser = new User(userDto.getUsername(), userDto.getPassword());
        User savedUser = userService.saveUser(newUser, roles, email);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, 
                                           @RequestBody UserDto userDto,
                                           @RequestParam Set<String> roles, 
                                           @RequestParam String email) {
        Optional<User> updatedUser = userService.updateUser(id, userDto, roles, email);
        return updatedUser
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
