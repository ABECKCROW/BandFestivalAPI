package com.lesson9.bandlist.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Band {
    private int id;
    private String bandName;
    private ZonedDateTime actAnnouncementDate;
}
