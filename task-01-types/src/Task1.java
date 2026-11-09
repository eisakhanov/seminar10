import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

public class Task1 {

    /** TODO 1: дата дня рождения в конкретном году; 29 февраля
     *  невисокосного года — IllegalArgumentException. */
    static LocalDate birthdayIn(MonthDay birthday, Year year) {
        return null; // TODO реализуйте
    }

    /** TODO 2: последний календарный день расчетного месяца. */
    static LocalDate billingEnd(YearMonth month) {
        return null; // TODO реализуйте
    }

    /** TODO 3: момент отправки сводки — локальное время в зоне клиента. */
    static Instant digestInstant(LocalDate day, LocalTime at, ZoneId zone) {
        return null; // TODO реализуйте
    }

    /** TODO 4: совпадают ли месяц и день с днем рождения. */
    static Boolean isBirthday(MonthDay birthday, LocalDate today) {
        return null; // TODO реализуйте
    }

    // -------------------- контроль: не изменяйте --------------------

    public static void main(String[] args) {
        List<ClientProfile> clients = ClientData.clients();
        ClientProfile ivanov = clients.get(0);
        ClientProfile petrova = clients.get(1);
        System.out.println("данные: " + clients.size() + " клиента, регион " + ivanov.zone());

        check("день-рождения-в-году", LocalDate.parse("2026-05-12"),
                birthdayIn(ivanov.birthday(), Year.of(2026)));
        check("день-рождения-29-февраля-високосный", LocalDate.parse("2028-02-29"),
                birthdayIn(petrova.birthday(), Year.of(2028)));

        Boolean rejected = null;
        try {
            birthdayIn(petrova.birthday(), Year.of(2026));
            rejected = false;
        } catch (IllegalArgumentException e) {
            rejected = true;
        } catch (DateTimeException e) {
            System.out.println("FAIL день-рождения-29-февраля-обычный: "
                    + "сначала isValidYear, потом IllegalArgumentException");
        }
        if (rejected != null) {
            check("день-рождения-29-февраля-обычный", true, rejected);
        }

        check("конец-месяца", LocalDate.parse("2026-02-28"),
                billingEnd(ivanov.billingMonth()));
        check("конец-месяца-високосный", LocalDate.parse("2028-02-29"),
                billingEnd(petrova.billingMonth()));

        Instant morning = digestInstant(LocalDate.parse("2026-05-12"),
                ivanov.digestAt(), ivanov.zone());
        check("сводка-утренняя", Instant.parse("2026-05-12T07:30:00Z"), morning);

        Instant evening = digestInstant(LocalDate.parse("2026-01-12"),
                petrova.digestAt(), petrova.zone());
        check("сводка-вечерняя", Instant.parse("2026-01-12T17:00:00Z"), evening);

        check("сегодня-день-рождения", true,
                isBirthday(ivanov.birthday(), LocalDate.parse("2026-05-12")));
        check("не-день-рождения", false,
                isBirthday(ivanov.birthday(), LocalDate.parse("2026-05-13")));

        if (morning != null && evening != null) {
            System.out.println("дополнительно: утренняя сводка 12 мая уходит в 07:30:00Z"
                    + " — во сколько уйдет та же сводка 12 января?");
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