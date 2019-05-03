package com.chernyak.model;

import com.chernyak.components.FilePath;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods for writing and reading list of Apartments.
 */
public class ApartmentIO {

    private final static Logger LOG = Logger.getLogger(ApartmentIO.class);

    private final static File dataDirectory = new File(FilePath.DATA_DIR.getPath());

    /**
     * This method writes Apartments from the list to file.
     *
     * @param apartments list for writing Apartments to the file.
     * @param file       the file in which the list is written.
     */
    public static void writeToFile(List<Apartment> apartments, File file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            if (!dataDirectory.exists()) {
                if (dataDirectory.mkdir()) {
                    LOG.info("Data-directory is created: " + dataDirectory.getAbsolutePath());
                } else {
                    LOG.info("Failed to create data-directory: " + dataDirectory.getAbsolutePath());
                }
            }
            fos = new FileOutputStream(file.getPath());
            oos = new ObjectOutputStream(fos);
            oos.writeObject(apartments);
        } catch (FileNotFoundException e) {
            LOG.trace("Failed to open data-file for writing, FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            LOG.trace("Failed to write information about apartments into data-file, IOException: " + e.getMessage());
        } finally {
            if (oos != null && fos != null) {
                try {
                    oos.close();
                    fos.close();
                } catch (IOException e) {
                    LOG.trace("Failed to close output-streams, IOException: " + e.getMessage());
                }
            }
        }
    }

    /**
     * This method reads Apartments from the file to list.
     *
     * @param file the file from which Apartments is read.
     */
    public static ArrayList<Apartment> readFromFile(File file) {
        ArrayList<Apartment> apartments = null;
        FileInputStream fin = null;
        ObjectInputStream ois = null;
        try {
            if (!dataDirectory.exists()) {
                if (dataDirectory.mkdir()) {
                    LOG.info("Data-directory is created: " + dataDirectory.getAbsolutePath());

                } else {
                    LOG.info("Failed to create data-directory: " + dataDirectory.getAbsolutePath());
                }
            }
//            if (!file.exists()) {
//                if (file.createNewFile()) {
//                    LOG.info("Data-file is created: " + file.getAbsolutePath());
//                }
//            }

            fin = new FileInputStream(file.getPath());
            ois = new ObjectInputStream(fin);
//            System.out.println(ois.available());
//            if (ois.available() != 0){
                apartments = (ArrayList<Apartment>) ois.readObject();
//            }

        } catch (FileNotFoundException e) {
            LOG.trace("Failed to open data-file for reading, FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            LOG.trace("Failed to read information about apartments from data-file, IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            LOG.trace("Failed to cast data from file to ArrayList<Apartment>, ClassNotFoundException: " + e.getMessage());
        } finally {
            if (fin != null && ois != null) {
                try {
                    fin.close();
                    ois.close();
                } catch (IOException e) {
                    LOG.trace("Failed to close input-streams, IOException: " + e.getMessage());
                }
            }
        }
        return apartments;
    }
}
