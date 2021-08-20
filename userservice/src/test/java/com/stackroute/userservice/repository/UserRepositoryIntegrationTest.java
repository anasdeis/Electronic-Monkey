package com.stackroute.userservice.repository;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    private User user1, user2, user3;
    private List<User> userList;

    @BeforeEach
    void setUp() {
        user1 = new User(1, "Anas", "anas@cgi.com", true, "username", "password");
        user2 = new User(2, "Justin", "justin@hotmail.com", false, "username", "password");
        user3 = new User(3, "Justin", "justin@cgi.com", true, "username", "password");
        userList = new ArrayList<User>();
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        user1 = user2 = user3 = null;
        userList = null;
    }

    @Test
    public void givenUserToSaveThenShouldReturnSavedUser() throws UserAlreadyExistsException {
        User savedUser = userRepository.save(user1);
        assertNotNull(savedUser);
        assertEquals(user1, savedUser);
    }

    @Test
    public void givenGetAllUsersThenShouldReturnListOfAllUsers() {
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        List<User> users = (List<User>) userRepository.findAll();
        assertNotNull(users);
        assertEquals(userList, users);
    }

    @Test
    public void givenGetAllUsersByNameThenShouldReturnListOfAllRespectiveUsers() {
        userList.add(user2);
        userList.add(user3);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        List<User> users = userRepository.findByName(user2.getName());
        assertNotNull(users);
        assertEquals(userList, users);
    }

    @Test
    public void givenGetAllUsersByAdminThenShouldReturnListOfAllAdminUsers() {
        userList.add(user1);
        userList.add(user3);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        List<User> users = userRepository.findByAdmin(user1.isAdmin());
        assertNotNull(users);
        assertEquals(userList, users);
    }

    @Test
    public void givenUserIdThenShouldReturnRespectiveUser() throws UserNotFoundException {
        User savedUser = userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        User getUser = userRepository.findById(savedUser.getId()).get();
        assertNotNull(getUser);
        assertEquals(user1, getUser);
    }

    @Test
    public void givenUserEmailThenShouldReturnRespectiveUser() throws UserNotFoundException {
        User savedUser = userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        User getUser = userRepository.findByEmail(savedUser.getEmail()).get();
        assertNotNull(getUser);
        assertEquals(user1, getUser);
    }

    @Test
    void givenUserIdToDeleteThenShouldReturnDeletedUser() throws UserNotFoundException {
        User savedUser = userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.deleteById(savedUser.getId());
        userList.add(user2);
        userList.add(user3);
        assertNotNull(userList);
        assertEquals(userList, (List<User>)userRepository.findAll());
    }

    @Test
    public void givenUserToUpdateThenShouldReturnUpdatedUser() {
        User savedUser = userRepository.save(user1);
        assertNotNull(savedUser);
        assertEquals(user1.getEmail(), savedUser.getEmail());;
        savedUser.setPassword(user2.getPassword());
        assertEquals(true, userRepository.existsById(savedUser.getId()));
        User updatedUser = userRepository.save(savedUser);
        assertNotNull(savedUser);
        assertEquals(savedUser, updatedUser);
    }

    /******* VALIDATION *****/
    @Test
    void givenValidUserThenReturnRespectiveUser(){
        assertEquals(user1, userRepository.save(user1));
    }
    /*
    @Test
    void givenUserWithInvalidIdThenThrowsException() throws ConstraintViolationException{
        user1.setId(0);
        assertThrows(ConstraintViolationException.class, () -> {
            userRepository.save(user1);
            entityManager.flush();
        });
    }

    @Test
    void givenUserWithInvalidEmailThenThrowsException(){
        user1.setEmail("anas");
        assertThrows(ConstraintViolationException.class, () -> {
            userRepository.save(user1);
            entityManager.flush();
        });
    }

    @Test
    void givenUserWithInvalidNameThenThrowsException(){
        user1.setName("");
        assertThrows(ConstraintViolationException.class, () -> {
            userRepository.save(user1);
            entityManager.flush();
        });
    }

    @Test
    void givenUserWithInvalidUsernameThenThrowsException(){
        user1.setUsername("");
        assertThrows(ConstraintViolationException.class, () -> {
            userRepository.save(user1);
            entityManager.flush();
        });
    }

    @Test
    void givenUserWithInvalidPasswordThenThrowsException(){
        user1.setPassword("12345");
        assertThrows(ConstraintViolationException.class, () -> {
            userRepository.save(user1);
            entityManager.flush();
        });
    }*/
}