package com.chernyak.controllers;

import java.io.IOException;
import java.util.Optional;

import com.chernyak.components.FXMLPath;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.chernyak.model.Apartment;
import com.chernyak.model.ApartmentsList;
import org.apache.log4j.Logger;

/**
 * This class is responsible for the logic of working with the main application window.
 */
public class MainController extends ListApartmentsController {

    private final static Logger LOG = Logger.getLogger(MainController.class);

    /**
     * Handles the apartment create event.
     */
    @FXML
    void actionAdd() {
        showView("Додати квартиру", FXMLPath.CREATE);
    }

    /**
     * Handles the task apartment event.
     */
    @FXML
    void actionEdit() {
        Apartment selectedApartment = tableViewApartmentsList.getSelectionModel().getSelectedItem();
        if (selectedApartment != null) {
            UpdateController.setUpdateApartment(selectedApartment);
            showView("Редагувати квартиру", FXMLPath.UPDATE);
        } else {
            alert("Попедедження",
                    "Ви не вибрали квартиру!",
                    "Оберіть, будь ласка, квартиру для редагування!",
                    Alert.AlertType.WARNING);
        }
    }

    /**
     * Handles the apartment delete event.
     */
    @FXML
    void actionRemove(ActionEvent actionEvent) {
        Apartment selectedApartment = tableViewApartmentsList.getSelectionModel().getSelectedItem();
        if (selectedApartment != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Видалення квартири");
            alert.setHeaderText("Видалити квартиру " + selectedApartment.getNumber() + " ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ApartmentsList.deleteApartment(selectedApartment);
                LOG.info("Apartment remove " + selectedApartment.toString());
            }
        } else {
            alert("Попедедження",
                    "Ви не вибрали квартиру!",
                    "Оберіть, будь ласка, квартиру для видалення!",
                    Alert.AlertType.WARNING);
        }
    }

    /**
     * Handles the apartments search event.
     */
    @FXML
    void actionSearch(ActionEvent event) {
        showView("Шукати квартири", FXMLPath.SEARCH);
    }

    /**
     * Called to initialize a controller.
     * Load apartments from file.
     */
    @FXML
    void initialize() {
        ApartmentsList.loadData();
        setColumnValueTypes();
        tableViewApartmentsList.setItems(ApartmentsList.getApartmentsList());

    }

    /**
     * Shows notification window.
     *
     * @param message message that should be shown.
     */
    public void alert(String title, String headerText, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Shows a window that is responsible for one of the application features.
     *
     * @param sceneTitle window title.
     * @param resource   view-file path.
     */
    private void showView(String sceneTitle, FXMLPath resource) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(resource.getPath()));
        try {
            loader.load();
        } catch (IOException e) {
            LOG.trace("Failed to load view-file, IOException: " + e.getMessage());
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle(sceneTitle);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
