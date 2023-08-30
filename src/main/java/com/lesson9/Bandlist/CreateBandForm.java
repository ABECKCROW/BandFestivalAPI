package com.lesson9.Bandlist;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class CreateBandForm {
    boolean isValidName(String bandName) {
        return bandName != null && !bandName.isEmpty() && bandName.length() <= 204;
    }

    @NotBlank(message = "名前の入力をしてください。")
    private final int id;
    private final String bandName;
    private final ZonedDateTime actAnnouncementDate;

    public CreateBandForm(int id, String bandName, ZonedDateTime actAnnouncementDate) {
        this.id = id;
        this.bandName = bandName;
        this.actAnnouncementDate = actAnnouncementDate;
    }
}
