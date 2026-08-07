package com.hragro.backend.model;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "franchise_locations")
public class FranchiseLocation extends BaseEntity {
    private String city;
    private String state;
    private List<Place> places = new ArrayList<>();

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    public List<Place> getPlaces() { return places; }
    public void setPlaces(List<Place> places) { this.places = places; }

    public static class Place {
        private String id;
        private String name;
        private boolean available;
        private Double lat;
        private Double lng;
        private String mapsUrl;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public boolean isAvailable() { return available; }
        public void setAvailable(boolean available) { this.available = available; }
        
        public Double getLat() { return lat; }
        public void setLat(Double lat) { this.lat = lat; }
        
        public Double getLng() { return lng; }
        public void setLng(Double lng) { this.lng = lng; }
        
        public String getMapsUrl() { return mapsUrl; }
        public void setMapsUrl(String mapsUrl) { this.mapsUrl = mapsUrl; }
    }
}