package ecommerce.repositories;

import ecommerce.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    @Query("SELECT s FROM ShoppingCart s WHERE s.id = :id")
    public ShoppingCart findBy(@Param("id") Long id);

}
