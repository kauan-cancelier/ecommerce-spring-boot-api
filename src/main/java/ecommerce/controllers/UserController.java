package ecommerce.controllers;

import com.google.common.base.Preconditions;
import ecommerce.dto.UserDTO;
import ecommerce.models.User;
import ecommerce.services.AbstractUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AbstractUserService service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        try {
            User savedUser = service.save(user);
            return ResponseEntity.created(URI.create("/users/" + savedUser.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteBy(@PathVariable("id") Long id) {
        try {
            User user = service.deleteBy(id);
            UserDTO transferUser = UserDTO.fromEntity(user);
            return ResponseEntity.ok(transferUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user) {
        try {
            Preconditions.checkNotNull(user.getId(), "The id is required to edit user");
            service.save(user);
            return ResponseEntity.ok().location(URI.create("/users/" + user.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findBy(@PathVariable("id") Long id) {
        try {
            User user = service.getBy(id);
            UserDTO transferUser = UserDTO.fromEntity(user);
            return ResponseEntity.ok(transferUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        try {
            List<User> users = service.listAll();
            List<UserDTO> transferUsers = UserDTO.fromListEntity(users);
            return ResponseEntity.ok(transferUsers);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
