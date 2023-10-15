package com.lesson9.Bandlist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Band {
    private int id;
    private String bandName;
    private ZonedDateTime actAnnouncementDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Band band = (Band) o;
        return id == band.id &&
                Objects.equals(bandName, band.bandName) &&
                Objects.equals(actAnnouncementDate, band.actAnnouncementDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bandName, actAnnouncementDate);
    }
}
