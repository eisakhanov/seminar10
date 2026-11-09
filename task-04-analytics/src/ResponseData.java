import java.time.Instant;
import java.util.List;

public final class ResponseData {
    private ResponseData() { }

    /** Запросы к сервису — фиксированный набор для контроля. */
    public static List<ResponseLog> logs() {
        return List.of(
                new ResponseLog("REQ-1", "billing",
                        Instant.parse("2024-03-30T22:30:00Z"),
                        Instant.parse("2024-03-30T22:34:00Z")),
                new ResponseLog("REQ-2", "gateway",
                        Instant.parse("2024-03-30T23:35:00Z"),
                        Instant.parse("2024-03-31T00:14:30Z")),
                new ResponseLog("REQ-3", "auth",
                        Instant.parse("2024-03-31T02:00:00Z"),
                        Instant.parse("2024-03-31T02:07:10Z")));
    }
}