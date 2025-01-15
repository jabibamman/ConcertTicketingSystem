package esgi.fyc.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a user request for a specific concert, including the user's information,
 * the concert ID, the priority of the request, and the time the request was made.
 *
 * <p>This entity is designed to facilitate the management of user requests, allowing
 * prioritization and comparison based on priority and request time.</p>
 */
@Data
@AllArgsConstructor
public class UserRequest implements Comparable<UserRequest> {

    /** The {@link User} who made the request. */
    private User user;

    /** The ID of the concert for which the request was made. */
    private Long concertId;

    /** The priority level of the request. Higher values indicate higher priority. */
    private int priority;

    /** The date and time when the request was made. */
    private LocalDateTime requestTime;

    /**
     * Compares this {@code UserRequest} with another {@code UserRequest} for ordering.
     *
     * <p>Requests are compared first by their priority in descending order (higher priority first).
     * If two requests have the same priority, they are compared by their request time in ascending order
     * (earlier requests come first).</p>
     *
     * @param other the other {@code UserRequest} to compare against.
     * @return a negative integer, zero, or a positive integer as this request
     *         is less than, equal to, or greater than the specified request.
     */
    @Override
    public int compareTo(UserRequest other) {
        if (this.priority != other.priority)
            return Integer.compare(other.priority, this.priority);

        return this.requestTime.compareTo(other.requestTime);
    }
}