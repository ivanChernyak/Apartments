package com.chernyak;

import com.chernyak.components.FXMLPath;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import com.chernyak.model.ApartmentsList;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Main extends Application {
    private final static Logger LOG = Logger.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(FXMLPath.MAIN.getPath()));
            primaryStage.setTitle("Список усіх квартир");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException e) {
            LOG.trace("Failed to load Main-view file, IOException: " + e.getMessage());
            alert();
            Platform.exit();
        }

    }

    @Override
    public void stop() {
        ApartmentsList.saveDate();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Shows notification window.
     */
    private void alert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Помилка");
        alert.setHeaderText("Щось пішло не так :(");
        alert.setContentText("Неможливо запустити програму. Спробуйте ще раз, якщо це перший запуск програми. \nАбо зверніться, будь ласка, до постачальника програми.");
        alert.showAndWait();
    }
}
