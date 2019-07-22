package com.github.fernandotaa.testkart.kartrace.processor;

import com.github.fernandotaa.testkart.kartrace.vo.KartRaceStatisticsVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerLapInfoVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerStatisticsVO;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class TotalTimeProcessor implements KartRacerProcessor {
    @Override
    public void process(KartRaceStatisticsVO kartRaceStatisticsVO, List<KartRacerStatisticsVO> listKartRacerStatistics, KartRacerLapInfoVO kartRacerLapInfo, KartRacerStatisticsVO kartRacerStatistics) {
        Duration duration = Duration.between(LocalTime.of(0, 0), kartRacerLapInfo.getLapTime());
        LocalTime totalTime = kartRacerStatistics.getTotalTime().plus(duration);
        kartRacerStatistics.setTotalTime(totalTime);
    }
}
