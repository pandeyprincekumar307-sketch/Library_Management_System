package com.org.example.library_management_system.service;

import com.org.example.library_management_system.entity.Category;
import com.org.example.library_management_system.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAllCategory() {
        return this.repository.findAll();
    }

    public Category save(Category category) {
        return (Category)this.repository.save(category);
    }

    public Category findById(Long id) {
        return (Category)this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found with this id " + id));
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
