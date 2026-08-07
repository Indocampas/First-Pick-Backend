package com.hragro.backend.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gallery_items")
public class GalleryItem extends BaseEntity {
    private String title;
    private String type;
    private String src;
    private String thumbnail;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getSrc() { return src; }
    public void setSrc(String src) { this.src = src; }
    
    public String getThumbnail() { return thumbnail; }
    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }
}