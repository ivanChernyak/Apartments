package com.chernyak.model;

import com.chernyak.components.FilePath;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;

public class ApartmentsList {

    private static File file = new File(FilePath.DATA_FILE.getPath());
    /**
     * Responsible for storing the Apartment list while the program is running.
     */
    private static ObservableList<Apartment> apartmentsList = FXCollections.observableList(new ArrayList<>());

    public static ObservableList<Apartment> getApartmentsList() {
        return apartmentsList;
    }

    /**
     * Loads a task list from a file.
     */
    public static void loadData() {
        ArrayList<Apartment> apartmentsArrayList = ApartmentIO.readFromFile(file);
        apartmentsList.addAll(apartmentsArrayList);
    }

    /**
     * Saves the task list to a file
     */
    public static void saveDate() {
        ArrayList<Apartment> apartmentsArrayList = new ArrayList<>();
        apartmentsArrayList.addAll(apartmentsList);
        ApartmentIO.writeToFile(apartmentsArrayList, file);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param apartment element to be appended to this list.
     */
    public static void createApartment(Apartment apartment) {
        apartmentsList.add(apartment);
    }

    /**
     * Removes the first occurrence of the specified element from list,
     * if it is present.
     *
     * @param apartment element to be removed from this list, if present.
     * @return true if this list contained the specified element.
     */
    public static boolean deleteApartment(Apartment apartment) {
        return apartmentsList.remove(apartment);
    }

    /**
     * Changes the specified element to a new one.
     *
     * @param oldApartment element to be removed from this list.
     * @param newApartment element to be appended to this list.
     */
    public static void updateApartment(Apartment oldApartment, Apartment newApartment) {
        if (deleteApartment(oldApartment)) {
            createApartment(newApartment);
        }
    }

    /**
     * Returns apartments-list with the specified number of rooms.
     * If list don't contains such elements then return "empty" apartment.
     */
    public static ObservableList<Apartment> getApartmentsWithRoomsNumber(int roomsNumber) {
        ObservableList<Apartment> apartments = FXCollections.observableList(new ArrayList<>());
        if (!apartmentsList.isEmpty()) {
            for (Apartment apartment : apartmentsList) {
                if (apartment.getRoomNumber() == roomsNumber) {
                    apartments.add(apartment);
                }
            }
        }

        if (apartments.isEmpty()) {
            apartments = null;
        }
        return apartments;
    }

    /**
     * Returns apartments-list with the floor in the specified range.
     * If list don't contains such elements then return "empty" apartment.
     */
    public static ObservableList<Apartment> getApartmentsWithFloorRange(int minFloor, int maxFloor) {
        ObservableList<Apartment> apartments = FXCollections.observableList(new ArrayList<>());
        if (!apartmentsList.isEmpty()) {
            for (Apartment apartment : apartmentsList) {
                if (apartment.getRoomNumber() >= minFloor && apartment.getRoomNumber() <= maxFloor) {
                    apartments.add(apartment);
                }
            }
        }
        if (apartments.isEmpty()) {
            apartments = null;
        }
        return apartments;
    }

    /**
     * Returns apartments-list with the square that grater then specified square.
     * If list don't contains such elements then return "empty" apartment.
     */
    public static ObservableList<Apartment> getApartmentsWithSquare(double square) {
        ObservableList<Apartment> apartments = FXCollections.observableList(new ArrayList<>());
        if (!apartmentsList.isEmpty()) {
            for (Apartment apartment : apartmentsList) {
                if (apartment.getSquare() > square) {
                    apartments.add(apartment);
                }
            }
        }
        if (apartments.isEmpty()) {
            apartments = null;
        }
        return apartments;
    }
}
