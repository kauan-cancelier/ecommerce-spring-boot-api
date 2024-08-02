package eccommerce.services.impl;

import com.google.common.base.Preconditions;
import eccommerce.models.User;
import eccommerce.repositories.UserRepository;
import eccommerce.services.AbstractUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements AbstractUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        Preconditions.checkNotNull(user, "The users is required");
        return repository.save(user);
    }

    @Override
    public User deleteBy(Long id) {
        Preconditions.checkNotNull(id, "The user id is required");
        User user = getBy(id);
        repository.delete(user);
        return user;
    }

    @Override
    public User getBy(Long id) {
        Preconditions.checkNotNull(id, "The user id is required");
        User user = repository.findBy(id);
        Preconditions.checkNotNull(user, "No users found for this id");
        return user;
    }

    @Override
    public User getBy(String email) {
        Preconditions.checkNotNull(email, "The user email is required");
        User user = repository.findBy(email);
        Preconditions.checkNotNull(user, "No users found for this email");
        return user;
    }

    @Override
    public List<User> listAll() {
        return repository.findAll();
    }

    @Override
    public List<User> listBy(String role) {
        return repository.listBy(role);
    }
}
