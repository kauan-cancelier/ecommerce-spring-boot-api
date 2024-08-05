package eccommerce;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import eccommerce.models.Category;
import eccommerce.models.Product;
import eccommerce.models.User;
import eccommerce.services.AbstractCategoryService;
import eccommerce.services.AbstractProductService;
import eccommerce.services.AbstractUserService;
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

            productService.listAll().forEach(p -> {
                System.out.println(p.getName());
            });

            System.out.println("Running.");
        };
    }
}
