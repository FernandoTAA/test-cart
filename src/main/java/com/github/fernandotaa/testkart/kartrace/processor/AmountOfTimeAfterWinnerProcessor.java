package com.github.fernandotaa.testkart.kartrace.processor;

import com.github.fernandotaa.testkart.kartrace.vo.KartRaceStatisticsVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerLapInfoVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerStatisticsVO;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AmountOfTimeAfterWinnerProcessor implements KartRacerProcessor {
    @Override
    public void process(KartRaceStatisticsVO kartRaceStatistics, List<KartRacerStatisticsVO> listKartRacerStatistics, KartRacerLapInfoVO kartRacerLapInfo, KartRacerStatisticsVO kartRacerStatistics) {
        Integer currentLap = listKartRacerStatistics.stream()
                .mapToInt(KartRacerStatisticsVO::getLapsCompleted)
                .max().orElse(0);

        Optional<KartRacerStatisticsVO> kartRacerStatisticsFirstPlace = listKartRacerStatistics.stream()
                .filter(r -> currentLap.equals(r.getLapsCompleted()))
                .min(Comparator.comparing(KartRacerStatisticsVO::getTotalTime));

        if (!kartRacerStatisticsFirstPlace.isPresent()) {
            return;
        }

        listKartRacerStatistics.stream()
                .filter(r -> currentLap.equals(r.getLapsCompleted()))
                .forEach(r -> r.calcAmountOfTimeAfterWinner(kartRacerStatisticsFirstPlace.get()));
        listKartRacerStatistics.stream()
                .filter(r -> !currentLap.equals(r.getLapsCompleted()))
                .forEach(KartRacerStatisticsVO::inOtherLap);
    }
}
