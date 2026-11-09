import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * Правило повторяющейся встречи: день недели, локальное время и зона
 * организатора.
 */
public record MeetingRule(DayOfWeek dayOfWeek, LocalTime at, ZoneId zone) {
}