package com.chatapp.pojo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class UserTest {

    private static User user;

    @BeforeAll
    static void setUp() {
        user = new User();
        user.setId("some_id");
        user.setEmail("some_email");
        user.setName("some_name");
        user.setSurname("some_surname");
        user.setPassword("some_password");
    }

    @Test
    public void testGetId() {
        Assertions.assertEquals("some_id", user.getId());
    }
    @Test
    public void testGetName() {
        Assertions.assertEquals("some_name", user.getName());
    }

    @Test
    public void testGetSurname() {
        Assertions.assertEquals("some_surname", user.getSurname());
    }

    @Test
    public void testGetEmail() {
        Assertions.assertEquals("some_email", user.getEmail());
    }

    @Test
    public void testGetPassword() {
        Assertions.assertEquals("some_password", user.getPassword());
    }
}
