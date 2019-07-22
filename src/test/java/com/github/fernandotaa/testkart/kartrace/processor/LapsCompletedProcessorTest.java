package com.github.fernandotaa.testkart.kartrace.processor;

import com.github.fernandotaa.testkart.kartrace.vo.KartRaceStatisticsVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerLapInfoVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerStatisticsVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

@DisplayName("Laps completed processor")
public class LapsCompletedProcessorTest {

    @Test
    @DisplayName("[SUCESS] TotalTimeProcessor.process - Laps completed calculation with empty laps completed value in the racer statistics.")
    public void processLapsCompletedWithEmptyLapsCompletedValueInTheRacerStatistcsSuccess() {
        KartRacerLapInfoVO kartRacerLapInfo = new KartRacerLapInfoVO("23:49:08.277      038 – F.MASSA                           1         1:02.852                          44,275");
        KartRacerStatisticsVO kartRacerStatistics = new KartRacerStatisticsVO("038", "F.MASSA");
        List<KartRacerStatisticsVO> listKartRacerStatistics = Arrays.asList(kartRacerStatistics);

        LapsCompletedProcessor lapsCompletedProcessor = new LapsCompletedProcessor();
        lapsCompletedProcessor.process(new KartRaceStatisticsVO(), listKartRacerStatistics, kartRacerLapInfo, kartRacerStatistics);

        Integer lapsCompletedExpected = 1;
        Assertions.assertEquals(lapsCompletedExpected, kartRacerStatistics.getLapsCompleted());
    }

    @Test
    @DisplayName("[SUCESS] TotalTimeProcessor.process - Laps completed calculation with laps completed value in the racer lap info less than the value in the racer statistics greater.")
    public void processLapsCompletedWithLapsCompletedValueInTheRacerLapInfoLessThanTheValueInTheStatistcsSuccess() {
        KartRacerLapInfoVO kartRacerLapInfo = new KartRacerLapInfoVO("23:49:08.277      038 – F.MASSA                           2         1:02.852                          44,275");
        KartRacerStatisticsVO kartRacerStatistics = new KartRacerStatisticsVO("038", "F.MASSA");
        kartRacerStatistics.setLapsCompleted(3);
        List<KartRacerStatisticsVO> listKartRacerStatistics = Arrays.asList(kartRacerStatistics);

        LapsCompletedProcessor lapsCompletedProcessor = new LapsCompletedProcessor();
        lapsCompletedProcessor.process(new KartRaceStatisticsVO(), listKartRacerStatistics, kartRacerLapInfo, kartRacerStatistics);

        Integer lapsCompletedExpected = 3;
        Assertions.assertEquals(lapsCompletedExpected, kartRacerStatistics.getLapsCompleted());
    }

    @Test
    @DisplayName("[SUCESS] TotalTimeProcessor.process - Laps completed calculation with laps completed value in the racer lap info greater than the value in the racer statistics greater.")
    public void processLapsCompletedWithLapsCompletedValueInTheRacerLapInfoGreaterThanTheValueInTheStatistcsSuccess() {

        KartRacerLapInfoVO kartRacerLapInfo = new KartRacerLapInfoVO("23:49:08.277      038 – F.MASSA                           3         1:02.852                          44,275");
        KartRacerStatisticsVO kartRacerStatistics = new KartRacerStatisticsVO("038", "F.MASSA");
        kartRacerStatistics.setLapsCompleted(2);
        List<KartRacerStatisticsVO> listKartRacerStatistics = Arrays.asList(kartRacerStatistics);

        LapsCompletedProcessor lapsCompletedProcessor = new LapsCompletedProcessor();
        lapsCompletedProcessor.process(new KartRaceStatisticsVO(), listKartRacerStatistics, kartRacerLapInfo, kartRacerStatistics);

        Integer lapsCompletedExpected = 3;
        Assertions.assertEquals(lapsCompletedExpected, kartRacerStatistics.getLapsCompleted());
    }
}
