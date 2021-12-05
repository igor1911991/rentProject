package com.rentauto.web;


import com.rentauto.RentValidator;
import com.rentauto.dao.*;
import com.rentauto.domain.DriversLicense;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "CreateOrderServlet", urlPatterns = {"/createOrder"})
public class CreateOrderServlet extends HttpServlet {


    private RentValidator order;
    private DriversLicenseDao dld;
//    private ClientDao cd;
//    private CarParkDao cpd;
//    private BookingDao bd;

    @Override
    public void init() {
//        order = new RentValidator();
        dld = new DriversLicenseDao();
        dld.setConnectionBuilder(new PoolConnectionBuilder());

//        cd = new ClientDao();
//        cd.setConnectionBuilder(new PoolConnectionBuilder());
//        cpd = new CarParkDao();
//        cpd.setConnectionBuilder(new PoolConnectionBuilder());
//        bd = new BookingDao();
//        bd.setConnectionBuilder(new PoolConnectionBuilder());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String dl = req.getParameter("drivelLicenceID");
//        order.setDrivelLicenceID(req.getParameter("drivelLicenceID"));
//        order.setPhoneNumber(Integer.valueOf(req.getParameter("phoneNumber")));
//        order.setVin(req.getParameter("vin"));
//        order.setRentalTime(Integer.valueOf(req.getParameter("rentalTime")));


        try {
            DriversLicense drL = dld.getDriversLicense(dl);
            resp.getWriter().write("555");
//            int i = order.checkAll(order.getDrivelLicenceID(), order.getVin());
//            if (i == 101) {
//                resp.getWriter().write("This driver's license is not registered");
//            } else if (i == 102){
//                resp.getWriter().write("This car is reserved");
//            } else {
//                resp.getWriter().write("Number of order: " + i);
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
