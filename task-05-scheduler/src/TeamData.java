import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

public final class TeamData {
    private TeamData() { }

    /** Расписание команды — фиксированное правило для контроля. */
    public static MeetingRule weekly() {
        return new MeetingRule(DayOfWeek.WEDNESDAY, LocalTime.of(15, 30),
                ZoneId.of("Europe/Berlin"));
    }

    /** Участники распределённой команды. */
    public static List<TeamMember> members() {
        return List.of(
                new TeamMember("Анна Мюллер", ZoneId.of("Europe/Berlin")),
                new TeamMember("Бен Картер", ZoneId.of("America/New_York")));
    }
}