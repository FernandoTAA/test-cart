package com.github.fernandotaa.testkart.kartrace.processor;

import com.github.fernandotaa.testkart.kartrace.vo.KartRaceStatisticsVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerLapInfoVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerStatisticsVO;

import java.util.List;

public class AverageSpeedProcessor implements KartRacerProcessor {

    @Override
    public void process(KartRaceStatisticsVO kartRaceStatisticsVO, List<KartRacerStatisticsVO> listKartRacerStatistics, KartRacerLapInfoVO kartRacerLapInfo, KartRacerStatisticsVO kartRacerStatistics) {
        kartRacerStatistics.addLapAndAverageSpeed(kartRacerLapInfo.getCurrentLap(), kartRacerLapInfo.getAverageSpeed());
    }
}
