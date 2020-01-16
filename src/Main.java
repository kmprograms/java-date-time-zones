import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.zone.ZoneRules;

public class Main {
    public static void main(String[] args) {

        // konwersja pomiedzy dwoma strefami czasowymi

        final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

        // Warszawa

        // ustalamy date i czas w Warszawie
        String warsawDateTimeString = "16-01-2020 14:25:00";

        // dokonujemy konwersji do typu LocalDateTime
        LocalDateTime warsawDateTime = LocalDateTime.parse(
                warsawDateTimeString,
                DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

        // wprowadzamy informacje na temat strefy czasowej
        ZoneId warsawZoneId = ZoneId.of("Europe/Warsaw");

        // laczymy wszystkie powyzsze informacje otrzymujac date i czas
        // w Warszawie z uwzglednieniem strefy czasowej
        ZonedDateTime warsawZoneDateTime = warsawDateTime.atZone(warsawZoneId);
        System.out.println("Warsaw: " + warsawZoneDateTime);
        System.out.println("Warsaw: " + warsawZoneDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));

        // New York

        // wprowadzamy informacje na temat strefy czasowej w New York
        ZoneId newYorkZoneId = ZoneId.of("America/New_York");
        ZonedDateTime newYorkZoneDateTime = warsawZoneDateTime.withZoneSameInstant(newYorkZoneId);
        System.out.println("New York: " + newYorkZoneDateTime);
        System.out.println("New York: " + newYorkZoneDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));

        // pobranie wszystkich dostepnych zone id
        // Arrays.stream(TimeZone.getAvailableIDs()).forEach(System.out::println);
        // ZoneId.getAvailableZoneIds().forEach(System.out::println);

        // wyznaczanie przesuniecia dla strefy czasowej
        ZoneId warsawTimeZone = ZoneId.of("Europe/Warsaw");
        ZoneRules warsawZoneRules = warsawTimeZone.getRules();
        Instant instant = Instant.now(); // pobranie aktualnego czasu UTC
        ZoneOffset offset = warsawZoneRules.getOffset(instant);
        System.out.println("Warsaw Offset: " + offset);
        System.out.println("Warsaw Offset In Seconds: " + offset.getTotalSeconds());

        // roznica pomiedzy datami i godzinami w dwoch strefach czasowych
        var difference1 = ChronoUnit.HOURS.between(warsawZoneDateTime, newYorkZoneDateTime);
        System.out.println("Difference 1 = " + difference1);

        // jezeli chcemy poznac roznice w godzinach, minutach, sekundach
        var difference2 = Duration.between(warsawZoneDateTime, newYorkZoneDateTime);
        System.out.println("Difference 2 = " + difference2);

        // jezeli chcemy poznac roznice w latach, miesiach, dniach
        var difference3 = Period.between(warsawZoneDateTime.toLocalDate(), newYorkZoneDateTime.toLocalDate());
        System.out.println("Difference 3 = " + difference3);


    }
}
