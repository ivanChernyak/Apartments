package com.chernyak.components;

import java.io.File;

/**
 * This enum is responsible for the paths to the data files.
 */
public enum FilePath {
    DATA_DIR(new File("ApartmentsData").getAbsolutePath()),
    DATA_FILE(new File("ApartmentsData/data.txt").getAbsolutePath());

    private String path;

    FilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
