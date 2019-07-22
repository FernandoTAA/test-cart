package com.github.fernandotaa.testkart.kartrace.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Comparator.comparing;

public class KartRacerStatisticsVO {

    public static final Comparator<KartRacerStatisticsVO> COMPARATOR_BY_AMOUNT_OF_TIME_AFTER_WINNER_AND_LAPS_COMPLETED_AND_TOTAL_TIME = comparing(KartRacerStatisticsVO::getAmountOfTimeAfterWinner).thenComparing(KartRacerStatisticsVO::getLapsCompleted).thenComparing(KartRacerStatisticsVO::getTotalTime);

    private static final LocalTime TIME_OF_LAST_POSITION = LocalTime.of(23, 59, 59, 999999999);

    @Getter
    private String racerNumber;

    @Getter
    private String racerName;

    @Getter
    @Setter
    private Integer rank = 0;

    @Getter
    @Setter
    private Integer lapsCompleted = 0;

    @Getter
    @Setter
    private LocalTime totalTime = LocalTime.of(0, 0);

    @Getter
    private LocalTime amountOfTimeAfterWinner = TIME_OF_LAST_POSITION;

    private Map<Integer, Number> averageSpeedByLap = new HashMap<>();

    public void inOtherLap() {
        amountOfTimeAfterWinner = TIME_OF_LAST_POSITION;
    }

    public void calcAmountOfTimeAfterWinner(KartRacerStatisticsVO firstPlace) {
        Duration duration = Duration.between(firstPlace.getTotalTime(), totalTime);
        amountOfTimeAfterWinner = LocalTime.of(0, 0, 0).plus(duration);
    }

    public KartRacerStatisticsVO(String racerNumber, String racerName) {
        this.racerNumber = racerNumber;
        this.racerName = racerName;
    }

    public void addLapAndAverageSpeed(Integer lap, Number lapAverageSpeed) {
        averageSpeedByLap.put(lap, lapAverageSpeed);
    }

    public Number getAverageSpeed() {
        return averageSpeedByLap.values().stream()
                .filter(Objects::nonNull)
                .mapToDouble(s -> s.doubleValue())
                .average()
                .orElse(0D);
    }

    public Integer getBestLap() {
        return averageSpeedByLap.entrySet().stream()
                .filter(Objects::nonNull)
                .reduce((e1, e2) -> e1.getValue().doubleValue() < e2.getValue().doubleValue() ? e1 : e2).
                        map(e -> e.getKey()).
                        orElse(0);

    }
}
