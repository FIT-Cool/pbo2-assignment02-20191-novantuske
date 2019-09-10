package com.kevin.entity;

/**
 * @author Kevin Novantus (1772011)
 *
 */
public class Category {
    private String catName;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Override
    public String toString() {
        return catName;
    }
}
