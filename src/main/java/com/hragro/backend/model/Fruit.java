package com.hragro.backend.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fruits")
public class Fruit extends BaseEntity {
    @Indexed
    private String name;
    private String desc;
    private Double price;
    private String unit;
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