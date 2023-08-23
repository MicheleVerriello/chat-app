package com.chatapp.pojo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class UserTest {

    private static User userComplexConstructor;
    private static User userSimpleConstructor;

    @BeforeAll
    static void setUp() {
        userComplexConstructor = new User("some_id", "some_name", "some_surname", "some_email", "some_password");
        userSimpleConstructor = new User();
    }

    @Test
    public void testSetId() {
        userSimpleConstructor.setId("some_id");
        Assertions.assertEquals("some_id", userSimpleConstructor.getId());
    }
    @Test
    public void testSetName() {
        userSimpleConstructor.setName("some_name");
        Assertions.assertEquals("some_name", userSimpleConstructor.getName());
    }

    @Test
    public void testSetSurname() {
        userSimpleConstructor.setSurname("some_surname");
        Assertions.assertEquals("some_surname", userSimpleConstructor.getSurname());
    }

    @Test
    public void testSetEmail() {
        userSimpleConstructor.setEmail("some_email");
        Assertions.assertEquals("some_email", userSimpleConstructor.getEmail());
    }

    @Test
    public void testSetPassword() {
        userSimpleConstructor.setPassword("some_password");
        Assertions.assertEquals("some_password", userSimpleConstructor.getPassword());
    }

    @Test
    public void testGetId() {
        Assertions.assertEquals("some_id", userComplexConstructor.getId());
    }
    @Test
    public void testGetName() {
        Assertions.assertEquals("some_name", userComplexConstructor.getName());
    }

    @Test
    public void testGetSurname() {
        Assertions.assertEquals("some_surname", userComplexConstructor.getSurname());
    }

    @Test
    public void testGetEmail() {
        Assertions.assertEquals("some_email", userComplexConstructor.getEmail());
    }

    @Test
    public void testGetPassword() {
        Assertions.assertEquals("some_password", userComplexConstructor.getPassword());
    }
}
