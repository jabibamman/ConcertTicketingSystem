package esgi.fyc.dao;

import esgi.fyc.entity.Priority;
import esgi.fyc.repository.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code PriorityDAO} class provides methods to interact with the database
 * for operations related to the {@link Priority} entity.
 * This includes adding new priorities, retrieving all priorities, and finding a specific
 * priority by its label.
 * <p>
 * This class uses {@link DatabaseConnection} for establishing database connections.
 * </p>
 */
public class PriorityDAO {

    /**
     * Adds a new priority to the database.
     *
     * @param priority the {@link Priority} object containing the details of the priority to add.
     * @throws SQLException if a database access error occurs or the query fails.
     */
    public void addPriority(Priority priority) {
        String query = "INSERT INTO priority (label, priority_value) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, priority.getLabel());
            statement.setInt(2, priority.getPriorityValue());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Consider replacing with a logger for better error handling
        }
    }

    /**
     * Retrieves all priorities from the database.
     *
     * @return a {@link List} of {@link Priority} objects representing all priorities in the database.
     *         If no priorities are found, an empty list is returned.
     * @throws SQLException if a database access error occurs or the query fails.
     */
    public List<Priority> getAllPriorities() {
        String query = "SELECT * FROM priority";
        List<Priority> priorities = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Priority priority = new Priority();
                priority.setId(resultSet.getInt("id"));
                priority.setLabel(resultSet.getString("label"));
                priority.setPriorityValue(resultSet.getInt("priority_value"));
                priorities.add(priority);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priorities;
    }

    /**
     * Retrieves a priority from the database by its label.
     *
     * @param label the label of the priority to retrieve.
     * @return a {@link Priority} object if a priority with the given label exists, otherwise {@code null}.
     * @throws SQLException if a database access error occurs or the query fails.
     */
    public Priority getPriorityByLabel(String label) {
        String query = "SELECT * FROM priority WHERE label = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, label);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Priority priority = new Priority();
                    priority.setId(resultSet.getInt("id"));
                    priority.setLabel(resultSet.getString("label"));
                    priority.setPriorityValue(resultSet.getInt("priority_value"));
                    return priority;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}