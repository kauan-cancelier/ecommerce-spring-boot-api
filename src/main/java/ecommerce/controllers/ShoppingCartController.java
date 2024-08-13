package ecommerce.controllers;

import com.google.common.base.Preconditions;
import ecommerce.models.ShoppingCart;
import ecommerce.services.AbstractShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    @Autowired
    private AbstractShoppingCartService service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ShoppingCart shoppingCart) {
        try {
            ecommerce.models.ShoppingCart savedUser = service.save(shoppingCart);
            return ResponseEntity.created(URI.create("/shopping-carts/" + savedUser.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteBy(@PathVariable("id") Long id) {
        try {
            ecommerce.models.ShoppingCart shoppingCart = service.deleteBy(id);
            return ResponseEntity.ok(shoppingCart);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ecommerce.models.ShoppingCart shoppingCart) {
        try {
            Preconditions.checkNotNull(shoppingCart.getId(), "The id is required to edit shopping cart");
            service.save(shoppingCart);
            return ResponseEntity.ok().location(URI.create("/shopping-carts/" + shoppingCart.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findBy(@PathVariable("id") Long id) {
        try {
            ecommerce.models.ShoppingCart shoppingCart = service.getBy(id);
            return ResponseEntity.ok(shoppingCart);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        try {
            List<ShoppingCart> shoppingCarts = service.listAll();
            return ResponseEntity.ok(shoppingCarts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
