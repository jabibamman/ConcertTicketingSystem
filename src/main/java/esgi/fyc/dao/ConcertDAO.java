package esgi.fyc.dao;

import esgi.fyc.entity.Concert;
import esgi.fyc.repository.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ConcertDAO} class provides methods to interact with the database
 * for operations related to the {@link Concert} entity.
 * This includes adding new concerts and retrieving all concerts.
 * <p>
 * This class relies on {@link DatabaseConnection} for database connections.
 * </p>
 */
public class ConcertDAO {


    /**
     * Adds a new concert to the database.
     *
     * @param concert the {@link Concert} object containing the details of the concert to add.
     * @throws SQLException if a database access error occurs or the query fails.
     */
    public void addConcert(Concert concert) {
        String query = "INSERT INTO concert (name, date, total_tickets, remaining_tickets) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, concert.getName());
            statement.setString(2, concert.getDate());
            statement.setInt(3, concert.getTotalTickets());
            statement.setInt(4, concert.getRemainingTickets());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all concerts from the database.
     *
     * @return a {@link List} of {@link Concert} objects representing all concerts in the database.
     *         If no concerts are found, an empty list is returned.
     * @throws SQLException if a database access error occurs or the query fails.
     */
    public List<Concert> getAllConcerts() {
        String query = "SELECT * FROM concert";
        List<Concert> concerts = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Concert concert = new Concert();
                concert.setId(resultSet.getInt("id"));
                concert.setName(resultSet.getString("name"));
                concert.setDate(resultSet.getString("date"));
                concert.setTotalTickets(resultSet.getInt("total_tickets"));
                concert.setRemainingTickets(resultSet.getInt("remaining_tickets"));
                concerts.add(concert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return concerts;
    }
}
