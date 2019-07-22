package com.github.fernandotaa.testkart.kartrace.vo;

import lombok.Data;

@Data
public class KartRaceStatisticsVO {

    private Number averageSpeedOfBestLap;
    private Integer bestLap;
    private KartRacerStatisticsVO kartRacerStatistics;

}
