import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.List;

public final class ClientData {
    private ClientData() { }

    /** Клиенты службы уведомлений — фиксированный набор для контроля. */
    public static List<ClientProfile> clients() {
        return List.of(
                new ClientProfile("Иванов", MonthDay.of(Month.MAY, 12),
                        YearMonth.of(2026, Month.FEBRUARY), LocalTime.of(9, 30),
                        ZoneId.of("Europe/Berlin")),
                new ClientProfile("Петрова", MonthDay.of(Month.FEBRUARY, 29),
                        YearMonth.of(2028, Month.FEBRUARY), LocalTime.of(18, 0),
                        ZoneId.of("Europe/Berlin")));
    }
}