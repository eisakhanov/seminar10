import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public class Task2 {

    static final ZoneId BERLIN = ZoneId.of("Europe/Berlin");
    static final LocalDate DAY = LocalDate.parse("2026-05-12");

    /** TODO 1: начало календарных суток в зоне региона. */
    static ZonedDateTime dayStart(LocalDate day, ZoneId zone) {
        return null; // TODO реализуйте
    }

    /** TODO 2: начало следующих суток в зоне региона. */
    static ZonedDateTime dayEnd(LocalDate day, ZoneId zone) {
        return null; // TODO реализуйте
    }

    /** TODO 3: попадает ли момент в сутки, полуинтервал [start, end). */
    static Boolean contains(Instant event, LocalDate day, ZoneId zone) {
        return null; // TODO реализуйте
    }

    // -------------------- контроль: не изменяйте --------------------

    public static void main(String[] args) {
        List<Instant> events = ShiftEvents.events();
        System.out.println("данные: " + events.size() + " событий, регион " + BERLIN
                + ", сутки " + DAY);

        ZonedDateTime start = dayStart(DAY, BERLIN);
        check("dayStart", Instant.parse("2026-05-11T22:00:00Z"),
                start == null ? null : start.toInstant());
        ZonedDateTime end = dayEnd(DAY, BERLIN);
        check("dayEnd", Instant.parse("2026-05-12T22:00:00Z"),
                end == null ? null : end.toInstant());

        check("e1-до-начала",       false, contains(events.get(0), DAY, BERLIN));
        check("e2-граница-начала",  true,  contains(events.get(1), DAY, BERLIN));
        check("e3-внутри",          true,  contains(events.get(2), DAY, BERLIN));
        check("e4-внутри",          true,  contains(events.get(3), DAY, BERLIN));
        check("e5-граница-конца",   false, contains(events.get(4), DAY, BERLIN));
        check("e6-после-конца",     false, contains(events.get(5), DAY, BERLIN));

        int inDay = 0;
        for (Instant e : events) {
            Boolean hit = contains(e, DAY, BERLIN);
            if (hit == null) {
                inDay = -1;
                break;
            }
            if (hit) inDay++;
        }
        if (inDay >= 0) {
            System.out.println("дополнительно: в сутки попало " + inDay
                    + " события из " + events.size());
        }
    }

    static void check(String name, Object expected, Object actual) {
        if (actual == null) {
            System.out.println("FAIL " + name + ": реализуйте TODO");
        } else if (Objects.equals(expected, actual)) {
            System.out.println("OK   " + name);
        } else {
            System.out.println("FAIL " + name + ": ожидалось " + expected
                    + ", получено " + actual);
        }
    }
}