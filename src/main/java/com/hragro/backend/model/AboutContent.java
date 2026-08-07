package com.hragro.backend.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "about_content")
public class AboutContent extends BaseEntity {
    private String vision;
    private String mission;

    public String getVision() { return vision; }
    public void setVision(String vision) { this.vision = vision; }
    
    public String getMission() { return mission; }
    public void setMission(String mission) { this.mission = mission; }
}