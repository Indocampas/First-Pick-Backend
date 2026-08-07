package com.hragro.backend.model;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "franchise_content")
public class FranchiseContent extends BaseEntity {
    private String category;
    private String intro;
    private List<String> listItems = new ArrayList<>();
    private String trailingText;
    private List<Truck> trucks = new ArrayList<>();

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getIntro() { return intro; }
    public void setIntro(String intro) { this.intro = intro; }
    
    public List<String> getListItems() { return listItems; }
    public void setListItems(List<String> listItems) { this.listItems = listItems; }
    
    public String getTrailingText() { return trailingText; }
    public void setTrailingText(String trailingText) { this.trailingText = trailingText; }
    
    public List<Truck> getTrucks() { return trucks; }
    public void setTrucks(List<Truck> trucks) { this.trucks = trucks; }

    public static class Truck {
        private String id;
        private String name;
        private String image;
        private String description;
        private List<String> features = new ArrayList<>();
        private String price;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }
        
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        
        public List<String> getFeatures() { return features; }
        public void setFeatures(List<String> features) { this.features = features; }
        
        public String getPrice() { return price; }
        public void setPrice(String price) { this.price = price; }
    }
}