package org.factoriaf5.pizzeriapaca.roles;

import org.factoriaf5.pizzeriapaca.roles.exceptions.RoleNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    
    RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role getById(Long id) {
        Role role = repository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found"));
        return role;
    }

    public Role getRoleByName(String roleName) {
        return repository.findByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with name: " + roleName));
    }
}
