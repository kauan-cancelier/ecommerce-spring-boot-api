package ecommerce.repositories;

import ecommerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.id = :id")
    public Category findBy(@Param("id") Long id);


    @Query("SELECT c FROM Category c WHERE c.name = :name")
    public Category findBy(@Param("name") String name);

}
