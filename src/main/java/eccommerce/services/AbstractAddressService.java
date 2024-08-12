package eccommerce.services;

import eccommerce.models.Address;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface AbstractAddressService {

    public Address save(Address address);

    public Address deleteBy(Long id);

    public Address getBy(Long id);

    public List<Address> listAll();
}
