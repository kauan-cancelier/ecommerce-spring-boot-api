package eccommerce.services;


import eccommerce.models.Order;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface AbstractOrderService {

    public Order save(Order order);

    public Order deleteBy(Long id);

    public Order getBy(Long id);

    public List<Order> listAll();

}
