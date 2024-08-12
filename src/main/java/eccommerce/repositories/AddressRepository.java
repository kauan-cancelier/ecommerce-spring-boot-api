package eccommerce.repositories;

import eccommerce.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.id = :id")
    public Address findBy(@Param("id") Long id);

}
