package com.lesson9.Bandlist;

import com.lesson9.Bandlist.entity.Band;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UpdateBandForm {
    private int id;
    private String bandName;
    private ZonedDateTime actAnnouncementDate;

    public Band toBand() {
        Band band = new Band(id, bandName, actAnnouncementDate);
        return band;
    }
}
