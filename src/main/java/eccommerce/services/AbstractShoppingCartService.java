package eccommerce.services;

import eccommerce.models.ShoppingCart;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface AbstractShoppingCartService {

    public ShoppingCart save(ShoppingCart shoppingCart);

    public ShoppingCart deleteBy(Long id);

    public ShoppingCart getBy(Long id);

    public List<ShoppingCart> listAll();


}
