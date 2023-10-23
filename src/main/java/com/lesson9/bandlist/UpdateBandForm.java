package com.lesson9.bandlist;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UpdateBandForm {

    @Size(max = 204)
    private String bandName;

    private ZonedDateTime actAnnouncementDate;
}
