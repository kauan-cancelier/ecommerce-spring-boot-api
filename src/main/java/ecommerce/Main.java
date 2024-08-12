package ecommerce;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import ecommerce.models.*;
import ecommerce.models.enums.OrderStatus;
import ecommerce.models.enums.ResidenceType;
import ecommerce.models.enums.SendType;
import ecommerce.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public Hibernate5JakartaModule jsonHibernate5Module() {
        return new Hibernate5JakartaModule();
    }

    @Autowired
    private AbstractUserService userService;

    @Autowired
    private AbstractCategoryService categoryService;

    @Autowired
    private AbstractProductService productService;

    @Autowired
    private AbstractShoppingCartService shoppingCartService;

    @Autowired
    private AbstractCartItemService cartItemService;

    @Autowired
    private AbstractOrderService orderService;

    @Autowired
    private AbstractAddressService addressService;

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            User user = new User();
            user.setName("Kauan");
            user.setEmail("kauan@gmail.com");
            user.setPassword("12345678");
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            user.setRole("client");
            userService.save(user);

            Category category = new Category();
            category.setName("pct");
            category.setDescription("pct description");
            Category savedCategory = categoryService.save(category);

            Product product = new Product();
            product.setCategory(categoryService.getBy(savedCategory.getId()));
            product.setName("Tenis");
            product.setDescription("Descrição");
            product.setPrice(BigDecimal.valueOf(450));
            product.setStock(BigDecimal.valueOf(450));
            productService.save(product);

            product = new Product();
            product.setCategory(categoryService.getBy(savedCategory.getId()));
            product.setName("Roupa");
            product.setDescription("Descrição 2");
            product.setPrice(BigDecimal.valueOf(1450));
            product.setStock(BigDecimal.valueOf(1450));
            productService.save(product);

            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setTotalPrice(BigDecimal.valueOf(123));
            shoppingCartService.save(shoppingCart);


            for (Product p : productService.listAll()) {
                ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                shoppingCartItem.setShoppingCart(shoppingCart);
                shoppingCartItem.setProduct(p);
                cartItemService.save(shoppingCartItem);
            }

            Address address = new Address();
            address.setAddressType("Casa");
            address.setCity("Tubarão");
            address.setPostalCode("88703-656");
            address.setNeighborhood("Sertão");
            address.setNumber("102B");
            address.setResidenceType(ResidenceType.House);
            address.setUser(user);
            address.setComplement("Ao lado de ");
            address.setState("Sc");
            address.setStreet("João bissoni");
            addressService.save(address);

            Order order = new Order();
            order.setNumber("NF45134");
            order.setUser(user);
            order.setShippingAddress(address);
            order.setTrackingNumber("1235644GJ2A");
            order.setShoppingCart(shoppingCart);
            order.setStatus(OrderStatus.NEW);
            order.setSendType(SendType.CORREIOS);
            order.setObservations("Entregar antes das 18 horas");
            order.setDiscount(BigDecimal.valueOf(5));
            order.setOrderDate(LocalDateTime.now());
            orderService.save(order);

            System.out.println("Running.");
        };
    }
}
