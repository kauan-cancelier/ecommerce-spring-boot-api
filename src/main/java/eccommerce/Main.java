package eccommerce;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import eccommerce.models.*;
import eccommerce.services.*;
import eccommerce.services.impl.ProductService;
import eccommerce.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

            System.out.println("Running.");
        };
    }
}
