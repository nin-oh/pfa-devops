package org.xproce.revormclass.web;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xproce.revormclass.dao.entities.Category;
import org.xproce.revormclass.service.CategoryService;
import org.xproce.revormclass.user.entities.UserModel;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categorylist"; // Name of the Thymeleaf template
    }



    @GetMapping("/deletecategory/{id}")
    public String deleteCategory(@PathVariable("id") Integer id, @ModelAttribute Category category) {

        categoryService.deleteCategory(category);
        return "redirect:/categories";
    }

    // Other methods for update and delete
}