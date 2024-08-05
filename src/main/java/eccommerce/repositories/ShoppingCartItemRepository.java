package eccommerce.repositories;

import eccommerce.models.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

    @Query("SELECT si FROM ShoppingCartItem si WHERE si.id = :id")
    public ShoppingCartItem findBy(@Param("id") Long id);

}
