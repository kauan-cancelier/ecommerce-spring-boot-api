package ecommerce.controllers;

import com.google.common.base.Preconditions;
import ecommerce.models.Address;
import ecommerce.services.AbstractAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AbstractAddressService service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Address address) {
        try {
            Address savedUser = service.save(address);
            return ResponseEntity.created(URI.create("/addresses/" + savedUser.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteBy(@PathVariable("id") Long id) {
        try {
            Address address = service.deleteBy(id);
            return ResponseEntity.ok(address);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Address address) {
        try {
            Preconditions.checkNotNull(address.getId(), "The id is required to edit address");
            service.save(address);
            return ResponseEntity.ok().location(URI.create("/addresses/" + address.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findBy(@PathVariable("id") Long id) {
        try {
            Address address = service.getBy(id);
            return ResponseEntity.ok(address);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        try {
            List<Address> addresses = service.listAll();
            return ResponseEntity.ok(addresses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
