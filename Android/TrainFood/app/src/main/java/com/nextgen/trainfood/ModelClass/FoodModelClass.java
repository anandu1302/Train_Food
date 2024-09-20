package com.nextgen.trainfood.ModelClass;

public class FoodModelClass {

    String id;
    String itemName;
    String itemDesc;
    String price;
    String foodType;
    String image;

    public FoodModelClass(String id,String itemName,String itemDesc,String price,String foodType,String image) {
        this.id = id;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.price = price;
        this.foodType = foodType;
        this.image = image;
    }

    public String getId(){
        return id;
    }

    public String getItemName(){
        return itemName;
    }

    public String getItemDesc(){
        return itemDesc;
    }

    public String getPrice(){
        return price;
    }

    public String getFoodType(){
        return foodType;
    }

    public String getImage(){
        return image;
    }

}
