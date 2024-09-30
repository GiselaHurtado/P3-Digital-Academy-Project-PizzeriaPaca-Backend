package org.factoriaf5.pizzeriapaca.register;

import java.util.HashSet;
import java.util.Arrays;

import org.factoriaf5.pizzeriapaca.users.User;
import org.factoriaf5.pizzeriapaca.roles.Role;
import org.factoriaf5.pizzeriapaca.roles.RoleRepository;
import org.factoriaf5.pizzeriapaca.roles.exceptions.RoleNotFoundException;
import org.factoriaf5.pizzeriapaca.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public User save(RegisterDto registerDto) {
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RoleNotFoundException("Role not found: ROLE_USER"));
        Role cocinaRole = roleRepository.findByName("ROLE_COCINA")
                .orElseThrow(() -> new RoleNotFoundException("Role not found: ROLE_COCINA"));
        Role motoristaRole = roleRepository.findByName("ROLE_MOTORISTA")
                .orElseThrow(() -> new RoleNotFoundException("Role not found: ROLE_MOTORISTA"));
        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RoleNotFoundException("Role not found: ROLE_ADMIN"));
        user.setRoles(new HashSet<>(Arrays.asList(userRole, cocinaRole, motoristaRole, adminRole)));

        userRepository.save(user);

        Customer customer = new Customer();
        customer.setUsername(registerDto.getUsername());
        customer.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        customer.setEmail(registerDto.getEmail());
        customer.setFirstName(registerDto.getFirstName());
        customer.setLastName(registerDto.getLastName());

        Address address = new Address();
        address.setUser(user);
        address.setCustomer(customer);
        address.setAddress(registerDto.getAddress());
        address.setPostalCode(registerDto.getPostalCode());
        address.setCity(registerDto.getCity());

        customer.addAddress(address);
        customerRepository.save(customer);

        return user;
    }
}
