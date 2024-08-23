package ecommerce.services.impl;

import com.google.common.base.Preconditions;
import ecommerce.models.Category;
import ecommerce.repositories.CategoryRepository;
import ecommerce.services.AbstractCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements AbstractCategoryService  {

    @Autowired
    private CategoryRepository repository;

    @Override
    public Category save(Category category) {
        Preconditions.checkNotNull(category, "The category is required");
        return repository.save(category);
    }

    @Override
    public Category deleteBy(Long id) {
        Preconditions.checkNotNull(id, "The category id is required");
        Category category = getBy(id);
        repository.delete(category);
        return category;
    }

    @Override
    public Category getBy(Long id) {
        Preconditions.checkNotNull(id, "The category id is required");
        Category category = repository.findBy(id);
        Preconditions.checkNotNull(category, "No category found for this id");
        return category;
    }

    @Override
    public Category getBy(String name) {
        Preconditions.checkNotNull(name, "The category name is required");
        Category category = repository.findBy(name);
        Preconditions.checkNotNull(category, "No category found for this name");
        return category;
    }

    @Override
    public List<Category> listAll() {
        return repository.findAll();
    }
}
