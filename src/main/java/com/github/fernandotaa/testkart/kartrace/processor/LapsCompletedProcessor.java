package com.github.fernandotaa.testkart.kartrace.processor;

import com.github.fernandotaa.testkart.kartrace.vo.KartRaceStatisticsVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerLapInfoVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerStatisticsVO;

import java.util.List;

public class LapsCompletedProcessor implements KartRacerProcessor {
    @Override
    public void process(KartRaceStatisticsVO kartRaceStatisticsVO, List<KartRacerStatisticsVO> listKartRacerStatistics, KartRacerLapInfoVO kartRacerLapInfo, KartRacerStatisticsVO kartRacerStatistics) {
        if (kartRacerLapInfo.getCurrentLap() > kartRacerStatistics.getLapsCompleted()) {
            kartRacerStatistics.setLapsCompleted(kartRacerLapInfo.getCurrentLap());
        }
    }
}
