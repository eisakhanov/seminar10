import java.time.Duration;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

public class Task4 {

    static final ZoneId BERLIN = ZoneId.of("Europe/Berlin");

    /** TODO 1: суммарная задержка всех запросов. */
    static Duration totalLatency(List<ResponseLog> logs) {
        return null; // TODO реализуйте
    }

    /** TODO 2: средняя задержка — наносекунды, округление вниз; пустой список — ZERO. */
    static Duration averageLatency(List<ResponseLog> logs) {
        return null; // TODO реализуйте
    }

    /** TODO 3: первый запрос с задержкой строго больше SLA. */
    static Optional<ResponseLog> firstExceeding(List<ResponseLog> logs, Duration sla) {
        return null; // TODO реализуйте
    }

    /** TODO 4: группировка по дню недели локальной даты получения в зоне, дни по возрастанию. */
    static Map<DayOfWeek, List<ResponseLog>> groupByWeekday(List<ResponseLog> logs, ZoneId zone) {
        return null; // TODO реализуйте
    }

    /** TODO 5: отчет о периоде: день недели и dd.MM.uuuu HH:mm XXX, локаль ru-RU, разделитель « – ». */
    static String observationReport(Instant start, Instant end, ZoneId zone) {
        return null; // TODO реализуйте
    }

    // -------------------- контроль: не изменяйте --------------------

    public static void main(String[] args) {
        List<ResponseLog> logs = ResponseData.logs();
        System.out.println("данные: " + logs.size() + " запроса, регион " + BERLIN);

        Duration total = totalLatency(logs);
        check("суммарная-задержка", Duration.ofSeconds(3040), total);

        Duration average = averageLatency(logs);
        check("средняя-задержка", Duration.ofSeconds(1013, 333_333_333L), average);
        check("средняя-пустой-список", Duration.ZERO, averageLatency(List.of()));

        Optional<ResponseLog> slow = firstExceeding(logs, Duration.ofMinutes(5));
        check("первый-медленнее-SLA", Optional.of("REQ-2"),
                slow == null ? null : slow.map(ResponseLog::id));
        check("первый-медленнее-SLA-пустой-список", Optional.empty(),
                firstExceeding(List.of(), Duration.ofMinutes(5)));

        Map<DayOfWeek, List<ResponseLog>> groups = groupByWeekday(logs, BERLIN);
        check("группы-дни-недели", List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY),
                groups == null ? null : new ArrayList<>(groups.keySet()));
        check("группа-воскресенье-запросы", List.of("REQ-2", "REQ-3"),
                groups == null ? null
                        : groups.get(DayOfWeek.SUNDAY).stream()
                                .map(ResponseLog::id).toList());

        String report = observationReport(Instant.parse("2024-03-30T22:30:00Z"),
                Instant.parse("2024-03-31T02:07:10Z"), BERLIN);
        check("отчет-о-периоде",
                "сб 30.03.2024 23:30 +01:00 – вс 31.03.2024 04:07 +02:00",
                report);

        boolean rejected = false;
        try {
            new ResponseLog("REQ-BAD", "billing",
                    Instant.parse("2024-03-30T22:30:00Z"),
                    Instant.parse("2024-03-30T22:00:00Z"));
        } catch (IllegalArgumentException e) {
            rejected = true;
        }
        check("проверка-порядка-моментов", true, rejected);

        if (average != null) {
            System.out.println("дополнительно: среднее " + average.toSeconds() + "."
                    + String.format("%09d", average.getNano())
                    + " с — проверьте округление вниз");
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