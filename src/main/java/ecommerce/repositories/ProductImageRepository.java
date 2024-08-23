package ecommerce.repositories;

import ecommerce.models.Product;
import ecommerce.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    @Query("SELECT p FROM ProductImage p WHERE p.id = :id")
    public ProductImage findBy(@Param("id") Long id);

    @Query("SELECT p FROM ProductImage p WHERE p.product = :product")
    public ProductImage findByProduct(@Param("product") Product product);

}
