package com.chernyak.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import com.chernyak.model.Apartment;

/**
 * This class is responsible for the logic of working with the apartments list in the table.
 */
public class ListApartmentsController {

    @FXML
    protected TableView<Apartment> tableViewApartmentsList;

    @FXML
    protected TableColumn<Apartment, Integer> tableColumnNumber;

    @FXML
    protected TableColumn<Apartment, Double> tableColumnSquare;

    @FXML
    protected TableColumn<Apartment, Integer> tableColumnFloor;

    @FXML
    protected TableColumn<Apartment, Integer> tableColumnRoomNumber;

    @FXML
    protected TableColumn<Apartment, String> tableColumnStreet;

    @FXML
    protected TableColumn<Apartment, String> tableColumnBuildType;

    @FXML
    protected TableColumn<Apartment, Integer> tableColumnLifeTime;

    /**
     * Specifies what type of data will be in the table column.
     */
    protected void setColumnValueTypes() {
        tableColumnNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Apartment, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Apartment, Integer> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getNumber());
            }
        });
        tableColumnSquare.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Apartment, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Apartment, Double> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getSquare());
            }
        });
        tableColumnFloor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Apartment, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Apartment, Integer> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getFloor());
            }
        });
        tableColumnRoomNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Apartment, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Apartment, Integer> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getRoomNumber());
            }
        });
        tableColumnStreet.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Apartment, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Apartment, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getStreet());
            }
        });
        tableColumnBuildType.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Apartment, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Apartment, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getBuildType());
            }
        });
        tableColumnLifeTime.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Apartment, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Apartment, Integer> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getLifeTime());
            }
        });
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
