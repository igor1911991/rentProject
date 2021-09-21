package com.rentauto.dao;

import com.rentauto.domain.DriversLicence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriversLicenceDao {

    private static final String INSERT_DRV_LICENCE = "INSERT INTO drivers_licence( " +
            " drivers_licence_id, category, driver_given_name, driver_surname, date_of_birth, expires) " +
            " VALUES (?, ?, ?, ?, ?, ?);";

    private static final String SELECT_DRV_LCNCE = "SELECT * " +
            "FROM drivers_licence WHERE UPPER(drivers_licence_id) LIKE UPPER(?)";

    private static final String UPDATE_DRV_STATUS = "UPDATE drivers_licence " +
            "SET expires = ? " +
            "WHERE drivers_licence_id = ?;";


    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    //добавление водительских прав
    public void setDriversLicence(Connection con, DriversLicence driversLicence) throws SQLException {
        try(PreparedStatement stmt = con.prepareStatement(INSERT_DRV_LICENCE)) {

            stmt.setString(1, driversLicence.getDriversLicenseIdentifier());
            stmt.setString(2, driversLicence.getCategory());
            stmt.setString(3, driversLicence.getDriverGivenName());
            stmt.setString(4, driversLicence.getDriverSurname());
            stmt.setDate(5, java.sql.Date.valueOf(driversLicence.getDateOfBirth()));
            stmt.setBoolean(6, true);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

    }

    //получение водительских прав
    public DriversLicence getDriversLicence(String pattern) throws SQLException {
        DriversLicence result = null;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_DRV_LCNCE)) {

            stmt.setString(1,pattern);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DriversLicence drvLcnce = new DriversLicence(
                        rs.getString("drivers_licence_id"),
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

        //TODO delete this
        //System.out.println(driversLicence);

        return result;
    }

    //изменение статуса водительских прав в зависимости от состояния
    public void updateDriversLicenceStatus(Connection con, DriversLicence driversLicence) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(UPDATE_DRV_STATUS)) {

            boolean newStatus;
            if(driversLicence.getExpires() == true) {
                newStatus = false;
            } else newStatus = true;

            stmt.setBoolean(1, newStatus);
            stmt.setString(2, driversLicence.getDriversLicenseIdentifier());

            stmt.executeUpdate();


        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

    }

}
