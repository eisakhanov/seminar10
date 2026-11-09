import java.time.Instant;
import java.util.List;

public final class ShiftEvents {
    private ShiftEvents() { }

    /** События ночной смены — фиксированный набор для контроля. */
    public static List<Instant> events() {
        return List.of(
                Instant.parse("2026-05-11T21:59:00Z"),
                Instant.parse("2026-05-11T22:00:00Z"),
                Instant.parse("2026-05-12T10:30:00Z"),
                Instant.parse("2026-05-12T21:59:59Z"),
                Instant.parse("2026-05-12T22:00:00Z"),
                Instant.parse("2026-05-13T05:00:00Z"));
    }
}