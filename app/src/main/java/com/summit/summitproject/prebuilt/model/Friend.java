package com.summit.summitproject.prebuilt.model;

import java.io.Serializable;

/**
 * Represents a simple credit card transaction -- containing the merchant and the amount.
 */
public class Friend implements Serializable {

    private final String name;
    private final String username;
    private double percent;
    private boolean hasGained;

    public Friend(String name, String username, double percent, boolean hasGained) {
        this.name = name;
        this.username = username;
        this.percent = percent;
        this.hasGained = hasGained;
    }

    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public double getPercent() { return this.percent; }

    public void setPercent(double newPercent){
        percent = newPercent;
    }

    public boolean hasGained() { return this.hasGained; }
    public void flipGain(){
        hasGained = !hasGained;
    }


    @Override
    public String toString() {
        return "Name: " + this.name + ", Username: " + this.username;
    }

}
