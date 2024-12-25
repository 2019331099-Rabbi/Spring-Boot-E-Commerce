package com.ecommerce.sbecom.controller;

import com.ecommerce.sbecom.model.Category;
import com.ecommerce.sbecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//    @GetMapping("/public/categories")
    @RequestMapping(value = "/public/categories", method = RequestMethod.GET) // Alternative
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categories);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Category: " + category.getCategoryName() + " : created");
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Category: " + categoryId + " : deleted");
//            return new ResponseEntity<>("Category: " + categoryId + " : deleted", HttpStatus.OK);
//            return ResponseEntity.ok("Category: " + categoryId + " : deleted");
        } catch (ResponseStatusException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getReason());
        }
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        try {
            categoryService.updateCategory(categoryId, category);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Category: " + categoryId + " : updated");
        } catch (ResponseStatusException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getReason());
        }
    }
}
