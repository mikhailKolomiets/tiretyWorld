package util;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by mihail on 27.10.17.
 */
public class GameTimeUtil {

    public static LocalDateTime createGameTimeFromLDT(LocalDateTime localDateTime) {

        int gameSeconds = getSecondsBetweenLDT(localDateTime, LocalDateTime.now()) * 24;

        System.out.println(gameSeconds);
        localDateTime = LocalDateTime.of(0, 1, 1, 0, 0);
        localDateTime = localDateTime.plusSeconds(gameSeconds);

        return localDateTime;
    }

    public static int getYearsBetweenLDT(LocalDateTime firstDate, LocalDateTime secondDate) {

        int years = secondDate.getYear() - firstDate.getYear();

        secondDate = secondDate.minusYears(years);

        if (firstDate.isAfter(secondDate))
            return years - 1;

        return years;
    }

    public static int getSecondsBetweenLDT(LocalDateTime firstDate, LocalDateTime secondDate) {
        return (int) (secondDate.atZone(ZoneId.systemDefault()).toEpochSecond() -
                firstDate.atZone(ZoneId.systemDefault()).toEpochSecond());
    }

    public static void main(String[] args) {
        System.out.println(getYearsBetweenLDT(LocalDateTime.parse("0003-04-14T23:01:36"),
                LocalDateTime.parse("0007-08-14T23:01:36")));
    }
}
