package com.lesson9.bandlist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CreateBandForm {

    @NotBlank(message = "名前の入力をしてください。")
    @Size(max = 204)
    private final String bandName;

    @NotNull(message = "日付の入力をしてください。")
    private final ZonedDateTime actAnnouncementDate;
}
