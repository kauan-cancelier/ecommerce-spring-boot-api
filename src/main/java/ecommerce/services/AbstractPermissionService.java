package ecommerce.services;

import ecommerce.models.Order;
import ecommerce.models.Permission;
import ecommerce.models.Role;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface AbstractPermissionService {

    public Permission save(Permission permission);

    public Permission deleteBy(Long id);

    public Permission getBy(Long id);

    public List<Permission> listBy(Role role);

    public List<Permission> listAll();


}
