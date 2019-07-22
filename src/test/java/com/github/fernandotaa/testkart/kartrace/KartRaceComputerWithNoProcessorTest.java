package com.github.fernandotaa.testkart.kartrace;

import com.github.fernandotaa.testkart.kartrace.KartRaceComputer.KartRaceComputerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("KartRaceComputer with no processor.")
public class KartRaceComputerWithNoProcessorTest {

    @Test
    @DisplayName("[SUCCESS] KartRaceComputer.")
    public void computeSuccess() {
        KartRaceComputer kartRaceComputer = KartRaceComputerFactory.createSimple();
        kartRaceComputer.compute("23:49:08.277      038 – F.MASSA                           1         1:02.852                          44,275");
        kartRaceComputer.compute("23:49:30.976      015 – F.ALONSO                          1         1:18.456                          35,47");
        String summarizeRacers = kartRaceComputer.summarizeRacers(r -> String.format("%s - %s", r.getRacerNumber(), r.getRacerName()));

        String summarizeRacersExpected = "038 - F.MASSA\n015 - F.ALONSO";
        Assertions.assertEquals(summarizeRacers, summarizeRacersExpected);
    }

    @Test
    @DisplayName("[ERROR] KartRaceComputer with error in currentTime field, causing record ignoring.")
    public void computeErrorCurrentTime() {
        KartRaceComputer kartRaceComputer = KartRaceComputerFactory.createSimple();
        kartRaceComputer.compute("23:49:08.277      038 – F.MASSA                           1         1:02.852                          44,275");
        kartRaceComputer.compute("23:49:30.97A      015 – F.ALONSO                          1         1:18.456                          35,47");
        String summarizeRacers = kartRaceComputer.summarizeRacers(r -> String.format("%s - %s", r.getRacerNumber(), r.getRacerName()));

        String summarizeRacersExpected = "038 - F.MASSA";
        Assertions.assertEquals(summarizeRacers, summarizeRacersExpected);
    }

    @Test
    @DisplayName("[ERROR] KartRaceComputer with error in currentLap field, causing record ignoring.")
    public void computeErrorCurrentLap() {
        KartRaceComputer kartRaceComputer = KartRaceComputerFactory.createSimple();
        kartRaceComputer.compute("23:49:08.277      038 – F.MASSA                           1         1:02.852                          44,275");
        kartRaceComputer.compute("23:49:30.976      015 – F.ALONSO                          A         1:18.456                          35,47");
        String summarizeRacers = kartRaceComputer.summarizeRacers(r -> String.format("%s - %s", r.getRacerNumber(), r.getRacerName()));

        String summarizeRacersExpected = "038 - F.MASSA";
        Assertions.assertEquals(summarizeRacersExpected, summarizeRacers);
    }

    @Test
    @DisplayName("[ERROR] KartRaceComputer with error in averageSpeed field, causing record ignoring.")
    public void computeErrorAverageSpeed() {
        KartRaceComputer kartRaceComputer = KartRaceComputerFactory.createSimple();
        kartRaceComputer.compute("23:49:08.277      038 – F.MASSA                           1         1:02.852                          44,275");
        kartRaceComputer.compute("23:49:30.976      015 – F.ALONSO                          1         1:18.45A                          35,47");
        String summarizeRacers = kartRaceComputer.summarizeRacers(r -> String.format("%s - %s", r.getRacerNumber(), r.getRacerName()));

        String summarizeRacersExpected = "038 - F.MASSA";
        Assertions.assertEquals(summarizeRacersExpected, summarizeRacers);
    }
}
