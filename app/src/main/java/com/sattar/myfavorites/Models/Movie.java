package com.sattar.myfavorites.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sattar on 4-1-2019
 */
public class Movie extends RealmObject {

    public interface Fields {
        public String KEY_RATE = "rate";
        public String KEY_ID = "id";
    }

    @PrimaryKey
   private String id;
   private String name;
   private String description;
   private double rate;
   private Float currentUserRate;
   private String imagePath;
   private Integer imageId;
   private String year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRate() {
        return  rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Float getCurrentUserRate() {
        return currentUserRate;
    }

    public void setCurrentUserRate(Float currentUserRate) {
        this.currentUserRate = currentUserRate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
