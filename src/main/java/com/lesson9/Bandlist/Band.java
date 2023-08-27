package com.lesson9.Bandlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Band {
    private int id;
    private String bandName;
    private ZonedDateTime actAnnouncementDate;
}
