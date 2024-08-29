package ecommerce.services.impl;

import com.google.common.base.Preconditions;
import ecommerce.models.Role;
import ecommerce.repositories.RoleRepository;
import ecommerce.services.AbstractRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements AbstractRoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public Role save(Role role) {
        Preconditions.checkNotNull(role, "The role is required");
        return repository.save(role);
    }

    @Override
    public Role deleteBy(Long id) {
        Preconditions.checkNotNull(id, "The role id is required");
        Role role = getBy(id);
        repository.delete(role);
        return role;
    }

    @Override
    public Role getBy(Long id) {
        Preconditions.checkNotNull(id, "The role id is required");
        Role role = repository.findBy(id);
        Preconditions.checkNotNull(role, "No role found for this id");
        return role;
    }

    @Override
    public Role getBy(String name) {
        Preconditions.checkNotNull(name, "The name is required to find by name");
        Role role = repository.findBy(name);
        Preconditions.checkNotNull(role, "No role found for name: " + name);
        return role;
    }

    @Override
    public List<Role> listAll() {
        return repository.findAll();
    }

}
