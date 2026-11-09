import java.time.Duration;
import java.time.Instant;

/** Запрос к сервису: момент получения и момент ответа. */
public record ResponseLog(String id, String endpoint, Instant receivedAt, Instant sentAt) {

    public ResponseLog {
        // TODO 6: отвергните sentAt раньше receivedAt — IllegalArgumentException
    }

    /** Задержка ответа. */
    public Duration latency() {
        return Duration.between(receivedAt, sentAt);
    }
}