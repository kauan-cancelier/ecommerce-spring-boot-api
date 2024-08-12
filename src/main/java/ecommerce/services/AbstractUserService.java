package ecommerce.services;

import ecommerce.models.User;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface AbstractUserService {

    public User save(User user);

    public User deleteBy(Long id);

    public User getBy(Long id);

    public User getBy(String email);

    public List<User> listAll();

    public List<User> listBy(String role);

}
