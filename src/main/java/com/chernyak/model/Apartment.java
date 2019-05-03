package com.chernyak.model;

import java.io.Serializable;

/**
 * This class can create Apartments.

 */
public class Apartment implements Serializable {
    public static int APARTMENTS_ID = 1;

    private int id;
    private int number;
    private double square;
    private int floor;
    private int roomNumber;
    private String street;
    private String buildType;
    /*
    Lifetime in days
     */
    private int lifeTime;

    public Apartment() {
    }

    public Apartment(
            int number,
            double square,
            int floor,
            int roomNumber,
            String street,
            String buildType,
            int lifeTime) {
        this.number = number;
        this.square = square;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.street = street;
        this.buildType = buildType;
        this.lifeTime = lifeTime;
    }

    public static int getApartmentsId() {
        APARTMENTS_ID++;
        return APARTMENTS_ID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public double getSquare() {
        return square;
    }

    public int getFloor() {
        return floor;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getBuildType() {
        return buildType;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apartment apartment = (Apartment) o;

        if (id != apartment.id) return false;
        if (number != apartment.number) return false;
        if (Double.compare(apartment.square, square) != 0) return false;
        if (floor != apartment.floor) return false;
        if (roomNumber != apartment.roomNumber) return false;
        if (lifeTime != apartment.lifeTime) return false;
        if (street != null ? !street.equals(apartment.street) : apartment.street != null) return false;
        return !(buildType != null ? !buildType.equals(apartment.buildType) : apartment.buildType != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + number;
        temp = Double.doubleToLongBits(square);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + floor;
        result = 31 * result + roomNumber;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (buildType != null ? buildType.hashCode() : 0);
        result = 31 * result + lifeTime;
        return result;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", number=" + number +
                ", square=" + square +
                ", floor=" + floor +
                ", roomNumber=" + roomNumber +
                ", street='" + street + '\'' +
                ", buildType='" + buildType + '\'' +
                ", lifeTime=" + lifeTime +
                '}';
    }
}
