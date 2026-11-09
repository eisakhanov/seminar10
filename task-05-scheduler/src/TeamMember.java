import java.time.ZoneId;

/**
 * Участник команды: имя и его часовой пояс.
 */
public record TeamMember(String name, ZoneId zone) {
}