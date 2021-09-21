package com.rentauto.dao;

import com.rentauto.domain.Auto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CarParkDao {


       //ищем авто
//    public static void main(String[] args) throws SQLException{

//        CarParkDao a = new CarParkDao();
//        a.findAuto("123456789");
//    }

    private static final String SELECT_CAR = "SELECT * " +
            "FROM auto WHERE UPPER(vin) LIKE UPPER(?)";

    private static final String INSERT_CAR = "INSERT INTO auto( " +
           " vin, model, body, transmission, price, booked) " +
           " VALUES (?, ?, ?, ?, ?, ?);";



    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    //TODO delete temporary method
    //поиск авто
    public List<Auto> findAuto(String pattern) throws SQLException {
        List<Auto> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_CAR)) {

            stmt.setString(1, "%" + pattern + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Auto str = new Auto(
                        rs.getString("vin"),
                        rs.getString("model"),
                        rs.getString("body"),
                        rs.getString("transmission"),
                        rs.getInt("price"),
                        rs.getBoolean("booked")
                        );
                result.add(str);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }


        if(result.size() == 0) {
            System.out.println("Auto not found");
        } else {
            for (int a = 0; a < result.size(); a++) {
                System.out.println(result.get(a) + "\n");
            }
        }



        return result;
    }

    //добавление авто в базу
    private void saveAuto(Connection con, Auto aut) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(INSERT_CAR)) {
            stmt.setString(1, aut.getVin());
            stmt.setString(2, aut.getModel());
            stmt.setString(3, aut.getCarBody());
            stmt.setString(4, aut.getTransmission());
            stmt.setInt(5, aut.getPrice());
            stmt.setBoolean(6, aut.isBooked());

            stmt.executeUpdate();

        } catch (SQLException ex) {
        throw new SQLException(ex);
        }

    }

    //получение авто из базы
    public Auto getAuto(String pattern) throws SQLException {
        Auto result = null;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_CAR)) {

            stmt.setString(1,pattern);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Auto auto = new Auto(
                        rs.getString("vin"),
                        rs.getString("model"),
                        rs.getString("body"),
                        rs.getString("transmission"),
                        rs.getInt("price"),
                        rs.getBoolean("booked")
                );
                result = auto;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return result;
    }




//       //добавляем авто в базу
//    public static void main(String[] args) throws Exception{
//        Auto car = new Auto("12345678907777777", "lada vesta", "sedan", "manual", 888, false);
//        CarParkDao r = new CarParkDao();
//
//
//        r.saveAuto(ConnectionBuilder.getConnection(), car);
//    }

}
