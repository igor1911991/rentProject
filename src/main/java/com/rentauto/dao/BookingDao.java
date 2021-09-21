package com.rentauto.dao;

import com.rentauto.domain.Auto;
import com.rentauto.domain.BookingOrder;
import com.rentauto.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class BookingDao {

    public static void main(String[] args) throws SQLException{

//        CarParkDao cpd = new CarParkDao();
//
//        Auto auto = cpd.getAuto("12345678901111111");
        //TODO убрать вывод на экран
//        System.out.println(auto.toString());
//        BookingDao a = new BookingDao();
//        a.updateAutoStatus(ConnectionBuilder.getConnection(), auto);

    }



    //TODO дописать
    private static final String INSERT_BOOKING = "INSERT INTO booking(" +
            "rental_order_status, rental_order_date, client, auto, rental_time)" +
            "VALUES (?, ?, ?, ?, ?);";

    private static final String UPDATE_AUTO_STATUS = "UPDATE auto " +
            "SET booked = ? " +
            "WHERE vin = ?;";

    private static final String SELECT_BOOKING_ORDER = "SELECT * " +
            "FROM booking WHERE rental_order_id = ?";

    private static final String START = "UPDATE booking " +
            "SET rental_order_status = ? " +
            "WHERE rental_order_id = ?;";

    private static final String COMPLETED = "UPDATE booking " +
            "SET rental_order_status = ? " +
            "WHERE rental_order_id = ?;";

    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    //создание заявления
    //TODO создать энум для получения статуса заявления
    public Integer createOrder(Connection con, BookingOrder order) throws SQLException{
        Integer result = -1;

        try (PreparedStatement stmt = con.prepareStatement(INSERT_BOOKING, new String[] {"rental_order_id"})) {
            stmt.setInt(1, 0);                                      // это здесь туду
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(3, order.getClientID());
            stmt.setString(4, order.getAutoID());
            stmt.setInt(5, order.getRentalTime());

            stmt.executeUpdate();

            ResultSet gkRs = stmt.getGeneratedKeys();
            if (gkRs.next()) {
                result = gkRs.getInt(1);
            }

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return result;
    }

    //изменение статуса авто в зависимости от состояния
    public void updateAutoStatus(Connection con, Auto auto) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(UPDATE_AUTO_STATUS)) {

            boolean newStatus;
            if(auto.isBooked()) {
                newStatus = false;
            } else newStatus = true;

            stmt.setBoolean(1, newStatus);
            stmt.setString(2, auto.getVin());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

    }

    //получение заявления
    public BookingOrder getOrder(Integer pattern) throws SQLException {
        BookingOrder result = null;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_BOOKING_ORDER)) {

            stmt.setInt(1, pattern);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BookingOrder order = new BookingOrder(
                        rs.getInt("rental_order_id"),
                        rs.getInt("rental_order_status"),
                        rs.getTimestamp("rental_order_date").toLocalDateTime(),
                        rs.getInt("client"),
                        rs.getString("auto"),
                        rs.getInt("rental_time")
                );
                result = order;
            }

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return result;
    }


    //начать использование
    public void toStart(Connection con, BookingOrder order) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(START)) {

            int newStatus;
            if(order.getOrderStatus() == 0) {
                newStatus = 1;
            } else System.out.println("завершено");    //TODO удалить вывод на экран
                   newStatus = order.getOrderStatus();

            stmt.setInt(1, newStatus);
            stmt.setInt(2, order.getNumberOrder());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

    }

    //изменение статуса заявления
    public void toComplete(Connection con, BookingOrder order) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(COMPLETED)) {

            int newStatus;
            if(order.getOrderStatus() == 0 || order.getOrderStatus() == 1) {
                newStatus = 2;
            } else newStatus = order.getOrderStatus();

            stmt.setInt(1, newStatus);
            stmt.setInt(2, order.getNumberOrder());

            stmt.executeUpdate();
        }
    }

}
