package org.factoriaf5.pizzeriapaca.users;

import java.util.List;

import org.factoriaf5.pizzeriapaca.profiles.Profile;
import org.factoriaf5.pizzeriapaca.profiles.ProfileService;
import org.factoriaf5.pizzeriapaca.roles.Role;
import org.factoriaf5.pizzeriapaca.roles.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ProfileService profileService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService, ProfileService profileService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.profileService = profileService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User saveUser(User user, Set<String> roleNames, String email) {
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        Set<Role> roles = roleNames.stream()
                .map(roleService::getRoleByName) 
                .collect(Collectors.toSet());
        user.setRoles(roles);

        
        User savedUser = userRepository.save(user);
        
       
        Profile profile = new Profile(email, savedUser);
        profileService.save(profile);

        
        return savedUser;
    }

    public Optional<User> updateUser(Long id, UserDto userDto, Set<String> roleNames, String email) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(userDto.getUsername());
            user.setPassword(passwordEncoder.encode(userDto.getPassword())); 

            
            Set<Role> roles = roleNames.stream()
                .map(roleService::getRoleByName)
                .collect(Collectors.toSet());
            user.setRoles(roles);

            
            User updatedUser = userRepository.save(user);

           
            Profile profile = profileService.save(new Profile(email, updatedUser));

            return Optional.of(updatedUser);
        } else {
            return Optional.empty();
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
