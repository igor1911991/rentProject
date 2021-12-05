package com.rentauto.dao;

import com.rentauto.domain.Auto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CarParkDao {


    private static final String SELECT_CAR = "SELECT * " +
            "FROM auto WHERE UPPER(vin) LIKE UPPER(?)";

    private static final String INSERT_CAR = "INSERT INTO auto( " +
           " vin, model, body, transmission, price, booked) " +
           " VALUES (?, ?, ?, ?, ?, ?);";


    private ConnectionBuilder connectionBuilder;

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }

    //TODO delete temporary method
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

    private void saveAuto(Auto auto) throws SQLException {

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(INSERT_CAR)) {
            stmt.setString(1, auto.getVin());
            stmt.setString(2, auto.getModel());
            stmt.setString(3, auto.getCarBody());
            stmt.setString(4, auto.getTransmission());
            stmt.setInt(5, auto.getPrice());
            stmt.setBoolean(6, auto.isBooked());

            stmt.executeUpdate();

        } catch (SQLException ex) {
        throw new SQLException(ex);
        }

    }

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

}
