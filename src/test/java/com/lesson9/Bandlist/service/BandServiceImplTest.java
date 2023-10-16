package com.lesson9.Bandlist.service;

import com.lesson9.Bandlist.entity.Band;
import com.lesson9.Bandlist.mapper.BandMapper;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    public void バンド検索でfindAllUniqueBandsメソッドが呼び出されること() {
        List<Band> bands = List.of(
                new Band(1, "ASIAN KUNG-FU GENERATION", date),
                new Band(2, "Rhythmic Toy World", date));
        doReturn(bands).when(bandMapper).findAllUniqueBands();

        List<Band> actual = bandServiceImpl.findAllUniqueBands();
        assertThat(actual).isEqualTo(bands);
    }
    
    @Test
    public void バンド検索で存在するIDを指定した時に正常にバンドが返されること() throws Exception {
        doReturn(Optional.of(new Band(1, "ASIAN KUNG-FU GENERATION", date))).when(bandMapper).findById(1);

        Band actual = bandServiceImpl.findById(1).orElse(null);
        assertThat(actual).isEqualTo(new Band(1, "ASIAN KUNG-FU GENERATION", date));
        verify(bandMapper, times(1)).findById(1);
    }

    @Test
    public void バンド検索で存在しないIDを指定した時に例外がスローされること() {
        doReturn(Optional.empty()).when(bandMapper).findById(99);
        assertThatThrownBy(() -> bandServiceImpl.findById(99))
                .isInstanceOfSatisfying(NotFoundException.class, e -> {
                    assertThat(e.getMessage()).isEqualTo("Band not found with ID: " + 99);
                });
        verify(bandMapper, times(1)).findById(99);
    }

    ZonedDateTime date = ZonedDateTime.of(2023, 12, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
}
