import java.time.Clock;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

public class Task5 {

    static final Clock CLOCK = Clock.fixed(Instant.parse("2025-03-04T00:00:00Z"), ZoneOffset.UTC);

    /** TODO 1: ближайшая дата заданного дня недели: сегодня, если день совпал, иначе следующий такой день. */
    static LocalDate nextOccurrence(LocalDate today, DayOfWeek dayOfWeek) {
        return null; // TODO реализуйте
    }

    /** TODO 2: момент встречи на дату; локальные дата и время привязываются к зоне, время из DST gap отклоняется. */
    static Instant meetingInstant(LocalDate date, LocalTime at, ZoneId zone) {
        return null; // TODO реализуйте
    }

    /** TODO 3: встречи на weeks недель начиная с текущей недели по часам; серия строится по LocalDate (plusWeeks). */
    static List<Instant> nextMeetings(Clock clock, int weeks, MeetingRule rule) {
        return null; // TODO реализуйте
    }

    /** TODO 4: локальное время момента в зоне участника. */
    static LocalTime localTimeFor(ZoneId participantZone, Instant meeting) {
        return null; // TODO реализуйте
    }

    // -------------------- контроль: не изменяйте --------------------

    public static void main(String[] args) {
        MeetingRule weekly = TeamData.weekly();
        System.out.println("данные: встреча по средам 15:30 " + weekly.zone()
                + ", часы зафиксированы на " + CLOCK.instant());

        List<Instant> meetings = nextMeetings(CLOCK, 5, weekly);
        check("встреч-5", 5, meetings == null ? null : meetings.size());
        check("встреча-1-старт", Instant.parse("2025-03-05T14:30:00Z"),
                meetings == null ? null : meetings.get(0));
        check("встреча-5-старт", Instant.parse("2025-04-02T13:30:00Z"),
                meetings == null || meetings.size() < 5 ? null : meetings.get(4));

        check("интервал-до-перевода", Duration.ofHours(168),
                meetings == null || meetings.size() < 2 ? null
                        : Duration.between(meetings.get(0), meetings.get(1)));
        check("интервал-после-перевода", Duration.ofHours(167),
                meetings == null || meetings.size() < 5 ? null
                        : Duration.between(meetings.get(3), meetings.get(4)));

        TeamMember ben = TeamData.members().get(1);
        check("нью-йорк-5-марта", LocalTime.of(9, 30),
                meetings == null || meetings.isEmpty() ? null
                        : localTimeFor(ben.zone(), meetings.get(0)));
        check("нью-йорк-12-марта", LocalTime.of(10, 30),
                meetings == null || meetings.size() < 2 ? null
                        : localTimeFor(ben.zone(), meetings.get(1)));
        check("нью-йорк-2-апреля", LocalTime.of(9, 30),
                meetings == null || meetings.size() < 5 ? null
                        : localTimeFor(ben.zone(), meetings.get(4)));

        List<Instant> empty = nextMeetings(CLOCK, 0, weekly);
        check("weeks-0-пусто", true, empty == null ? null : empty.isEmpty());

        check("повтор-равен", true,
                meetings == null ? null : meetings.equals(nextMeetings(CLOCK, 5, weekly)));

        check("gap-отклоняется", true, rejectsGap());

        if (meetings != null && meetings.size() == 5) {
            System.out.println("дополнительно: интервал 26.03 -> 02.04 "
                    + Duration.between(meetings.get(3), meetings.get(4))
                    + " — серия по LocalDate, а не по 168 часам; у Бена 02.04 снова "
                    + localTimeFor(ben.zone(), meetings.get(4)) + " — почему?");
        }
    }

    /** Назначает встречу на время из gap: ожидается DateTimeException. */
    static Boolean rejectsGap() {
        try {
            Instant i = meetingInstant(LocalDate.parse("2025-03-30"), LocalTime.of(2, 30),
                    ZoneId.of("Europe/Berlin"));
            return i == null ? null : Boolean.FALSE;
        } catch (DateTimeException e) {
            return Boolean.TRUE;
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