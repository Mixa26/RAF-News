package raf.news.rafnews.repositories;

import raf.news.rafnews.entities.Category;
import java.util.List;

public interface CategoryRepository {

    Category addCategory(Category category);

    Category updateCategory(Category category);

    List<Category> allCategories();

    Category deleteCategory(int id);
}
