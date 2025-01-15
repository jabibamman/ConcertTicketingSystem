package esgi.fyc.entity;

import lombok.Data;

/**
 * Represents a user in the system with details such as name, email,
 * and their priority level.
 *
 * <p>This entity is used to manage user information and their corresponding
 * priority level for different operations.</p>
 */
@Data
public class User {
    /** The unique identifier of the user. */
    private int id;

    /** The name of the user. */
    private String name;

    /** The email address of the user. */
    private String email;

    /** The priority level assigned to the user. */
    private int priorityLevel;
}