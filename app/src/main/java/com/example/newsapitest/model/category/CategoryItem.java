package com.example.newsapitest.model.category;

public class CategoryItem {
    private String categoryName;
    private int categoryIcon;

    public CategoryItem(String categoryName, int categoryIcon) {
        this.categoryName = categoryName;
        this.categoryIcon = categoryIcon;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getCategoryIcon() {
        return categoryIcon;
    }
}
