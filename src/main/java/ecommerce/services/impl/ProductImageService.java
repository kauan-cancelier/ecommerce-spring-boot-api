package ecommerce.services.impl;

import com.google.common.base.Preconditions;
import ecommerce.models.Product;
import ecommerce.models.ProductImage;
import ecommerce.repositories.ProductImageRepository;
import ecommerce.services.AbstractProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ProductImageService implements AbstractProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductService productService;

    @Override
    public ProductImage save(Long productId, MultipartFile file) {
        Product product = productService.getBy(productId);
        ProductImage productImage = new ProductImage();
        try {
            productImage.setBytes(file.getBytes());
            productImage.setProduct(product);

            String userHome = System.getProperty("user.home");
            String directoryPath = userHome + "/Downloads/images/";
            String filePath = directoryPath + file.getOriginalFilename();
            Path directory = Paths.get(directoryPath);

            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            productImage.setPath(filePath);
            Path imagePath = Paths.get(filePath);
            Files.write(imagePath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar a imagem", e);
        }
        return productImageRepository.save(productImage);
    }

    @Override
    public ProductImage getBy(Long id) {
        ProductImage image = productImageRepository.findBy(id);
        Preconditions.checkNotNull(image, "No product image found for this id");
        return image;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductImage getByProduct(Long id) {
        Product product = productService.getBy(id);
        ProductImage image = productImageRepository.findByProduct(product);
        Preconditions.checkNotNull(image, "No product image found for this id");
        if (image.getBytes() != null) {
            byte[] imageData = image.getBytes();
        }
        return image;
    }


    @Override
    public ProductImage remove(Long id) {
        ProductImage image = getBy(id);
        productImageRepository.delete(image);
        return image;
    }

}
