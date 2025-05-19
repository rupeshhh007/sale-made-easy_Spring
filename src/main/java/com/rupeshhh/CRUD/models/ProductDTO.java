package com.rupeshhh.CRUD.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {

    @NotEmpty(message = "The name is required.")
    private String name;

    @NotEmpty(message = "The brand is required.")
    private String brand;

    @NotEmpty(message = "The category is required.")
    private String category;

    @Min(value = 0, message = "Price must be positive")
    private double price;

    @Size(min = 10, max = 2000, message = "The description must be between 10 and 2000 characters.")
    private String description;

    private MultipartFile imageFile;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public MultipartFile getImageFile() { return imageFile; }
    public void setImageFile(MultipartFile imageFile) { this.imageFile = imageFile; }
}
