package com.factory.controller;

import com.factory.entity.Category;
import com.factory.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @RequestMapping("/categories")
    public String findAllCategories(Model model, HttpServletRequest request){
        final List<Category> categories = categoryService.findAllCategories();

        model.addAttribute("categories", categories);
        if(request.isUserInRole("USER")){
            return "redirect:/list-categories-user";
        }
        return "list-categories";
    }

    @RequestMapping("/category/{id}")
    public String findCategoryById(@PathVariable("id") Long id, Model model) {
        final Category category = categoryService.findCategoryById(id);

        model.addAttribute("category", category);
        return "list-category";
    }

    @GetMapping("/addCategory")
    public String showCreateForm(Category category) {
        return "add-category";
    }

    @RequestMapping("/add-category")
    public String createCategory(Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-category";
        }

        categoryService.createCategory(category);
        model.addAttribute("category", categoryService.findAllCategories());
        return "redirect:/categories";
    }

    @GetMapping("/updateCategory/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        final Category category = categoryService.findCategoryById(id);

        model.addAttribute("category", category);
        return "update-category";
    }

    @RequestMapping("/update-category/{id}")
    public String updateCategory(@PathVariable("id") Long id, Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            category.setId(id);
            return "update-category";
        }

        categoryService.updateCategory(category);
        model.addAttribute("category", categoryService.findAllCategories());
        return "redirect:/categories";
    }

    @RequestMapping("/remove-category/{id}")
    public String deleteCategory(@PathVariable("id") Long id, Model model) {
        categoryService.deleteCategory(id);

        model.addAttribute("category", categoryService.findAllCategories());
        return "redirect:/categories";
    }
}
