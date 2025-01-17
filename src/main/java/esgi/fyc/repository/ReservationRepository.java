package esgi.fyc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import esgi.fyc.entity.Reservation;

/**
 * Provides data access methods for handling {@link Reservation} entities in the database.
 *
 * <p>This repository is responsible for persisting reservation data into the database,
 * leveraging the {@link DatabaseConnection} utility for establishing connections.</p>
 */
public class ReservationRepository {

    /** An instance of {@link DatabaseConnection} used to obtain database connections. */
    private final DatabaseConnection databaseConnection;

    /**
     * Constructs a new {@code ReservationRepository}.
     * Initializes the {@link DatabaseConnection} used for database interactions.
     */
    public ReservationRepository() {
        this.databaseConnection = new DatabaseConnection();
    }

    /**
     * Persists a {@link Reservation} entity into the database.
     *
     * <p>This method inserts a new reservation record into the database,
     * using the provided user ID, concert ID, ticket number, and reservation timestamp.</p>
     *
     * @param reservation the {@link Reservation} object to be saved in the database.
     * @throws RuntimeException if a database access error occurs during the save operation.
     */
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