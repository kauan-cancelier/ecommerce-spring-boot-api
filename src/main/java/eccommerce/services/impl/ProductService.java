package eccommerce.services.impl;

import com.google.common.base.Preconditions;
import eccommerce.models.Product;
import eccommerce.repositories.ProductRepository;
import eccommerce.services.AbstractProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements AbstractProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Product save(Product product) {
        Preconditions.checkNotNull(product, "The product is required");
        return repository.save(product);
    }

    @Override
    public Product deleteBy(Long id) {
        Preconditions.checkNotNull(id, "The product id is required");
        Product product = getBy(id);
        repository.delete(product);
        return product;
    }

    @Override
    public Product getBy(Long id) {
        Preconditions.checkNotNull(id, "The product id is required");
        Product product = repository.findBy(id);
        Preconditions.checkNotNull(product, "No product found for this id");
        return product;
    }

    @Override
    public List<Product> listAll() {
        return repository.findAll();
    }
}
