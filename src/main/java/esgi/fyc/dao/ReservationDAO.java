package esgi.fyc.dao;

import esgi.fyc.entity.Reservation;
import esgi.fyc.repository.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ReservationDAO} class provides methods to interact with the database
 * for operations related to the {@link Reservation} entity.
 * This includes adding new reservations, retrieving reservations for a specific concert,
 * and calculating the total number of reservations for a concert.
 * <p>
 * This class uses {@link DatabaseConnection} for establishing database connections.
 * </p>
 */
public class ReservationDAO {

    /**
     * Adds a new reservation to the database.
     *
     * @param reservation the {@link Reservation} object containing the details of the reservation to add.
     * @throws SQLException if a database access error occurs or the query fails.
     */
    public void addReservation(Reservation reservation) {
        String query = "INSERT INTO reservation (user_id, concert_id, ticket_number) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservation.getUserId());
            statement.setInt(2, reservation.getConcertId());
            statement.setInt(3, reservation.getTicketNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all reservations for a specific concert.
     *
     * @param concertId the ID of the concert for which reservations are to be retrieved.
     * @return a {@link List} of {@link Reservation} objects for the specified concert.
     *         If no reservations are found, an empty list is returned.
     * @throws SQLException if a database access error occurs or the query fails.
     */
    public List<Reservation> getReservationsForConcert(int concertId) {
        String query = "SELECT * FROM reservation WHERE concert_id = ?";
        List<Reservation> reservations = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, concertId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setId(resultSet.getInt("id"));
                    reservation.setUserId(resultSet.getInt("user_id"));
                    reservation.setConcertId(resultSet.getInt("concert_id"));
                    reservation.setTicketNumber(resultSet.getInt("ticket_number"));
                    reservations.add(reservation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    /**
     * Retrieves the total number of reservations for a specific concert.
     *
     * @param concertId the ID of the concert for which the total number of reservations is to be calculated.
     * @return the total number of reservations for the specified concert. If no reservations are found, returns 0.
     * @throws SQLException if a database access error occurs or the query fails.
     */
    public int getTotalReservationsForConcert(int concertId) {
        String query = "SELECT COUNT(*) FROM reservation WHERE concert_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, concertId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}