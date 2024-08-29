package ecommerce.services;

import ecommerce.models.Role;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface AbstractRoleService {

    public Role save(Role role);

    public Role deleteBy(Long id);

    public Role getBy(Long id);

    public Role getBy(String name);

    public List<Role> listAll();

}
