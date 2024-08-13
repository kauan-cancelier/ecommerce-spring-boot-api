package ecommerce.controllers;

import com.google.common.base.Preconditions;
import ecommerce.models.ShoppingCartItem;
import ecommerce.services.AbstractCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("shopping-cart-items")
public class ShoppingCartItemController {

    @Autowired
    private AbstractCartItemService service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ShoppingCartItem shoppingCartItem) {
        try {
            ecommerce.models.ShoppingCartItem savedUser = service.save(shoppingCartItem);
            return ResponseEntity.created(URI.create("/shopping-carts-items/" + savedUser.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteBy(@PathVariable("id") Long id) {
        try {
            ecommerce.models.ShoppingCartItem shoppingCartItem = service.deleteBy(id);
            return ResponseEntity.ok(shoppingCartItem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ecommerce.models.ShoppingCartItem shoppingCartItem) {
        try {
            Preconditions.checkNotNull(shoppingCartItem.getId(), "The id is required to edit shopping cart item");
            service.save(shoppingCartItem);
            return ResponseEntity.ok().location(URI.create("/shopping-carts-items/" + shoppingCartItem.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findBy(@PathVariable("id") Long id) {
        try {
            ecommerce.models.ShoppingCartItem shoppingCartItem = service.getBy(id);
            return ResponseEntity.ok(shoppingCartItem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        try {
            List<ShoppingCartItem> shoppingCartItems = service.listAll();
            return ResponseEntity.ok(shoppingCartItems);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
