package com.lesson9.Bandlist.controller.response;

import com.lesson9.Bandlist.entity.Band;
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
