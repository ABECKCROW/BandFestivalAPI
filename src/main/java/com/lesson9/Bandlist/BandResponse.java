package com.lesson9.Bandlist;

import lombok.Getter;

@Getter
public class BandResponse {
    private int id;
    private String bandName;

    public BandResponse(Band bandName) {
        this.id = bandName.getId();
        this.bandName = bandName.getBandName();
    }
}
