package ecommerce.services.impl;

import com.google.common.base.Preconditions;
import ecommerce.models.User;
import ecommerce.repositories.UserRepository;
import ecommerce.services.AbstractUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements AbstractUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        Preconditions.checkNotNull(user, "The user is required");
        return repository.saveAndFlush(user);
    }

    public User login(String email, String password) {
        System.out.println("Senha: " + password + " email: " + email);
        User user = repository.login(email, password);
        Preconditions.checkNotNull(user, "Email or password invalid");
        return user;
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
