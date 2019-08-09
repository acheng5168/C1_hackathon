package com.summit.summitproject.prebuilt.model;

import java.io.Serializable;

/**
 * Represents a simple credit card transaction -- containing the merchant and the amount.
 */
public class Action implements Serializable {

    private final String stock;
    private final String date;
    private double priceBought;
    private double priceCurrent;

    public Action(String stock, String date, double priceBought, double priceCurrent) {
        this.stock = stock;
        this.date = date;
        this.priceBought = priceBought;
        this.priceCurrent = priceCurrent;
    }

    public String getStock() {
        return this.stock;
    }

    public String getDate() {
        return this.date;
    }

    public double getPriceBought() {
        return this.priceBought;
    }

    public double getPriceCurrent() {
        return this.priceCurrent;
    }
}
