package eccommerce.services.impl;

import com.google.common.base.Preconditions;
import eccommerce.models.Address;
import eccommerce.repositories.AddressRepository;
import eccommerce.services.AbstractAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements AbstractAddressService {

    @Autowired
    private AddressRepository repository;

    @Override
    public Address save(Address address) {
        Preconditions.checkNotNull(address, "The address is required");
        return repository.save(address);
    }

    @Override
    public Address deleteBy(Long id) {
        Preconditions.checkNotNull(id, "The product id is required");
        Address address = getBy(id);
        repository.delete(address);
        return address;
    }

    @Override
    public Address getBy(Long id) {
        Preconditions.checkNotNull(id, "The product id is required");
        Address address = repository.findBy(id);
        Preconditions.checkNotNull(address, "No product found for this id");
        return address;
    }

    @Override
    public List<Address> listAll() {
        return repository.findAll();
    }
}
