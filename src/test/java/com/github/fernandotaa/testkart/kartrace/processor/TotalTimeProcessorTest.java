package com.github.fernandotaa.testkart.kartrace.processor;

import com.github.fernandotaa.testkart.kartrace.vo.KartRaceStatisticsVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerLapInfoVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerStatisticsVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@DisplayName("Total time calculation processor")
public class TotalTimeProcessorTest {

    @Test
    @DisplayName("[SUCESS] TotalTimeProcessor.process - Total time calculation with empty total time in the racer statistics.")
    public void processWithEmptyTotalTimeInTheRacerStatisticsSuccess() {
        KartRacerLapInfoVO kartRacerLapInfo = new KartRacerLapInfoVO("23:49:08.277      038 – F.MASSA                           1         1:02.852                          44,275");
        KartRacerStatisticsVO kartRacerStatistics = new KartRacerStatisticsVO("038", "F.MASSA");
        List<KartRacerStatisticsVO> listKartRacerStatistics = Arrays.asList(kartRacerStatistics);

        TotalTimeProcessor totalTimeProcessor = new TotalTimeProcessor();
        totalTimeProcessor.process(new KartRaceStatisticsVO(), listKartRacerStatistics, kartRacerLapInfo, kartRacerStatistics);

        LocalTime totalTimeExpected = LocalTime.of(0, 1, 02, 852000000);
        Assertions.assertEquals(totalTimeExpected, kartRacerStatistics.getTotalTime());
    }

    @Test
    @DisplayName("[SUCESS] TotalTimeProcessor.process - Total time calculation with some total time value assigned in the racer statistics.")
    public void processWithSomeTotalTimeValueAssignedInTheRacerStatisticsSuccess() {
        KartRacerLapInfoVO kartRacerLapInfo = new KartRacerLapInfoVO("23:49:08.277      038 – F.MASSA                           1         1:02.852                          44,275");
        KartRacerStatisticsVO kartRacerStatistics = new KartRacerStatisticsVO("038", "F.MASSA");
        kartRacerStatistics.setTotalTime(LocalTime.of(0, 1, 11, 111000000));
        List<KartRacerStatisticsVO> listKartRacerStatistics = Arrays.asList(kartRacerStatistics);

        TotalTimeProcessor totalTimeProcessor = new TotalTimeProcessor();
        totalTimeProcessor.process(new KartRaceStatisticsVO(), listKartRacerStatistics, kartRacerLapInfo, kartRacerStatistics);

        LocalTime totalTimeExpected = LocalTime.of(0, 2, 13, 963000000);
        Assertions.assertEquals(totalTimeExpected, kartRacerStatistics.getTotalTime());
    }
}
