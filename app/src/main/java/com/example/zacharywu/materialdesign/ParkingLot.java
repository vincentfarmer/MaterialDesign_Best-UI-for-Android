package com.example.zacharywu.materialdesign;

public class ParkingLot {

    private String name;
    private int imageID;

    public ParkingLot(String name, int imageID){
        this.name = name;
        this.imageID = imageID;
    }

    public int getImageID() {
        return imageID;
    }

    public String getName() {
        return name;
    }
}
