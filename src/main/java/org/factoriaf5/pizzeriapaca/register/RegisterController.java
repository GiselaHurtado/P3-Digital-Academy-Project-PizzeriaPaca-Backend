package org.factoriaf5.pizzeriapaca.register;

import java.util.HashMap;
import java.util.Map;

import org.factoriaf5.pizzeriapaca.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "${api-endpoint}/register")
public class RegisterController {

    private final RegisterService service;

    public RegisterController(RegisterService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> register(
            @Valid @RequestHeader("username") String username,
            @RequestHeader("password") String password,
            @RequestHeader("email") String email,
            @CookieValue(value = "JSESSIONID", required = false) String jsessionid) {
      

        RegisterDto newUser = new RegisterDto();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);

      
        if (jsessionid != null) {
            System.out.println("JSESSIONID cookie: " + jsessionid);
        }

        User user = service.save(newUser);

        Map<String, String> json = new HashMap<>();
        json.put("message", "Register successful");
        json.put("username", user.getUsername());

        return ResponseEntity.status(HttpStatus.CREATED).body(json);
    }
}
