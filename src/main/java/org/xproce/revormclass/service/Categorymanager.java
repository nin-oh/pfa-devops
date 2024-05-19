package org.xproce.revormclass.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.xproce.revormclass.dao.entities.Category;
import org.xproce.revormclass.dao.entities.Product;

import java.util.List;

public interface Categorymanager {
    public Category addCategory(Category category);

    public Category updateCategory(Category category);

    public Category getCategory(Integer Id);

    public boolean deleteCategory(Category category);
    public List<Category> getAllCategories();


}
