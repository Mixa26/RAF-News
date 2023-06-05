package raf.news.rafnews.services;

import raf.news.rafnews.entities.Category;
import raf.news.rafnews.repositories.CategoryRepository;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

    @Inject
    private CategoryRepository categoryRepository;

    public CategoryService() {}

    public Category addCategory(Category category){
        return categoryRepository.addCategory(category);
    }

    public Category updateCategory(Category category){
        return categoryRepository.updateCategory(category);
    }

    public List<Category> allCategories(){
        return categoryRepository.allCategories();
    }

    public Category deleteCategory(int id){
        return categoryRepository.deleteCategory(id);
    }
}
