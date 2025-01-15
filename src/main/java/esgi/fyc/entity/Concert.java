package esgi.fyc.entity;


import lombok.Data;

@Data
public class Concert {
    private int id;
    private String name;
    private String date;
    private int totalTickets;
    private int remainingTickets;
}
