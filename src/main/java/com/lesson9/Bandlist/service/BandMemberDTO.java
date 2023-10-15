package com.lesson9.Bandlist.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class BandMemberDTO {
    private String bandName;
    private ZonedDateTime actAnnouncementDate;
    private String memberName;
    private String part;
    private int bandId;
}
