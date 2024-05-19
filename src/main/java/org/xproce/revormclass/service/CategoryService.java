package org.xproce.revormclass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xproce.revormclass.dao.entities.Category;
import org.xproce.revormclass.dao.entities.Product;
import org.xproce.revormclass.dao.repositories.CategoryRep;
import org.xproce.revormclass.dao.repositories.ProductRepository;

import java.util.List;

@Service
public class CategoryService implements Categorymanager {

    @Autowired
    private CategoryRep categoryRep;

    @Override
    public Category addCategory(Category category) {
        return categoryRep.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRep.save(category);
    }

    @Override
    public Category getCategory(Integer Id) {
        return categoryRep.findById(Id).get();
    }

    @Override
    public boolean deleteCategory(Category category) {
        try {
            categoryRep.delete(category);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting product: " + e.getMessage());
            return false;
        }
    }
    @Autowired
    private CategoryRep categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
