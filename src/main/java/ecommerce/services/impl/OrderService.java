package ecommerce.services.impl;

import com.google.common.base.Preconditions;
import ecommerce.models.Order;
import ecommerce.repositories.OrderRepository;
import ecommerce.services.AbstractOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements AbstractOrderService {

    @Autowired
    private OrderRepository repository;

    @Override
    public Order save(Order order) {
        Preconditions.checkNotNull(order, "The order is required");
        return repository.save(order);
    }

    @Override
    public Order deleteBy(Long id) {
        Preconditions.checkNotNull(id, "The product id is required");
        Order order = getBy(id);
        repository.delete(order);
        return order;
    }

    @Override
    public Order getBy(Long id) {
        Preconditions.checkNotNull(id, "The product id is required");
        Order order = repository.findBy(id);
        Preconditions.checkNotNull(order, "No product found for this id");
        return order;
    }

    @Override
    public List<Order> listAll() {
        return repository.findAll();
    }

}
