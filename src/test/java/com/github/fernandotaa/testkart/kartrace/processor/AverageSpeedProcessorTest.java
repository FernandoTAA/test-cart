package com.github.fernandotaa.testkart.kartrace.processor;

import com.github.fernandotaa.testkart.kartrace.vo.KartRaceStatisticsVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerLapInfoVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerStatisticsVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

@DisplayName("Average Speed processor")
public class AverageSpeedProcessorTest {

    @Test
    @DisplayName("[SUCESS] AverageSpeedProcessor.process - Average Speed calculation with one lap average speed.")
    public void processWithOneLapAverageSpeedSuccess() {
        KartRacerLapInfoVO kartRacerLapInfo = new KartRacerLapInfoVO("23:49:08.277      038 – F.MASSA                           1         1:02.852                          44,275");
        KartRacerStatisticsVO kartRacerStatistics = new KartRacerStatisticsVO("038", "F.MASSA");
        List<KartRacerStatisticsVO> listKartRacerStatistics = Arrays.asList(kartRacerStatistics);

        AverageSpeedProcessor averageSpeedProcessor = new AverageSpeedProcessor();
        averageSpeedProcessor.process(new KartRaceStatisticsVO(), listKartRacerStatistics, kartRacerLapInfo, kartRacerStatistics);

        Number averageSpeedExpected = 44.275D;
        Number bestLapExpected = 1;
        Assertions.assertEquals(averageSpeedExpected, kartRacerStatistics.getAverageSpeed());
        Assertions.assertEquals(bestLapExpected, kartRacerStatistics.getBestLap());
    }

    @Test
    @DisplayName("[SUCESS] AverageSpeedProcessor.process - Average Speed calculation with three lap average speed.")
    public void processWithThreeLapAverageSpeedSuccess() {
        KartRacerLapInfoVO kartRacerLapInfo = new KartRacerLapInfoVO("23:49:08.277      038 – F.MASSA                           3         1:02.852                          44,275");
        KartRacerStatisticsVO kartRacerStatistics = new KartRacerStatisticsVO("038", "F.MASSA");
        kartRacerStatistics.addLapAndAverageSpeed(1, 45.55);
        kartRacerStatistics.addLapAndAverageSpeed(2,43.93);
        List<KartRacerStatisticsVO> listKartRacerStatistics = Arrays.asList(kartRacerStatistics);

        AverageSpeedProcessor averageSpeedProcessor = new AverageSpeedProcessor();
        averageSpeedProcessor.process(new KartRaceStatisticsVO(), listKartRacerStatistics, kartRacerLapInfo, kartRacerStatistics);

        Number averageSpeedExpected = 44.585D;
        Number bestLapExpected = 2;
        Assertions.assertEquals(averageSpeedExpected, kartRacerStatistics.getAverageSpeed());
        Assertions.assertEquals(bestLapExpected, kartRacerStatistics.getBestLap());
    }
}
