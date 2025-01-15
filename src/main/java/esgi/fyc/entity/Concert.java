package esgi.fyc.entity;

import lombok.Data;

/**
 * Represents a concert event with details such as name, date,
 * and the number of tickets available.
 *
 * <p>This entity is used to store and manage information about concerts.</p>
 */
@Data
public class Concert {
    /** The unique identifier of the concert. */
    private int id;

    /** The name of the concert. */
    private String name;

    /** The date of the concert as a string (consider using LocalDate). */
    private String date;

    /** The total number of tickets available for the concert. */
    private int totalTickets;

    /** The number of tickets still available for the concert. */
    private int remainingTickets;
}