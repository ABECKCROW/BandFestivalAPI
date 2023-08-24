package com.lesson9.Bandlist;

public class BandResponse {
    private int id;
    private String bandName;


    public BandResponse(Band bandName) {
        this.id = bandName.getId();
        this.bandName = bandName.getBandName();
    }

    public int getId() {
        return id;
    }

    public String getBandName() {
        return bandName;
    }
}
