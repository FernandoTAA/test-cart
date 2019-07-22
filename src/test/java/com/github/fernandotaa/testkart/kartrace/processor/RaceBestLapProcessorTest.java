package com.github.fernandotaa.testkart.kartrace.processor;

import com.github.fernandotaa.testkart.kartrace.vo.KartRaceStatisticsVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerLapInfoVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerStatisticsVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

@DisplayName("Race best lap processor")
public class RaceBestLapProcessorTest {

    @Test
    @DisplayName("[SUCESS] RaceBestLapProcessor.process - Best lap calculation with no best lap computed.")
    public void processWithNoBestLapComputedSuccess() {
        KartRaceStatisticsVO kartRaceStatistics = new KartRaceStatisticsVO();
        KartRacerLapInfoVO kartRacerLapInfo = new KartRacerLapInfoVO("23:49:08.277      038 – F.MASSA                           1         1:02.852                          44,275");
        KartRacerStatisticsVO kartRacerStatistics = new KartRacerStatisticsVO("038", "F.MASSA");
        List<KartRacerStatisticsVO> listKartRacerStatistics = Arrays.asList(kartRacerStatistics);

        RaceBestLapProcessor raceBestLapProcessor = new RaceBestLapProcessor();
        raceBestLapProcessor.process(kartRaceStatistics, listKartRacerStatistics, kartRacerLapInfo, kartRacerStatistics);

        Number averageSpeedOfBestLapExpected = 44.275D;
        Integer bestLapExpected = 1;
        String racerNumberOfBestLapExpected = "038";
        Assertions.assertEquals(averageSpeedOfBestLapExpected, kartRaceStatistics.getAverageSpeedOfBestLap());
        Assertions.assertEquals(bestLapExpected, kartRaceStatistics.getBestLap());
        Assertions.assertEquals(racerNumberOfBestLapExpected, kartRaceStatistics.getKartRacerStatistics().getRacerNumber());
    }

    @Test
    @DisplayName("[SUCESS] RaceBestLapProcessor.process - Best lap calculation with one best lap computed.")
    public void processWithOneBestLapComputedSuccess() {
        KartRaceStatisticsVO kartRaceStatistics = new KartRaceStatisticsVO();
        kartRaceStatistics.setAverageSpeedOfBestLap(45D);
        kartRaceStatistics.setBestLap(2);
        kartRaceStatistics.setKartRacerStatistics(new KartRacerStatisticsVO("015", "F.ALONSO"));

        KartRacerLapInfoVO kartRacerLapInfo = new KartRacerLapInfoVO("23:49:08.277      038 – F.MASSA                           1         1:02.852                          44,275");
        KartRacerStatisticsVO kartRacerStatistics = new KartRacerStatisticsVO("038", "F.MASSA");
        List<KartRacerStatisticsVO> listKartRacerStatistics = Arrays.asList(kartRacerStatistics);

        RaceBestLapProcessor raceBestLapProcessor = new RaceBestLapProcessor();
        raceBestLapProcessor.process(kartRaceStatistics, listKartRacerStatistics, kartRacerLapInfo, kartRacerStatistics);

        Number averageSpeedOfBestLapExpected = 44.275D;
        Integer bestLapExpected = 1;
        String racerNumberOfBestLapExpected = "038";
        Assertions.assertEquals(averageSpeedOfBestLapExpected, kartRaceStatistics.getAverageSpeedOfBestLap());
        Assertions.assertEquals(bestLapExpected, kartRaceStatistics.getBestLap());
        Assertions.assertEquals(racerNumberOfBestLapExpected, kartRaceStatistics.getKartRacerStatistics().getRacerNumber());
    }
}
