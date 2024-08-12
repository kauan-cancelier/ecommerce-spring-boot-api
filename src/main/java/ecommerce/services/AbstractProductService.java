package ecommerce.services;

import ecommerce.models.Product;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface AbstractProductService {

    public Product save(Product product);

    public Product deleteBy(Long id);

    public Product getBy(Long id);

    public List<Product> listAll();

}
