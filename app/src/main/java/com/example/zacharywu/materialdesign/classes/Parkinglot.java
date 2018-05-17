package com.example.zacharywu.materialdesign.classes;

public class Parkinglot {

    private String name;
    private int imageID;

    public Parkinglot(String name, int imageID){
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
