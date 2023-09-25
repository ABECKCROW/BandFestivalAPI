package com.lesson9.Bandlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UpdateBandForm {
    private String bandName;
    private ZonedDateTime actAnnouncementDate;
}
