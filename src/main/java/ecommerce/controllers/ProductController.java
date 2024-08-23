package ecommerce.controllers;

import com.google.common.base.Preconditions;
import ecommerce.models.Category;
import ecommerce.models.Product;
import ecommerce.models.ProductImage;
import ecommerce.services.AbstractCategoryService;
import ecommerce.services.AbstractProductImageService;
import ecommerce.services.AbstractProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private AbstractProductService productService;

    @Autowired
    private AbstractCategoryService categoryService;

    @Autowired
    private AbstractProductImageService imageService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product) {
        try {
            Product savedUser = productService.save(product);
            return ResponseEntity.created(URI.create("/products/" + savedUser.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/images")
    public ResponseEntity<ProductImage> uploadImage(
            @PathVariable("id") Long productId,
            @RequestParam("image") MultipartFile file) {
        ProductImage image = imageService.save(productId, file);
        return ResponseEntity.ok(image);
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<?> getImage(@PathVariable("id") Long productId) {
        try {
            ProductImage productImage = imageService.getByProduct(productId);

            Path imagePath = Paths.get(productImage.getPath());
            Resource resource = new UrlResource(imagePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + productImage.getPath() + "\"")
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteBy(@PathVariable("id") Long id) {
        try {
            Product product = productService.deleteBy(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Product product) {
        try {
            Preconditions.checkNotNull(product.getId(), "The id is required to edit product");
            productService.save(product);
            return ResponseEntity.ok().location(URI.create("/products/" + product.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findBy(@PathVariable("id") Long id) {
        try {
            Product product = productService.getBy(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


   @GetMapping
   public ResponseEntity<?> listAll(@RequestParam(value = "category", required = false) String categoryName) {
        try {
            if (!categoryName.isBlank()) {
                Category category = categoryService.getBy(categoryName);
                List<Product> products = productService.listBy(category);
                System.out.println(products);
                return ResponseEntity.ok(products);
            }
            List<Product> products = productService.listAll();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
