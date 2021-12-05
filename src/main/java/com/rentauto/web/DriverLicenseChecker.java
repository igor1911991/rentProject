package com.rentauto.web;



import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import com.rentauto.domain.DriversLicense;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class DriverLicenseChecker {

    DriversLicense dl;

    public DriversLicense checkDriverLicense(String driversLicenseID) {


        try {

            Client client = ClientBuilder.newClient();
            DriversLicense driversLicense = client.target(
                    "http://localhost:8080/federaldriverregister-1.0/rest/check")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(driversLicenseID, MediaType.APPLICATION_JSON))
                    .readEntity(DriversLicense.class);


            dl = driversLicense;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return dl;
    }

}
