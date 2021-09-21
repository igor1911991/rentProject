package com.rentauto;

import com.rentauto.dao.*;
import com.rentauto.domain.Auto;
import com.rentauto.domain.BookingOrder;
import com.rentauto.domain.Client;
import com.rentauto.domain.DriversLicence;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RentValidator {

    public static void main(String[] args) throws SQLException {

//        DriversLicenceDao driverLicence;
//        DriversLicence dl;
//        driverLicence = new DriversLicenceDao();
//        dl = driverLicence.getDriversLicence("12345678901234567");
//        System.out.println(dl);


//        LocalDate date = LocalDate.of(1988, 11, 18);
//        Client client = new Client("Vacheslav", 9005050, date, "12345678901234567");
//        ClientDao cd = new ClientDao();
//        Integer i = cd.saveClient(ConnectionBuilder.getConnection(), client);
//        System.out.println(i);

        CarParkDao autoDao = new CarParkDao();
        Auto auto;
        auto = autoDao.getAuto("12345678907777777");

        BookingOrder bookOrder = new BookingOrder(1, auto.getVin(), 4);
        BookingDao bd = new BookingDao();
        Integer i = bd.createOrder(ConnectionBuilder.getConnection(), bookOrder);
        System.out.println(i);
//        int a;
//        int b = 10;
//        for(a = 1; a < b; a++) {
//            bd.createOrder(ConnectionBuilder.getConnection(), bookOrder);
//        }


//        DriversLicence dl = new DriversLicence(
//                "12345678901231231",
//                "b",
//                "Maxim",
//                "Kolesnikov",
//                LocalDate.of(1974, 01, 22),
//                true
//        );

//        DriversLicenceDao dl = new DriversLicenceDao();
//        DriversLicence dl2 = dl.getDriversLicence("12345678901231231");
//
//        DriversLicenceDao dld = new DriversLicenceDao();
//        dld.setDriversLicence(ConnectionBuilder.getConnection(), dl);
//        dld.updateDriversLicenceStatus(ConnectionBuilder.getConnection(), dl2);


//        ClientDao cd = new ClientDao();
//        Client client = cd.getClient(1);
//        System.out.println(client);




//        BookingDao bd = new BookingDao();
//        BookingOrder ord = bd.getOrder(15);
//        System.out.println(ord);
//        bd.toStart(ConnectionBuilder.getConnection(), ord);
//        System.out.println(bd.getOrder(15));
//        bd.toComplete(ConnectionBuilder.getConnection(), ord);
//        System.out.println(bd.getOrder(15));

    }

}
