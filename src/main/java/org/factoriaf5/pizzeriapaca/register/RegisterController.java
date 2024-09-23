package org.factoriaf5.pizzeriapaca.register;

import java.util.HashMap;
import java.util.Map;

import org.factoriaf5.pizzeriapaca.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping(path = "${api-endpoint}/register")
public class RegisterController {

    private final RegisterService service;

    public RegisterController(RegisterService service) {
        this.service = service;
    }

    @PostMapping
     public ResponseEntity<?> registerUser(@RequestHeader("newUser") String newUserHeader) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        RegisterDto newUser = objectMapper.readValue(newUserHeader, RegisterDto.class);
    
        User user = service.save(newUser);

        Map<String, String> json = new HashMap<>();
        json.put("message", "Register successful");
        json.put("username", user.getUsername());

        return ResponseEntity.status(HttpStatus.CREATED).body(json);
    }
}
