package ru.anani.lesson6.socialnet.persistence.repository;

import ru.anani.lesson6.socialnet.domain.User;

import java.util.*;

public class UserRepository {
    private final Map<Long, User> userDb = new HashMap<>();

    {
        userDb.put(1L, new User(1, "user1@mail.com", "qwerty", "Jhon", "James", "Male", new Date(1684654865)));
        userDb.put(2L, new User(2, "user2@mail.com", "qwerty", "Ellie", "Smith", "Male", new Date(1684654865)));
        userDb.put(3L, new User(3, "user3@mail.com", "qwerty", "Max", "Dalton", "Male", new Date(1684654865)));
        userDb.put(4L, new User(4, "user4@mail.com", "qwerty", "Marcus", "Michelson", "Male", new Date(1684654865)));
        userDb.put(5L, new User(5, "user5@mail.com", "qwerty", "Lion", "Jarvis", "Male", new Date(1684654865)));
        userDb.put(11L, new User(11, "user11@mail.com", "qwerty", "Lion", "Jarvis", "Male", new Date(1684654865)));
    }

    public List<User> findAll() {
        return new ArrayList<User>(userDb.values());
    }

    public boolean contains(long id) {
        return userDb.containsKey(id);
    }

    public boolean contains(User user) {
        return userDb.containsValue(user);
    }

    public User findById(long id) {
        return userDb.get(id);
    }

    public User findByEmail(String email) {
        return userDb.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .get();
    }

    public List<User> findAllByFirstname(String firstname) {
        return (List<User>) userDb.values().stream()
                .filter(user -> user.getFirstname().equals(firstname));
    }

    public List<User> findAllByLastname(String lastname) {
        return (List<User>) userDb.values().stream()
                .filter(user -> user.getLastname().equals(lastname));
    }
}
