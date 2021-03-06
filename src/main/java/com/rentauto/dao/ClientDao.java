package com.rentauto.dao;

import com.rentauto.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDao {

    //TODO delete this 26/11/21 19:37
    public ClientDao() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String INSERT_CLIENT = "INSERT INTO client( " +
            " given_name, phone_number, date_of_birth, drivers_license) " +
            " VALUES (?, ?, ?, ?);";

    private static final String SELECT_CLIENT = "SELECT * " +
            "FROM client WHERE client_id = ?";


    private ConnectionBuilder connectionBuilder;

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }

    public Integer saveClient(Client client) throws SQLException {
        int result = -1;

        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(INSERT_CLIENT, new String[] {"client_id"} )) {
            stmt.setString(1, client.getName());
            stmt.setInt(2, client.getPhoneNumber());
            stmt.setDate(3, java.sql.Date.valueOf(client.getDateOfBirth()));
            stmt.setString(4, client.getDriversLicenseIdentifier());

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

    public Client getClient(Integer pattern) throws SQLException {
        Client result = null;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_CLIENT)) {

            stmt.setInt(1, pattern);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Client client1 = new Client(
                        rs.getString("given_name"),
                        rs.getInt("phone_number"),
                        rs.getDate("date_of_birth").toLocalDate(),
                        rs.getString("drivers_license")
                );
                result = client1;

            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return result;
    }

}
