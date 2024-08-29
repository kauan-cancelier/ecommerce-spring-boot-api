package ecommerce.repositories;

import ecommerce.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.id = :id")
    public Role findBy(@Param("id") Long id);

    @Query("SELECT r FROM Role r WHERE r.name = :name")
    public Role findBy(@Param("name") String name);

}
