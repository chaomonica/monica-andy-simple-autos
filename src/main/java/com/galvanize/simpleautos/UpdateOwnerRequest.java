package com.galvanize.simpleautos;

public class UpdateOwnerRequest {
    
    private String color;
    private String owner;

    public UpdateOwnerRequest(String color, String owner) {
        this.color = color;
        this.owner = owner;
    }

    public UpdateOwnerRequest() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
