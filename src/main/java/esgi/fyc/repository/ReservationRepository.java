package esgi.fyc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import esgi.fyc.entity.Reservation;

public class ReservationRepository {
    private final DatabaseConnection databaseConnection;

    public ReservationRepository() {
        this.databaseConnection = new DatabaseConnection();
    }

    public void save(Reservation reservation) {
        try (Connection connection = databaseConnection.getConnection()) {
            String sql = "INSERT INTO reservation (user_id, concert_id, ticket_number, reserved_at) " +
                        "VALUES (?, ?, ?, ?)";
            
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, reservation.getUserId());
                statement.setInt(2, reservation.getConcertId());
                statement.setInt(3, reservation.getTicketNumber());
                statement.setTimestamp(4, java.sql.Timestamp.valueOf(reservation.getReservedAt()));
                
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde de la réservation", e);
        }
    }
} 