import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public class Task3 {

    static final ZoneId BERLIN = ZoneId.of("Europe/Berlin");
    static final LocalDateTime GAP_TIME = LocalDateTime.parse("2024-03-31T02:30");
    static final LocalDateTime OVERLAP_TIME = LocalDateTime.parse("2024-10-27T02:30");
    static final LocalDateTime PLAIN_TIME = LocalDateTime.parse("2024-03-15T12:00");

    /** TODO 1: не существует ли такое локальное время в зоне (DST gap). */
    static Boolean isGap(LocalDateTime time, ZoneId zone) {
        return null; // TODO реализуйте
    }

    /** TODO 2: список валидных смещений для локального времени. */
    static List<ZoneOffset> validOffsets(LocalDateTime time, ZoneId zone) {
        return null; // TODO реализуйте
    }

    /** TODO 3: значение в overlap с более ранним смещением. */
    static ZonedDateTime atEarlier(LocalDateTime time, ZoneId zone) {
        return null; // TODO реализуйте
    }

    /** TODO 4: значение в overlap с более поздним смещением. */
    static ZonedDateTime atLater(LocalDateTime time, ZoneId zone) {
        return null; // TODO реализуйте
    }

    /** TODO 5: на сколько различаются моменты earlier и later. */
    static Duration overlapShift(LocalDateTime time, ZoneId zone) {
        return null; // TODO реализуйте
    }

    // -------------------- контроль: не изменяйте --------------------

    public static void main(String[] args) {
        System.out.println("зона: " + BERLIN + "; gap " + GAP_TIME
                + ", overlap " + OVERLAP_TIME);

        check("gap-это-gap", true, isGap(GAP_TIME, BERLIN));

        List<ZoneOffset> gapOffsets = validOffsets(GAP_TIME, BERLIN);
        check("gap-смещения-пустые", true,
                gapOffsets == null ? null : gapOffsets.isEmpty());

        check("overlap-не-gap", false, isGap(OVERLAP_TIME, BERLIN));

        check("overlap-смещения",
                List.of(ZoneOffset.ofHours(2), ZoneOffset.ofHours(1)),
                validOffsets(OVERLAP_TIME, BERLIN));

        ZonedDateTime earlier = atEarlier(OVERLAP_TIME, BERLIN);
        check("earlier-смещение", ZoneOffset.ofHours(2),
                earlier == null ? null : earlier.getOffset());

        ZonedDateTime later = atLater(OVERLAP_TIME, BERLIN);
        check("later-смещение", ZoneOffset.ofHours(1),
                later == null ? null : later.getOffset());

        check("overlap-разница-час", Duration.ofHours(1),
                overlapShift(OVERLAP_TIME, BERLIN));

        List<ZoneOffset> plainOffsets = validOffsets(PLAIN_TIME, BERLIN);
        check("обычное-время-одно-смещение", 1,
                plainOffsets == null ? null : plainOffsets.size());

        if (earlier != null && later != null) {
            System.out.println("дополнительно: getValidOffsets ничего не выбрасывает"
                    + " — в отличие от ofStrict");
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