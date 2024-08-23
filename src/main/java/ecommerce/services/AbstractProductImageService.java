package ecommerce.services;

import ecommerce.models.Product;
import ecommerce.models.ProductImage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

@Validated
public interface AbstractProductImageService {

    public ProductImage save(Long product_id, MultipartFile file);

    public ProductImage getBy(Long id);

    public ProductImage getByProduct(Long id);

    public ProductImage remove(Long id);


}
