package com.github.fernandotaa.testkart;

import com.github.fernandotaa.testkart.kartrace.KartRaceComputer;
import com.github.fernandotaa.testkart.kartrace.KartRaceComputer.KartRaceComputerFactory;
import com.github.fernandotaa.testkart.kartrace.vo.KartRaceStatisticsVO;
import com.github.fernandotaa.testkart.kartrace.vo.KartRacerStatisticsVO;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class ApplicationLauncher {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("mm:ss.SSS");
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("000.000");

    public static void main(String[] args) throws IOException {
        KartRaceComputer kartRaceComputer = KartRaceComputerFactory.create();

        try (
                InputStream inputStream = ApplicationLauncher.class.getResourceAsStream("/kart.log");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                kartRaceComputer.compute(line);
            }
        }

        System.out.println(" Rank - Racer Number - Racer Name                     - Laps Completed - Race Time - Average Speed - Best Lap - Amount Of Time After Winner");
        System.out.println(kartRaceComputer.summarizeRacers(ApplicationLauncher::summarizeRacers));

        System.out.println();
        System.out.println(" Best Lap - Average Speed - Racer Number - Racer Name");
        System.out.println(kartRaceComputer.summarizeRace(ApplicationLauncher::summarizeRace));
    }

    public static String summarizeRacers(KartRacerStatisticsVO kartRacerStatistics) {
        String rank = StringUtils.leftPad(kartRacerStatistics.getRank().toString(), 4);
        String racerNumber = StringUtils.leftPad(kartRacerStatistics.getRacerNumber(), 12);
        String racerName = StringUtils.rightPad(kartRacerStatistics.getRacerName(), 30);
        String lapsCompleted = StringUtils.leftPad(kartRacerStatistics.getLapsCompleted().toString(), 14);
        String raceTime = kartRacerStatistics.getTotalTime().format(TIME_FORMATTER);
        String averageSpeed = StringUtils.leftPad(DECIMAL_FORMAT.format(kartRacerStatistics.getAverageSpeed()), 13);
        String bestLap = StringUtils.leftPad(kartRacerStatistics.getBestLap().toString(), 8);
        String differenceBetweenFirstPlace = StringUtils.leftPad(kartRacerStatistics.getAmountOfTimeAfterWinner().format(TIME_FORMATTER), 27);
        return String.format(" %s   %s   %s   %s   %s   %s   %s   %s", rank, racerNumber, racerName, lapsCompleted, raceTime, averageSpeed, bestLap, differenceBetweenFirstPlace);
    }

    public static String summarizeRace(KartRaceStatisticsVO kartRaceStatistics) {
        String bestLap = StringUtils.leftPad(kartRaceStatistics.getBestLap().toString(), 8);
        String averageSpeedOfBestLap = StringUtils.leftPad(DECIMAL_FORMAT.format(kartRaceStatistics.getAverageSpeedOfBestLap()), 13);
        String racerNumber = StringUtils.leftPad(kartRaceStatistics.getKartRacerStatistics().getRacerNumber(), 12);
        String racerName = StringUtils.rightPad(kartRaceStatistics.getKartRacerStatistics().getRacerName(), 40);
        return String.format(" %S   %s   %s   %s", bestLap, averageSpeedOfBestLap, racerNumber, racerName);
    }
}
