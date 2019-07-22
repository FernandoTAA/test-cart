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

@DisplayName("Amount of time after winner processor")
public class AmountOfTimeAfterWinnerProcessorTest {

    @Test
    @DisplayName("[SUCESS] AmountOfTimeAfterWinnerProcessor.process - Calculate amount of time between the winner and each racer.")
    public void processSuccess() {
        KartRacerLapInfoVO kartRacerLapInfo = new KartRacerLapInfoVO("23:49:08.277      038 – F.MASSA                           1         1:02.852                          44,275");
        KartRacerStatisticsVO kartRacerStatistics1 = new KartRacerStatisticsVO("038", "F.MASSA");
        kartRacerStatistics1.setTotalTime(LocalTime.of(0, 2, 2,2 ));
        kartRacerStatistics1.setLapsCompleted(2);
        KartRacerStatisticsVO kartRacerStatistics2 = new KartRacerStatisticsVO("015", "F.ALONSO");
        kartRacerStatistics2.setTotalTime(LocalTime.of(0, 3, 3,3 ));
        kartRacerStatistics2.setLapsCompleted(2);
        List<KartRacerStatisticsVO> listKartRacerStatistics = Arrays.asList(kartRacerStatistics1, kartRacerStatistics2);

        AmountOfTimeAfterWinnerProcessor amountOfTimeAfterWinnerProcessor = new AmountOfTimeAfterWinnerProcessor();
        amountOfTimeAfterWinnerProcessor.process(new KartRaceStatisticsVO(), listKartRacerStatistics, kartRacerLapInfo, kartRacerStatistics1);

        LocalTime amountOfTimeAfterWinner1Expected = LocalTime.of(0, 0, 0);
        Assertions.assertEquals(amountOfTimeAfterWinner1Expected, kartRacerStatistics1.getAmountOfTimeAfterWinner());

        LocalTime amountOfTimeAfterWinner2Expected = LocalTime.of(0, 1, 1, 1);
        Assertions.assertEquals(amountOfTimeAfterWinner2Expected, kartRacerStatistics2.getAmountOfTimeAfterWinner());
    }

    @Test
    @DisplayName("[SUCESS] AmountOfTimeAfterWinnerProcessor.process - Calculate amount of time between the winner and each racer.")
    public void processDifferentLapsCompletedSuccess() {
        KartRacerLapInfoVO kartRacerLapInfo = new KartRacerLapInfoVO("23:49:08.277      038 – F.MASSA                           1         1:02.852                          44,275");
        KartRacerStatisticsVO kartRacerStatistics1 = new KartRacerStatisticsVO("038", "F.MASSA");
        kartRacerStatistics1.setTotalTime(LocalTime.of(0, 2, 2,2 ));
        kartRacerStatistics1.setLapsCompleted(2);
        KartRacerStatisticsVO kartRacerStatistics2 = new KartRacerStatisticsVO("015", "F.ALONSO");
        kartRacerStatistics2.setTotalTime(LocalTime.of(0, 3, 3,3 ));
        kartRacerStatistics2.setLapsCompleted(1);
        List<KartRacerStatisticsVO> listKartRacerStatistics = Arrays.asList(kartRacerStatistics1, kartRacerStatistics2);

        AmountOfTimeAfterWinnerProcessor amountOfTimeAfterWinnerProcessor = new AmountOfTimeAfterWinnerProcessor();
        amountOfTimeAfterWinnerProcessor.process(new KartRaceStatisticsVO(), listKartRacerStatistics, kartRacerLapInfo, kartRacerStatistics1);

        LocalTime amountOfTimeAfterWinner1Expected = LocalTime.of(0, 0, 0);
        Assertions.assertEquals(amountOfTimeAfterWinner1Expected, kartRacerStatistics1.getAmountOfTimeAfterWinner());

        LocalTime amountOfTimeAfterWinner2Expected = LocalTime.of(23, 59, 59,999999999);
        Assertions.assertEquals(amountOfTimeAfterWinner2Expected, kartRacerStatistics2.getAmountOfTimeAfterWinner());
    }
}