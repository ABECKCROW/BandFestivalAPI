package com.lesson9.bandlist.service;

import com.lesson9.bandlist.CreateBandForm;
import com.lesson9.bandlist.entity.Band;
import com.lesson9.bandlist.mapper.BandMapper;
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
    public void バンド検索でfindAllUniqueBandsメソッドが呼び出されること() throws NotFoundException {
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of("UTC"));
        List<Band> bands = List.of(
                new Band(1, "ASIAN KUNG-FU GENERATION", currentDate),
                new Band(2, "Rhythmic Toy World", currentDate));
        doReturn(bands).when(bandMapper).findAllUniqueBands();

        List<Band> actual = bandServiceImpl.findAllUniqueBands();
        assertThat(actual).isEqualTo(bands);

        verify(bandMapper, times(1)).findAllUniqueBands();
    }

    @Test
    public void バンド検索で存在するIDを指定した時に正常にバンドが返されること() throws Exception {
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of("UTC"));
        doReturn(Optional.of(new Band(1, "ASIAN KUNG-FU GENERATION", currentDate))).when(bandMapper).findById(1);

        Band actual = bandServiceImpl.findById(1).orElse(null);
        assertThat(actual).isEqualTo(new Band(1, "ASIAN KUNG-FU GENERATION", currentDate));
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

    @Test
    public void バンド検索で今日以前に発表されたバンドが返されること() {
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime beforeDate = currentDate.minusDays(1);
        ZonedDateTime afterDate = currentDate.plusDays(1);
        List<Band> bands = List.of(
                new Band(1, "ASIAN KUNG-FU GENERATION", beforeDate),
                new Band(2, "Rhythmic Toy World", afterDate));
        doReturn(bands).when(bandMapper).findAllUniqueBands();

        List<Band> actual = bandServiceImpl.getBandsByDate(currentDate);
        List<Band> expected = List.of(new Band(1, "ASIAN KUNG-FU GENERATION", beforeDate));
        assertThat(actual).isEqualTo(expected);

        verify(bandMapper, times(1)).findAllUniqueBands();
    }

    @Test
    public void バンド検索で今日以前に発表されたバンドがない時に空のリストが返されること() {
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime afterDate = currentDate.plusDays(1);
        List<Band> bands = List.of(
                new Band(1, "ASIAN KUNG-FU GENERATION", afterDate),
                new Band(2, "Rhythmic Toy World", afterDate));
        doReturn(bands).when(bandMapper).findAllUniqueBands();

        List<Band> actual = bandServiceImpl.getBandsByDate(currentDate);
        List<Band> expected = List.of();
        assertThat(actual).isEqualTo(expected);

        verify(bandMapper, times(1)).findAllUniqueBands();
    }

    @Test
    public void バンド登録ができること() {
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of("UTC"));
        CreateBandForm createBandForm = new CreateBandForm("ASIAN KUNG-FU GENERATION", currentDate);
        Band expectedBand = new Band(1, "ASIAN KUNG-FU GENERATION", currentDate);
        doReturn(new Band(1, "ASIAN KUNG-FU GENERATION", currentDate)).when(bandMapper).createAndGetId(expectedBand);

        assertThat(BandServiceImpl.createBands(CreateBandForm)).isEqualTo(createBandForm);
        verify(bandMapper, times(1)).createAndGetId(expectedBand);
    }

    @Test
    public void 登録したバンドが重複していた時に例外がスローされること() {

    }

    @Test
    public void バンドが更新できること() {

    }

    @Test
    public void 更新するバンドがない時に例外がスローされること() {

    }

    @Test
    public void バンドが削除できること() {

    }

    @Test
    public void 削除するバンドがないときに例外がスローされること() {

    }


}
