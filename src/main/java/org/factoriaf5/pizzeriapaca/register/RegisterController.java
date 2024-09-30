package org.factoriaf5.pizzeriapaca.register;

import java.util.HashMap;
import java.util.Map;

import org.factoriaf5.pizzeriapaca.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping(path = "${api-endpoint}/register")
public class RegisterController {

    private final RegisterService service;

    public RegisterController(RegisterService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> register(
            @RequestHeader String username,
            @RequestHeader String password,
            @RequestHeader String email,
            @RequestHeader String firstName,
            @RequestHeader String lastName,
            @RequestHeader String address,
            @RequestHeader String postalCode,
            @RequestHeader String city) {

        RegisterDto newUser = new RegisterDto(username, password, email, firstName, lastName, address, postalCode, city);
        User user = service.save(newUser);

        Map<String, String> json = new HashMap<>();
        json.put("message", "Register successful");
        json.put("username", user.getUsername());
        json.put("email", user.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(json);
    }
}
