package esgi.fyc.entity;

import lombok.Data;

/**
 * Represents a priority level with a label and a corresponding value.
 *
 * <p>This entity is typically used for categorizing or ranking items
 * based on their importance or urgency.</p>
 */
@Data
public class Priority {
    /** The unique identifier of the priority level. */
    private int id;

    /** The label of the priority (e.g., "High", "Medium", "Low"). */
    private String label;

    /** The numerical value representing the priority level. */
    private int priorityValue;
}