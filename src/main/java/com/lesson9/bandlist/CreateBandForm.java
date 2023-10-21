package com.lesson9.bandlist;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CreateBandForm {

    @NotBlank(message = "名前の入力をしてください。")
    private final String bandName;
    private final ZonedDateTime actAnnouncementDate;

    boolean isValidName(String bandName) {
        return bandName != null && !bandName.isEmpty() && bandName.length() <= 204;
    }
}
