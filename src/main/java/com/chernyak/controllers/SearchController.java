package com.chernyak.controllers;

import com.chernyak.model.Apartment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.chernyak.model.ApartmentsList;

public class SearchController extends ListApartmentsController {

    @FXML
    private TextField textFieldSquare;

    @FXML
    private TextField textFieldRoomNumber;

    @FXML
    private TextField textFieldFloorMin;

    @FXML
    private TextField textFieldFloorMax;

    @FXML
    private CheckBox checkBoxSquare;

    @FXML
    private CheckBox checkBoxRoomNumber;

    @FXML
    private CheckBox checkBoxFloor;

    /**
     * Handles the checkBoxRoomNumber click-event.
     */
    @FXML
    void actionSelectRoomNumber() {
        if (checkBoxRoomNumber.isSelected()) {
            checkBoxSquare.setSelected(false);
            checkBoxSquare.setIndeterminate(true);
            textFieldSquare.clear();

            checkBoxFloor.setSelected(false);
            checkBoxFloor.setIndeterminate(true);
            textFieldFloorMin.clear();
            textFieldFloorMax.clear();
        }
    }

    /**
     * Handles the checkBoxFloor click-event.
     */
    @FXML
    void actionSelectFloor() {
        if (checkBoxFloor.isSelected()) {
            checkBoxSquare.setSelected(false);
            checkBoxSquare.setIndeterminate(true);
            textFieldSquare.clear();
            checkBoxRoomNumber.setIndeterminate(true);
            checkBoxRoomNumber.setSelected(false);
            textFieldRoomNumber.clear();
        }
    }

    /**
     * Handles the checkBoxSquare click-event.
     */
    @FXML
    void actionSelectSquare() {
        if ((checkBoxSquare.isSelected())) {
            checkBoxRoomNumber.setIndeterminate(true);
            checkBoxRoomNumber.setSelected(false);
            textFieldRoomNumber.clear();
            checkBoxFloor.setSelected(false);
            checkBoxFloor.setIndeterminate(true);
            textFieldFloorMin.clear();
            textFieldFloorMax.clear();
        }
    }

    /**
     * Handles the apartments search event.
     */
    @FXML
    void actionSearch(ActionEvent event) {
        this.tableViewApartmentsList.setItems(null);
        if (checkBoxRoomNumber.isSelected()) {
            if (validateInt(textFieldRoomNumber, "Кількість кімнат квартири - число від 1 до 99!")) {
                int roomsNumber = Integer.parseInt(textFieldRoomNumber.getText());
                ObservableList<Apartment> apartments = ApartmentsList.getApartmentsWithRoomsNumber(roomsNumber);
                if ( apartments!=null){
                    this.tableViewApartmentsList.setItems(apartments);
                } else {
                    alert("Таких квартир немає.");
                }
            }
        }

        if (checkBoxSquare.isSelected()) {
            if (validateDouble(textFieldSquare, "Площа квартири - двохзначне число!")) {
                double square = Double.parseDouble(textFieldSquare.getText());
                ObservableList<Apartment> apartments = ApartmentsList.getApartmentsWithSquare(square);
                if ( apartments!=null){
                    this.tableViewApartmentsList.setItems(apartments);
                } else {
                    alert("Таких квартир немає.");
                }
            }
        }

        if (checkBoxFloor.isSelected()) {
            if (validateInt(textFieldFloorMin, "Поверх квартири - число від 1 до 99!")
                    && validateInt(textFieldFloorMax, "Поверх квартири - двохзначне число!")) {
                int floorMin = Integer.parseInt(textFieldFloorMin.getText());
                int floorMax = Integer.parseInt(textFieldFloorMax.getText());
                ObservableList<Apartment> apartments = ApartmentsList.getApartmentsWithFloorRange(floorMin, floorMax);
                if ( apartments!=null){
                    this.tableViewApartmentsList.setItems(apartments);
                } else {
                    alert("Таких квартир немає.");
                }
            }
        }
    }

    @FXML
    void initialize() {
        setColumnValueTypes();
    }

    private boolean validateDouble(TextField textField, String warningMessage) {
        if (textField.getText().matches("^[-+]?[0-9]*\\.?[0-9]+$")) {
            return true;
        } else {
            alert(warningMessage);
            return false;
        }
    }

    private boolean validateInt(TextField textField, String warningMessage) {
        if (textField.getText().matches("\\d{1,2}")) {
            return true;
        } else {
            alert(warningMessage);
            return false;
        }
    }

    /**
     * Shows notification window.
     *
     * @param message message that should be shown.
     */
    private void alert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Попедедження");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}

