package com.lesson9.Bandlist.controller.response;

import com.lesson9.Bandlist.entity.Band;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class BandResponse {
    private int id;
    private String bandName;

    public BandResponse(Band bandName) {
        this.id = bandName.getId();
        this.bandName = bandName.getBandName();
    }
}
