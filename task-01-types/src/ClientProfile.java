import java.time.LocalTime;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.ZoneId;

/**
 * Профиль клиента службы уведомлений. Поля разной природы — разные типы:
 * у дня рождения нет года, у расчетного месяца нет дня, у времени сводки
 * нет даты, а зона офиса превращает локальное время в момент.
 */
public record ClientProfile(
        String name,
        MonthDay birthday,
        YearMonth billingMonth,
        LocalTime digestAt,
        ZoneId zone) {
}