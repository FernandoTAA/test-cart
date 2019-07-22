package com.github.fernandotaa.testkart.kartrace;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

@DisplayName("KartRaceComputer with processors.")
public class KartRaceComputerWithProcessorsTest {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("mm:ss.SSS");

    @Test
    @DisplayName("[SUCCESS] KartRaceComputer.")
    public void computeSuccess() {
        KartRaceComputer kartRaceComputer = KartRaceComputer.KartRaceComputerFactory.create();
        kartRaceComputer.compute("23:49:08.277      038 – F.MASSA                           1         1:02.852                          44,275");
        kartRaceComputer.compute("23:49:30.976      015 – F.ALONSO                          1         1:18.456                          35,47");
        kartRaceComputer.compute("23:50:11.447      038 – F.MASSA                           2         1:03.170                          44,053");
        kartRaceComputer.compute("23:50:37.987      015 – F.ALONSO                          2         1:07.011                          41,528");
        kartRaceComputer.compute("23:51:14.216      038 – F.MASSA                           3         1:02.769                          44,334");
        kartRaceComputer.compute("23:51:46.691      015 – F.ALONSO                          3         1:08.704                          40,504");
        kartRaceComputer.compute("23:52:17.003      038 – F.MASS                            4         1:02.787                          44,321");
        kartRaceComputer.compute("23:53:06.741      015 – F.ALONSO                          4         1:20.050                          34,763");

        String summarizeRacers = kartRaceComputer.summarizeRacers(r -> String.format("%d - %s - %s - %d - %s - %s - %d - %s", r.getRank(), r.getRacerNumber(), r.getRacerName(), r.getLapsCompleted(), r.getTotalTime().format(TIME_FORMATTER), r.getAverageSpeed().toString(), r.getBestLap(), r.getAmountOfTimeAfterWinner().format(TIME_FORMATTER)));
        String summarizeRace = kartRaceComputer.summarizeRace(r -> String.format("%d - %s - %s - %s", r.getBestLap(), r.getAverageSpeedOfBestLap(), r.getKartRacerStatistics().getRacerNumber(), r.getKartRacerStatistics().getRacerName()));

        String summarizeRacersExpected = "1 - 038 - F.MASSA - 4 - 04:11.578 - 44.24575 - 2 - 00:00.000\n2 - 015 - F.ALONSO - 4 - 04:54.221 - 38.06625 - 4 - 00:42.643";
        Assertions.assertEquals(summarizeRacersExpected, summarizeRacers);

        String summarizeRaceExpected = "4 - 34.763 - 015 - F.ALONSO";
        Assertions.assertEquals(summarizeRaceExpected, summarizeRace);
    }
}
