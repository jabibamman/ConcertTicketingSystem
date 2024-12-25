package esgi.fyc.entity;

import lombok.Data;

@Data
public class Reservation {
    private int id;
    private int userId;
    private int concertId;
    private int ticketNumber;
}
