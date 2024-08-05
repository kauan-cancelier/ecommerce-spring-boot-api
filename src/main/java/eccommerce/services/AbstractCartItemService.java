package eccommerce.services;

import eccommerce.models.ShoppingCartItem;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface AbstractCartItemService {

    public ShoppingCartItem save(ShoppingCartItem shoppingCartItem);

    public ShoppingCartItem deleteBy(Long id);

    public ShoppingCartItem getBy(Long id);

    public List<ShoppingCartItem> listAll();
}
