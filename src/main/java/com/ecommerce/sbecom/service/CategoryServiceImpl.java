package com.ecommerce.sbecom.service;

import com.ecommerce.sbecom.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        boolean isDeleted = categories.removeIf(c -> c.getCategoryId().equals(categoryId));
        if (!isDeleted) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category: " + categoryId + " : not found");
    }

    @Override
    public void updateCategory(Long categoryId, Category category) {
        boolean isUpdated = false;
        for (Category c : categories) {
            if (c.getCategoryId().equals(categoryId)) {
                c.setCategoryName(category.getCategoryName());
                isUpdated = true;
                break;
            }
        }
        if (!isUpdated) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category: " + categoryId + " : not found");
    }
}
