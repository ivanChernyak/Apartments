package com.chernyak.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.chernyak.model.Apartment;

/**
 * This class is responsible for the logic of working with the user input data.
 */
public abstract class InputApartmentController {

    @FXML
    private TextField textFieldNumber;

    @FXML
    private TextField textFieldSquare;

    @FXML
    private TextField textFieldRoomNumber;

    @FXML
    private TextField textFieldStreet;

    @FXML
    private TextField textFieldFloor;

    @FXML
    private TextField textFieldBuildType;

    @FXML
    private TextField textFieldLifeTime;

    public TextField getTextFieldSquare() {
        return textFieldSquare;
    }

    public TextField getTextFieldRoomNumber() {
        return textFieldRoomNumber;
    }

    public TextField getTextFieldStreet() {
        return textFieldStreet;
    }

    public TextField getTextFieldFloor() {
        return textFieldFloor;
    }

    public TextField getTextFieldBuildType() {
        return textFieldBuildType;
    }

    public TextField getTextFieldLifeTime() {
        return textFieldLifeTime;
    }

    public TextField getTextFieldNumber() {
        return textFieldNumber;
    }

    /**
     * Handles an event clicking a Cancel button.
     * Closes the current window.
     *
     * @param actionEvent some type of action.
     */
    @FXML
    public void actionClose(ActionEvent actionEvent) {
        this.closeWindow(actionEvent);
    }

    /**
     * Handles an event clicking a Ok button.
     *
     * @param actionEvent some type of action.
     */
    @FXML
    public abstract void actionOk(ActionEvent actionEvent);

    /**
     * Shows notification window.
     *
     * @param message message that should be shown.
     */
    public void alert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Попедедження");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    /**
     * Creates a Apartment that contains user input data.
     *
     * @return a Apartment that contains user input data.
     */
    protected Apartment getInputApartment() {
        int number;
        double square;
        int floor;
        int roomNumber;
        String street;
        String buildType;
        int lifeTime;

        number = Integer.parseInt(textFieldNumber.getText());
        square = Double.parseDouble(textFieldSquare.getText());
        floor = Integer.parseInt(textFieldFloor.getText());
        roomNumber = Integer.parseInt(textFieldRoomNumber.getText());
        street = textFieldStreet.getText();
        buildType = textFieldBuildType.getText();
        lifeTime = Integer.parseInt(textFieldLifeTime.getText());

        return new Apartment(number, square, floor, roomNumber, street, buildType, lifeTime);
    }

    /**
     * Checks the validity of the entered data.
     *
     * @return true if data is valid, otherwise return false.
     */
    protected boolean validate() {
        if (textFieldNumber.getText().matches("\\d{1,2}")) {
            if (textFieldFloor.getText().matches("^[-+]?[0-9]*\\.?[0-9]+$")) {
                if (textFieldBuildType.getText().length() <= 30 && textFieldBuildType.getText().length() != 0) {
                    if (textFieldRoomNumber.getText().matches("\\d{1,2}")) {
                        if (textFieldStreet.getText().length() <= 30 && textFieldStreet.getText().length() != 0) {
                            if (textFieldFloor.getText().matches("\\d{1,2}")) {
                                if (textFieldLifeTime.getText().matches("\\d{1,2}")) {
                                    return true;
                                } else {
                                    alert("Термін квартири - число від 1 до 99!");
                                    return false;
                                }
                            } else {
                                alert("Поверх квартири - число від 1 до 99!");
                                return false;
                            }
                        } else {
                            alert("Введіть коректну назву вулиці!!");
                            return false;
                        }
                    } else {
                        alert("Кількість кімнат квартири - число від 1 до 99!");
                        return false;
                    }
                } else {
                    alert("Введіть коректний тип будівлі!");
                    return false;
                }
            } else {
                alert("Площа квартири - число від 1.0 до 99.0!");
                return false;
            }
        } else {
            alert("Номер квартири - число від 1 до 99!");
            return false;
        }
    }

    /**
     * Closes the current window.
     *
     * @param actionEvent some type of action.
     */
    protected void closeWindow(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
