package com.ecommerce.sbecom.service;

import com.ecommerce.sbecom.model.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);
    void deleteCategory(Long categoryId);
    void updateCategory(Long categoryId, Category category);
}
