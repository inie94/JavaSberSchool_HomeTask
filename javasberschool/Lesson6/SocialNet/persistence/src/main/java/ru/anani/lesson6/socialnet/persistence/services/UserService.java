package ru.anani.lesson6.socialnet.persistence.services;

import ru.anani.lesson6.socialnet.domain.User;
import ru.anani.lesson6.socialnet.persistence.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository repository = new UserRepository();

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(long id) {
        if (repository.contains(id))
            return repository.findById(id);
        return null;
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }
}
