package com.lesson9.bandlist.entity;

public class Member {
    private int id;
    private String memberName;
    private String part;
    private int bandId;

    public Member(int id, String memberName, String part, int bandId) {
        this.id = id;
        this.memberName = memberName;
        this.part = part;
        this.bandId = bandId;
    }

    public int getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getPart() {
        return part;
    }

    public int getBandId() {
        return bandId;
    }
}
