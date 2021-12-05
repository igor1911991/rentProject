package com.rentauto.dao;

import com.rentauto.domain.DriversLicense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriversLicenseDao {


    private static final String INSERT_DRV_LICENSE = "INSERT INTO drivers_license( " +
            " drivers_license_id, category, driver_given_name, driver_surname, date_of_birth, expires) " +
            " VALUES (?, ?, ?, ?, ?, ?);";

    private static final String SELECT_DRV_LICENSE = "SELECT * " +
            "FROM drivers_license WHERE UPPER(drivers_license_id) LIKE UPPER(?)";

    private static final String UPDATE_DRV_STATUS = "UPDATE drivers_license " +
            "SET expires = ? " +
            "WHERE drivers_license_id = ?;";


    private ConnectionBuilder connectionBuilder;

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }

    //добавление водительских прав
    public void setDriversLicense(DriversLicense driversLicense) throws SQLException {
        try(Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(INSERT_DRV_LICENSE)) {

            stmt.setString(1, driversLicense.getDriversLicenseIdentifier());
            stmt.setString(2, driversLicense.getCategory());
            stmt.setString(3, driversLicense.getDriverGivenName());
            stmt.setString(4, driversLicense.getDriverSurname());
            stmt.setDate(5, java.sql.Date.valueOf(driversLicense.getDateOfBirth()));
            stmt.setBoolean(6, true);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

    }

    public DriversLicense getDriversLicense(String pattern) throws SQLException {
        DriversLicense result = null;


        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_DRV_LICENSE)) {

            stmt.setString(1,pattern);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DriversLicense drvLcnce = new DriversLicense(
                        rs.getString("drivers_license_id"),
                        rs.getString("category"),
                        rs.getString("driver_given_name"),
                        rs.getString("driver_surname"),
                        rs.getDate("date_of_birth").toLocalDate(),
                        rs.getBoolean("expires")
                );
                result = drvLcnce;

            }

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return result;
    }

    public void updateDriversLicenseStatus(DriversLicense driversLicense) throws SQLException {
        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(UPDATE_DRV_STATUS)) {

            boolean newStatus;
            if(driversLicense.getExpires() == true) {
                newStatus = false;
            } else newStatus = true;

            stmt.setBoolean(1, newStatus);
            stmt.setString(2, driversLicense.getDriversLicenseIdentifier());

            stmt.executeUpdate();


        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

    }

}
