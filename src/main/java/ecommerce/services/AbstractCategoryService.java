package ecommerce.services;

import ecommerce.models.Category;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface AbstractCategoryService {

    public Category save(Category category);

    public Category deleteBy(Long id);

    public Category getBy(Long id);

    public List<Category> listAll();

}
