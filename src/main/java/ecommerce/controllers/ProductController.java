package ecommerce.controllers;

import com.google.common.base.Preconditions;
import ecommerce.models.Product;
import ecommerce.services.AbstractProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private AbstractProductService service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product) {
        try {
            Product savedUser = service.save(product);
            return ResponseEntity.created(URI.create("/products/" + savedUser.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteBy(@PathVariable("id") Long id) {
        try {
            Product product = service.deleteBy(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Product product) {
        try {
            Preconditions.checkNotNull(product.getId(), "The id is required to edit product");
            service.save(product);
            return ResponseEntity.ok().location(URI.create("/products/" + product.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findBy(@PathVariable("id") Long id) {
        try {
            Product product = service.getBy(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        try {
            List<Product> products = service.listAll();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
