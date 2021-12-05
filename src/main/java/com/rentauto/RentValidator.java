package com.rentauto;

import com.rentauto.dao.CarParkDao;
import com.rentauto.dao.DriversLicenseDao;
import com.rentauto.dao.PoolConnectionBuilder;
import com.rentauto.domain.Auto;
import com.rentauto.domain.Client;
import com.rentauto.domain.DriversLicense;

import java.sql.SQLException;


public class RentValidator {

    String drivelLicenceID;
    int phoneNumber;
    String vin;
    int rentalTime;


    public RentValidator() {
    }

    public RentValidator(String drivelLicenceID, int phoneNumber, String vin, int rentalTime) {
        this.drivelLicenceID = drivelLicenceID;
        this.phoneNumber = phoneNumber;
        this.vin = vin;
        this.rentalTime = rentalTime;
    }

    //проверка введенных данных
    public Integer checkAll(String driverLicenceID, String vin) throws SQLException{
        Integer result = 0;
        DriversLicense driversLicence = checkDriverLicence(driverLicenceID); //было - this.checkDriverLicence(driverLicenceID);
        Boolean statusAuto = this.checkStatusAuto(vin);

        if(driversLicence == null){
            return 101;
        } else if(statusAuto){
            return 102;
        } else {
            CarParkDao cpd = new CarParkDao();
            Auto auto = cpd.getAuto(vin);
            result = toBook(driversLicence, auto, this.phoneNumber, this.rentalTime);
        }
        return result;
    }

    //проветка водительского удостоверения
    private DriversLicense checkDriverLicence(String drivelLicenceID) throws SQLException{
        DriversLicense result = null;

        DriversLicenseDao dld = new DriversLicenseDao();
        result = dld.getDriversLicense(drivelLicenceID);
        return result;
    }

    //проверка статуса авто
    private Boolean checkStatusAuto(String autoVin) throws SQLException{
        Boolean result = false;

        CarParkDao cpd = new CarParkDao();
        Auto auto = cpd.getAuto(autoVin);
        result = auto.isBooked();

        return result;
    }


    //создание заявки
    private Integer toBook(DriversLicense driversLicense, Auto auto, Integer phoneNumber, Integer rentalTime) throws SQLException{
        Integer orderNumber = 0;



        CreateOrder co = new CreateOrder(driversLicense, auto, phoneNumber, rentalTime);

        //TODO образец
        orderNumber = co.createOrder();


        return orderNumber;
    }

    public String getDrivelLicenceID() {
        return drivelLicenceID;
    }

    public void setDrivelLicenceID(String drivelLicenceID) {
        this.drivelLicenceID = drivelLicenceID;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(int rentalTime) {
        this.rentalTime = rentalTime;
    }

//    public static void main(String[] args) throws SQLException {
//
//        RentValidator rn = new RentValidator("10203040501234561", 9960653, "12345678901234567", 2);
//
//        int num = rn.checkAll(rn.drivelLicenceID, rn.vin);
//        System.out.println(num);
//    }

}
