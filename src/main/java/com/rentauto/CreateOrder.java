package com.rentauto;

import com.rentauto.dao.BookingDao;
import com.rentauto.dao.ClientDao;
import com.rentauto.domain.Auto;
import com.rentauto.domain.BookingOrder;
import com.rentauto.domain.Client;
import com.rentauto.domain.DriversLicense;

import java.sql.SQLException;

public class CreateOrder {

    DriversLicense driversLicense;
    Auto auto;
    int phoneNumber;
    int rentTime;

    public CreateOrder(DriversLicense driversLicense, Auto auto, int phoneNumber, int rentTime) {
        this.driversLicense = driversLicense;
        this.auto = auto;
        this.phoneNumber = phoneNumber;
        this.rentTime = rentTime;
    }

    public Integer createOrder(CreateOrder this) throws SQLException{
        Integer rensult = 0;

        int clientID = saveClient(this.driversLicense, this.phoneNumber);
        if(clientID <= 0) {
            return clientID;
        } else {
            int statusAuto = updateStatusAuto(this.auto);
            if (statusAuto == 1) {
                return statusAuto;
            } else {
                BookingOrder bo = new BookingOrder(clientID, auto.getVin(), rentTime);
                rensult = saveOrder(bo);
            }
        }

        return rensult;
    }

    private Integer saveClient(DriversLicense driversLicense, Integer phoneNumber) throws SQLException{
        Integer result = 0;
        Client client = new Client(driversLicense.getDriverGivenName(), phoneNumber,
                                   driversLicense.getDateOfBirth(),
                                   driversLicense.getDriversLicenseIdentifier());
        ClientDao cd = new ClientDao();
        result = cd.saveClient(client);
        return result;
    }

    private Integer updateStatusAuto(Auto auto) throws SQLException{
        Integer result = 0;
        BookingDao bd = new BookingDao();
        result = bd.updateAutoStatus(auto);

        return result;
    }

    private Integer saveOrder(BookingOrder bookingOrder) throws SQLException {

        BookingDao bd = new BookingDao();
        Integer nunberBookingOrder = bd.createOrder(bookingOrder);

        return nunberBookingOrder;
    }


    public DriversLicense getDriversLicense() {
        return driversLicense;
    }

    public void setDriversLicense(DriversLicense driversLicense) {
        this.driversLicense = driversLicense;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getRentTime() {
        return rentTime;
    }

    public void setRentTime(Integer rentTime) {
        this.rentTime = rentTime;
    }
}

