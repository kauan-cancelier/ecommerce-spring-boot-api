package ecommerce.services.impl;

import com.google.common.base.Preconditions;
import ecommerce.models.Permission;
import ecommerce.models.Role;
import ecommerce.repositories.PermissionRepository;
import ecommerce.services.AbstractPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService implements AbstractPermissionService {

    @Autowired
    private PermissionRepository repository;

    @Override
    public Permission save(Permission permission) {
        Preconditions.checkNotNull(permission, "The permission is required to save");
        return repository.save(permission);
    }

    @Override
    public Permission deleteBy(Long id) {
        Preconditions.checkNotNull(id, "The permission id is required");
        Permission permission = getBy(id);
        repository.delete(permission);
        return permission;
    }

    @Override
    public Permission getBy(Long id) {
        Preconditions.checkNotNull(id, "The permission id is required");
        Permission permission = repository.findBy(id);
        Preconditions.checkNotNull(permission, "No permission found for this id");
        return permission;
    }

    @Override
    public List<Permission> listBy(Role role) {
        List<Permission> permissions = repository.listBy(role);
        return permissions;
    }

    @Override
    public List<Permission> listAll() {
        return repository.findAll();
    }
}
