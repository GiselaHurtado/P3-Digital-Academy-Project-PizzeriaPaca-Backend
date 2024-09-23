package org.factoriaf5.pizzeriapaca.users;

public class UserDto { 

    private final String username;
    private final String password;
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

}