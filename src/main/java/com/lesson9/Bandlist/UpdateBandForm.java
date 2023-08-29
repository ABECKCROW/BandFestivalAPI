package com.lesson9.Bandlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateBandForm {
    private int id;
    private String UpdatedName;
    private String actAnnouncementDate;

    public UpdateBandForm() {

    }
}
