package eccommerce.services.impl;

import com.google.common.base.Preconditions;
import eccommerce.models.Product;
import eccommerce.models.ShoppingCart;
import eccommerce.repositories.ShoppingCartRepository;
import eccommerce.services.AbstractShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService implements AbstractShoppingCartService {

    @Autowired
    private ShoppingCartRepository repository;

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        Preconditions.checkNotNull(shoppingCart, "The shopping cart is required to save.");
        return repository.save(shoppingCart);
    }

    @Override
    public ShoppingCart deleteBy(Long id) {
        Preconditions.checkNotNull(id, "The shopping cart id is required.");
        ShoppingCart shoppingCart = getBy(id);
        repository.delete(shoppingCart);
        return shoppingCart;
    }

    @Override
    public ShoppingCart getBy(Long id) {
        Preconditions.checkNotNull(id, "The shopping cart id is required");
        ShoppingCart shoppingCart = repository.findBy(id);
        Preconditions.checkNotNull(shoppingCart, "No shopping cart found for this id");
        return shoppingCart;
    }

    @Override
    public List<ShoppingCart> listAll() {
        return repository.findAll();
    }
}
