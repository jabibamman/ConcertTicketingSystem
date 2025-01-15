package esgi.fyc.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String email;
    private int priorityLevel;
}
