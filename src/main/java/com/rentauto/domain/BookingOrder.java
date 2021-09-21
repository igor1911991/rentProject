package com.rentauto.domain;



import java.time.LocalDateTime;

public class BookingOrder {


    int numberOrder;
    int orderStatus;
    LocalDateTime timeOfCreation;
    int clientID;
    String autoID;
    int rentalTime;

    public BookingOrder(int clientID, String autoID, int rentalTime) {

        this.clientID = clientID;
        this.autoID = autoID;
        this.rentalTime = rentalTime;
    }

    public BookingOrder(int numberOrder, int orderStatus, LocalDateTime timeOfCreation, int clientID, String autoID, int rentalTime) {
        this.numberOrder = numberOrder;
        this.orderStatus = orderStatus;
        this.timeOfCreation = timeOfCreation;
        this.clientID = clientID;
        this.autoID = autoID;
        this.rentalTime = rentalTime;
    }

    public int getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(int numberOrder) {
        this.numberOrder = numberOrder;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(LocalDateTime timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getAutoID() {
        return autoID;
    }

    public void setAutoID(String autoID) {
        this.autoID = autoID;
    }

    public int getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(int rentalTime) {
        this.rentalTime = rentalTime;
    }


    @Override
    public String toString() {
        return  "BookingOrder {" +
                "numberOrder='" + numberOrder + '\'' + "," + '\n' +
                "       orderStatus='" + orderStatus + '\'' + "," + '\n' +
                "       timeOfCreation='" + timeOfCreation + '\'' + "," + '\n' +
                "       clientID='" + clientID + '\'' + "," + '\n' +
                "       rentalTime='" + rentalTime + '\'' + "," + '\n' +
                "} " + super.toString();
    }
}
