package ecommerce.services.impl;

import com.google.common.base.Preconditions;
import ecommerce.models.ShoppingCartItem;
import ecommerce.repositories.ShoppingCartItemRepository;
import ecommerce.services.AbstractCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartItemService implements AbstractCartItemService  {

    @Autowired
    private ShoppingCartItemRepository repository;

    @Override
    public ShoppingCartItem save(ShoppingCartItem product) {
        Preconditions.checkNotNull(product, "The shopping cart item is required");
        return repository.save(product);
    }

    @Override
    public ShoppingCartItem deleteBy(Long id) {
        Preconditions.checkNotNull(id, "The shopping cart item id is required");
        ShoppingCartItem shoppingCartItem = getBy(id);
        repository.delete(shoppingCartItem);
        return shoppingCartItem;
    }

    @Override
    public ShoppingCartItem getBy(Long id) {
        Preconditions.checkNotNull(id, "The shopping cart item id is required");
        ShoppingCartItem shoppingCartItem = repository.findBy(id);
        Preconditions.checkNotNull(shoppingCartItem, "No shopping cart item found for this id");
        return shoppingCartItem;
    }

    @Override
    public List<ShoppingCartItem> listAll() {
        return repository.findAll();
    }
}
