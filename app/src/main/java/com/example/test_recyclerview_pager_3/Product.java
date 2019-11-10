package com.example.test_recyclerview_pager_3;



public class Product {
    private String title;
    private int ResourceID;

    public Product(int id, String title)
    {
        this.ResourceID = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getResourceID() {
        return ResourceID;
    }
}
