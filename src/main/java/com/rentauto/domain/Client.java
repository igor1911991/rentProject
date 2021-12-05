package com.rentauto.domain;

import java.time.LocalDate;


public class Client {

    String name;
    int phoneNumber;
    LocalDate dateOfBirth;
    String driversLicenseIdentifier;


    public Client(String name, int phoneNumber, LocalDate dateOfBirth, String driversLicenseIdentifier) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.driversLicenseIdentifier = driversLicenseIdentifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDriversLicenseIdentifier() {
        return driversLicenseIdentifier;
    }

    public void setDriversLicenseIdentifier(String driversLicenseIdentifier) {
        this.driversLicenseIdentifier = driversLicenseIdentifier;
    }

    @Override
    public String toString() {
        return "Auto {" +
                "name='" + name + '\'' + "," + '\n' +
                "      phoneNumber='" + phoneNumber + '\'' + "," + '\n' +
                "      dateOfBirth='" + dateOfBirth + '\'' + "," + '\n' +
                "      driversLicenseIdentifier='" + driversLicenseIdentifier + '\'' + "," + '\n' +
                "} " + super.toString();
    }
}
