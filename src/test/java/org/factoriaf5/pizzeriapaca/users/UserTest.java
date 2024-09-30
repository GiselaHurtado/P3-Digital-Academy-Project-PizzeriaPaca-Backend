package org.factoriaf5.pizzeriapaca.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.factoriaf5.pizzeriapaca.profiles.Profile;
import org.factoriaf5.pizzeriapaca.roles.Role;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void testUserId() {
        User user = new User();
        user.setId(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    void testUsername() {
        User user = new User();
        user.setUsername("testuser");
        assertEquals("testuser", user.getUsername());
    }

    @Test
    void testPassword() {
        User user = new User();
        user.setPassword("password");
        assertEquals("password", user.getPassword());
    }

    @Test
    void testProfile() {
        User user = new User();
        Profile profile = new Profile();
        user.setProfile(profile);
        assertEquals(profile, user.getProfile());
    }

    @Test
    void testRoles() {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        user.setRoles(roles);
        assertEquals(roles, user.getRoles());
    }

    @Test
    void testUserConstructor() {
        User user = new User("testuser", "password");
        assertEquals("testuser", user.getUsername());
        assertEquals("password", user.getPassword());
        assertNull(user.getProfile());
    }
}
