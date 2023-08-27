package com.lesson9.Bandlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@ToString
@AllArgsConstructor
public class Band {
    private int id;
    private String bandName;
    private ZonedDateTime actAnnouncementDate;
}
