package ecommerce.repositories;

import ecommerce.models.Permission;
import ecommerce.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query("SELECT p FROM Permission p WHERE p.id = :id")
    public Permission findBy(Long id);

    @Query("SELECT p FROM Permission p WHERE p.role = :role")
    public List<Permission> listBy(@Param("role") Role role);

}
