package com.github.fernandotaa.testkart.kartrace;

import com.github.fernandotaa.testkart.kartrace.processor.*;
import com.github.fernandotaa.testkart.kartrace.vo.KartRaceStatisticsVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerLapInfoVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerStatisticsVO;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

final public class KartRaceComputer {

    public interface KartRaceComputerFactory {

        static KartRaceComputer createSimple() {
            return new KartRaceComputer();
        }

        static KartRaceComputer create() {
            return create(new LapsCompletedProcessor(), new TotalTimeProcessor(), new AverageSpeedProcessor(), new RaceBestLapProcessor(), new AmountOfTimeAfterWinnerProcessor());
        }

        static KartRaceComputer create(KartRacerProcessor... processors) {
            return new KartRaceComputer(processors);
        }
    }

    private KartRaceStatisticsVO kartRaceStatistics = new KartRaceStatisticsVO();
    private List<KartRacerStatisticsVO> listKartRacerStatistics = new ArrayList<>();
    private Collection<KartRacerProcessor> processors;

    private KartRaceComputer(KartRacerProcessor... processors) {
        this.processors = Arrays.asList(processors);
    }

    public void compute(String logLine) {
        KartRacerLapInfoVO kartRacerLapInfo;
        try {
            kartRacerLapInfo = new KartRacerLapInfoVO(logLine);
        } catch (IllegalArgumentException e) {
            return;
        }

        KartRacerStatisticsVO kartRacerStatistics = getRacerStatistics(kartRacerLapInfo, listKartRacerStatistics);
        processors.forEach(p -> p.process(kartRaceStatistics, Collections.unmodifiableList(listKartRacerStatistics), kartRacerLapInfo, kartRacerStatistics));
    }

    public String summarizeRacers(Function<KartRacerStatisticsVO, String> summarizer) {
        AtomicInteger rank = new AtomicInteger(1);
        return listKartRacerStatistics.stream()
                .sorted(KartRacerStatisticsVO.COMPARATOR_BY_AMOUNT_OF_TIME_AFTER_WINNER_AND_LAPS_COMPLETED_AND_TOTAL_TIME)
                .peek(r -> r.setRank(rank.getAndIncrement()))
                .map(summarizer::apply)
                .collect(Collectors.joining("\n"));
    }

    public String summarizeRace(Function<KartRaceStatisticsVO, String> summarizer) {
        return summarizer.apply(kartRaceStatistics);
    }

    private KartRacerStatisticsVO getRacerStatistics(KartRacerLapInfoVO kartRacerLapInfo, List<KartRacerStatisticsVO> listRacerStatistics) {
        KartRacerStatisticsVO kartRacerStatistics = listRacerStatistics.stream()
                .filter(r -> r.getRacerNumber().equals(kartRacerLapInfo.getRacerNumber()))
                .findFirst()
                .orElseGet(() -> new KartRacerStatisticsVO(kartRacerLapInfo.getRacerNumber(), kartRacerLapInfo.getRacerName()));
        if (!listRacerStatistics.contains(kartRacerStatistics)) {
            listRacerStatistics.add(kartRacerStatistics);
        }
        return kartRacerStatistics;
    }
}
