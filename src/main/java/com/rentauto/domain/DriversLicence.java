package com.rentauto.domain;

import java.time.LocalDate;
import java.util.Date;

public class DriversLicence {

    private String driversLicenseIdentifier;
    private String category;
    private String driverGivenName;
    private String driverSurname;
    private LocalDate dateOfBirth;
    private Boolean expires;                        //истечение срока действия удостоверения



    public DriversLicence(String driversLicenseIdentifier, String category, String driverGivenName, String driverSurname, LocalDate dateOfBirth, Boolean expires) {
        this.driversLicenseIdentifier = driversLicenseIdentifier;
        this.category = category;
        this.driverGivenName = driverGivenName;
        this.driverSurname = driverSurname;
        this.dateOfBirth = dateOfBirth;
        this.expires = expires;
    }


    public String getDriversLicenseIdentifier() {
        return driversLicenseIdentifier;
    }

    public void setDriversLicenseIdentifier(String driversLicenseIdentifier) {
        this.driversLicenseIdentifier = driversLicenseIdentifier;
    }

    public String getDriverGivenName() {
        return driverGivenName;
    }

    public void setDriverGivenName(String driverGivenName) {
        this.driverGivenName = driverGivenName;
    }

    public String getDriverSurname() {
        return driverSurname;
    }

    public void setDriverSurname(String driverSurname) {
        this.driverSurname = driverSurname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getExpires() {
        return expires;
    }

    public void setExpires(Boolean expires) {
        this.expires = expires;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    //TODO change this method
    @Override
    public String toString() {
        return "DriversLicence{ " +
                "driversLicenseIdentifier='" + driversLicenseIdentifier + '\'' +
                ", driverGivenName='" + driverGivenName + '\'' +
                ", driverSurname='" + driverSurname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", expires=" + expires +
                ", category='" + category + '\'' +
                '}';
    }
}

