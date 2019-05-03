package com.chernyak.components;

/**
 * This enum is responsible for the paths to the views FXML-files.
 */
public enum FXMLPath {

    CREATE("/views/create.fxml"),
    SEARCH("/views/search.fxml"),
    UPDATE("/views/update.fxml"),
    MAIN("/views/mainPage.fxml");

    private String path;

    FXMLPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
