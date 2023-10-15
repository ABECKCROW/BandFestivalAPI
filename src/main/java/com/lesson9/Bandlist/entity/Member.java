package com.lesson9.Bandlist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {
    private int id;
    private String memberName;
    private String part;
    private int bandId;
}
