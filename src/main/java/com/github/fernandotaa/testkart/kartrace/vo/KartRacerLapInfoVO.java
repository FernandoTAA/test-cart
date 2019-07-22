package com.github.fernandotaa.testkart.kartrace.vo;

import lombok.Getter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Getter
public class KartRacerLapInfoVO {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    private static final Locale PT_LOCALE = Locale.forLanguageTag("pt");

    private LocalTime currentTime;
    private String racerNumber;
    private String racerName;
    private Integer currentLap;
    private LocalTime lapTime;
    private Number averageSpeed;

    public KartRacerLapInfoVO(String data) {
        try {
            currentTime = LocalTime.parse(data.substring(0, 18).trim(), TIME_FORMATTER);
            racerNumber = data.substring(18, 21).trim();
            racerName = data.substring(24, 54).trim();
            currentLap = Integer.parseInt(data.substring(53, 63).trim());
            lapTime = LocalTime.parse(treatMinute(data.substring(63, 76).trim()), TIME_FORMATTER);
            averageSpeed = NumberFormat.getInstance(PT_LOCALE).parse(data.substring(76).trim());
        } catch (ParseException | DateTimeParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private CharSequence treatMinute(String text) {
        if (text.length() == 8) {
            return "00:0" + text;
        }

        return "00:" + text;
    }
}
