package com.chernyak.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.chernyak.model.Apartment;
import com.chernyak.model.ApartmentsList;
import org.apache.log4j.Logger;

/**
 * This class is responsible for the logic of working with the apartment updating.
 */
public class UpdateController extends InputApartmentController {

    private final static Logger LOG = Logger.getLogger(UpdateController.class);

    private static Apartment updateApartment;

    public static void setUpdateApartment(Apartment updateApartment) {
        UpdateController.updateApartment = updateApartment;
    }

    /**
     * Handles the update task event.
     */
    @Override
    public void actionOk(ActionEvent actionEvent) {
        if (validate()) {
            Apartment newApartment = this.getInputApartment();
            newApartment.setId(updateApartment.getId());
            ApartmentsList.updateApartment(updateApartment, newApartment);
            LOG.info("Apartment update " + newApartment.toString());
            closeWindow(actionEvent);
        }
    }

    /**
     * Called to initialize a controller.
     */
    @FXML
    void initialize() {
        setApartmentFields();
    }

    /**
     * Sets the data for the apartment being updated to the view-fields.
     */
    private void setApartmentFields() {
        this.getTextFieldNumber().setText(String.valueOf(updateApartment.getNumber()));
        this.getTextFieldSquare().setText(String.valueOf(updateApartment.getSquare()));
        this.getTextFieldRoomNumber().setText(String.valueOf(updateApartment.getRoomNumber()));
        this.getTextFieldFloor().setText(String.valueOf(updateApartment.getFloor()));
        this.getTextFieldLifeTime().setText(String.valueOf(updateApartment.getLifeTime()));
        this.getTextFieldStreet().setText(updateApartment.getStreet());
        this.getTextFieldBuildType().setText(updateApartment.getBuildType());
    }
}
