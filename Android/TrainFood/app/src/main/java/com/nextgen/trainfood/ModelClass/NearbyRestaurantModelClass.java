package com.nextgen.trainfood.ModelClass;

public class NearbyRestaurantModelClass {

    String id;
    String name;
    String place;
    String image;
    String rating;

    public NearbyRestaurantModelClass(String id,String name,String place,String image,String rating) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.image = image;
        this.rating = rating;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getPlace(){
        return place;
    }

    public String getImage(){
        return image;
    }

    public String getRating(){
        return rating;
    }
}
