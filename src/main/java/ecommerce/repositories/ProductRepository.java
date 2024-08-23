package ecommerce.repositories;

import ecommerce.models.Category;
import ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.id = :id")
    public Product findBy(@Param("id") Long id);

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    public List<Product> listBy(@Param("category") Category category);

}
