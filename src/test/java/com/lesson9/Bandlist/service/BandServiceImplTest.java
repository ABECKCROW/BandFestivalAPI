package com.lesson9.Bandlist.service;

import com.lesson9.Bandlist.entity.Band;
import com.lesson9.Bandlist.mapper.BandMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BandServiceImplTest {

    @InjectMocks
    BandServiceImpl bandServiceImpl;

    @Mock
    BandMapper bandMapper;

    @Test
    public void バンド検索で存在するIDを指定した時に正常にバンドが返されること() throws Exception {
        ZonedDateTime date = ZonedDateTime.of(2023, 12, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        doReturn(Optional.of(new Band(1, "ASIAN KUNG-FU GENERATION", date))).when(bandMapper).findById(1);

        Band actual = bandServiceImpl.findById(1).orElse(null);
        assertThat(actual).isEqualTo(new Band(1, "ASIAN KUNG-FU GENERATION", date));
        verify(bandMapper, times(1)).findById(1);
    }
}

