package com.example;

public class Product {
    private String name;
    private String category;
    private int price;
    private String imageFileName; // Ubah imageUrl menjadi imageFileName
    private String description;
    private String keywords;

    public Product(String name, String category, int price, String imageFileName, String description, String keywords) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageFileName = imageFileName;
        this.description = description;
        this.keywords = keywords;
    }


    // Getter dan setter untuk masing-masing properti
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
