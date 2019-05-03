package com.chernyak.controllers;

import javafx.event.ActionEvent;
import com.chernyak.model.Apartment;
import com.chernyak.model.ApartmentsList;
import org.apache.log4j.Logger;

/**
 * This class is responsible for the logic of working with the apartment creating.
 */
public class CreateController extends InputApartmentController {
    private final static Logger LOG = Logger.getLogger(CreateController.class);

    /**
     * Handles the create apartment event.
     */
    @Override
    public void actionOk(ActionEvent actionEvent) {
        if (validate()) {
            Apartment apartment = this.getInputApartment();
            apartment.setId(Apartment.getApartmentsId());
            ApartmentsList.createApartment(apartment);
            LOG.info("Apartment create " + apartment.toString());
            closeWindow(actionEvent);
        }
    }
}
