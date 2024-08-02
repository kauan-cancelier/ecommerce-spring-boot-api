package eccommerce.repositories;

import eccommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.id = :id")
    public User findBy(Long id);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User findBy(String email);

    @Query("SELECT u FROM User u WHERE u.role = :role")
    public List<User> listBy(String role);

}
