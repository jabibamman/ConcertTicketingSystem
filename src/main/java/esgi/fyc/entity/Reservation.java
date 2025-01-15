package esgi.fyc.entity;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * Represents a reservation made by a user for a specific concert.
 *
 * <p>This entity includes details such as the user ID, concert ID,
 * the number of tickets reserved, and the reservation timestamp.</p>
 */
@Data
public class Reservation {
    /** The unique identifier of the reservation. */
    private int id;

    /** The ID of the user who made the reservation. */
    private int userId;

    /** The ID of the concert for which the reservation was made. */
    private int concertId;

    /** The number of tickets reserved for the concert. */
    private int ticketNumber;

    /** The date and time when the reservation was made. */
    private LocalDateTime reservedAt;
}