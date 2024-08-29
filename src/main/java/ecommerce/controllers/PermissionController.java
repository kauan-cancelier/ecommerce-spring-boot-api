package ecommerce.controllers;

import com.google.common.base.Preconditions;
import ecommerce.models.Category;
import ecommerce.models.Permission;
import ecommerce.models.Product;
import ecommerce.models.Role;
import ecommerce.services.AbstractPermissionService;
import ecommerce.services.AbstractRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private AbstractPermissionService permissionService;

    @Autowired
    private AbstractRoleService roleService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Permission permission) {
        try {
            Permission savedPermission = permissionService.save(permission);
            return ResponseEntity.created(URI.create("/permissions/" + savedPermission.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteBy(@PathVariable("id") Long id) {
        try {
            Permission permission = permissionService.deleteBy(id);
            return ResponseEntity.ok(permission);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Permission permission) {
        try {
            Preconditions.checkNotNull(permission.getId(), "The id is required to edit permission");
            permissionService.save(permission);
            return ResponseEntity.ok().location(URI.create("/permissions/" + permission.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findBy(@PathVariable("id") Long id) {
        try {
            Permission permission = permissionService.getBy(id);
            return ResponseEntity.ok(permission);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(value = "role", required = false) String roleName) {
        try {
            if (!roleName.isBlank()) {
                Role role = roleService.getBy(roleName);
                List<Permission> permissions = permissionService.listBy(role);
                return ResponseEntity.ok(permissions);
            }
            List<Permission> permissions = permissionService.listAll();
            return ResponseEntity.ok(permissions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
