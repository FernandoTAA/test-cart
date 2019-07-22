package com.github.fernandotaa.testkart.kartrace.processor;

import com.github.fernandotaa.testkart.kartrace.vo.KartRaceStatisticsVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerLapInfoVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerStatisticsVO;

import java.util.List;

public interface KartRacerProcessor {
    void process(KartRaceStatisticsVO kartRaceStatistics, List<KartRacerStatisticsVO> listKartRacerStatistics, KartRacerLapInfoVO kartRacerLapInfo, KartRacerStatisticsVO kartRacerStatistics);
}
