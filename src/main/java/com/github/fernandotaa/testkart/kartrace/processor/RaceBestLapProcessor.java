package com.github.fernandotaa.testkart.kartrace.processor;

import com.github.fernandotaa.testkart.kartrace.vo.KartRaceStatisticsVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerLapInfoVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerStatisticsVO;

import java.util.List;
import java.util.Objects;

public class RaceBestLapProcessor implements KartRacerProcessor {
    @Override
    public void process(KartRaceStatisticsVO kartRaceStatistics, List<KartRacerStatisticsVO> listKartRacerStatistics, KartRacerLapInfoVO kartRacerLapInfo, KartRacerStatisticsVO kartRacerStatistics) {
        if (Objects.isNull(kartRaceStatistics.getAverageSpeedOfBestLap()) || kartRaceStatistics.getAverageSpeedOfBestLap().doubleValue() > kartRacerLapInfo.getAverageSpeed().doubleValue()) {
            kartRaceStatistics.setAverageSpeedOfBestLap(kartRacerLapInfo.getAverageSpeed());
            kartRaceStatistics.setBestLap(kartRacerLapInfo.getCurrentLap());
            kartRaceStatistics.setKartRacerStatistics(kartRacerStatistics);
        }
    }
}
