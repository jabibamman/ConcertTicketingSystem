package esgi.fyc.dao;

import esgi.fyc.entity.Priority;
import esgi.fyc.repository.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PriorityDAO {

    public void addPriority(Priority priority) {
        String query = "INSERT INTO priority (label, priority_value) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, priority.getLabel());
            statement.setInt(2, priority.getPriorityValue());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
