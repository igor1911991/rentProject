package com.rentauto.domain;

public class Auto {

    private String model;
    private String carBody;
    private String transmission;
    private String vin;
    private int price;
    private boolean booked;


    public Auto(String vin, String model, String carBody, String transmission, int price, boolean booked) {
        this.model = model;
        this.carBody = carBody;
        this.transmission = transmission;
        this.vin = vin;
        this.price = price;
        this.booked = booked;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarBody() {
        return carBody;
    }

    public void setCarBody(String carBody) {
        this.carBody = carBody;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public String toString() {
        return  "Auto {" +
                "model='" + model + '\'' + "," + '\n' +
                "       carBody='" + carBody + '\'' + "," + '\n' +
                "       transmission='" + transmission + '\'' + "," + '\n' +
                "       vin='" + vin + '\'' + "," + '\n' +
                "       price='" + price + '\'' + "," + '\n' +
                "       booked='" + booked + '\'' + "," + '\n' +
                "} " + super.toString();
    }



}
