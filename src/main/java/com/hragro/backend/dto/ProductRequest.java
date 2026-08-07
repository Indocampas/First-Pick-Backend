package com.hragro.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequest {
    @NotBlank(message = "Product name is required")
    private String name;
    
    @NotBlank(message = "Description is required")
    private String desc;
    
    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Double price;
    
    @NotBlank(message = "Unit is required")
    private String unit;
    
    @NotBlank(message = "Image URL is required")
    private String image;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}