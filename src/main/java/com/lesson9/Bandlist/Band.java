package com.lesson9.Bandlist;

import java.time.ZonedDateTime;

public class Band {
    private int id;
    private String bandName;
    private ZonedDateTime actAnnouncementDate;

    public Band() {
        this.id = id;
        this.bandName = bandName;
        this.actAnnouncementDate = actAnnouncementDate;
    }

    public int getId() {
        return id;
    }

    public String getBandName() {
        return bandName;
    }

    public ZonedDateTime getActAnnouncementDate() {
        return actAnnouncementDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public void setActAnnouncementDate(ZonedDateTime actAnnouncementDate) {
        this.actAnnouncementDate = actAnnouncementDate;
    }
}
