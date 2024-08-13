package ecommerce.controllers;

import com.google.common.base.Preconditions;
import ecommerce.models.Order;
import ecommerce.services.AbstractOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private AbstractOrderService service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Order order) {
        try {
            ecommerce.models.Order savedUser = service.save(order);
            return ResponseEntity.created(URI.create("/orders/" + savedUser.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteBy(@PathVariable("id") Long id) {
        try {
            ecommerce.models.Order order = service.deleteBy(id);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ecommerce.models.Order order) {
        try {
            Preconditions.checkNotNull(order.getId(), "The id is required to edit order");
            service.save(order);
            return ResponseEntity.ok().location(URI.create("/orders/" + order.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findBy(@PathVariable("id") Long id) {
        try {
            ecommerce.models.Order order = service.getBy(id);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        try {
            List<Order> orders = service.listAll();
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
