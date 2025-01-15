package esgi.fyc.dao;

import esgi.fyc.entity.User;
import esgi.fyc.repository.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code UserDAO} class provides methods to interact with the database
 * for operations related to the {@link User} entity.
 * This includes adding new users, retrieving a user by email, and fetching all users.
 * <p>
 * This class uses {@link DatabaseConnection} for establishing database connections.
 * </p>
 */
public class UserDAO {

    /**
     * Adds a new user to the database.
     *
     * @param user the {@link User} object containing the details of the user to add.
     * @throws SQLException if a database access error occurs or the query fails.
     */
    public void addUser(User user) {
        String query = "INSERT INTO \"user\" (name, email, priority_level) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getPriorityLevel());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a user from the database by their email.
     *
     * @param email the email of the user to retrieve.
     * @return a {@link User} object if a user with the given email exists, otherwise {@code null}.
     * @throws SQLException if a database access error occurs or the query fails.
     */
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM \"user\" WHERE email = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPriorityLevel(resultSet.getInt("priority_level"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return a {@link List} of {@link User} objects representing all users in the database.
     *         If no users are found, an empty list is returned.
     * @throws SQLException if a database access error occurs or the query fails.
     */
    public List<User> getAllUsers() {
        String query = "SELECT * FROM \"user\"";
        List<User> users = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPriorityLevel(resultSet.getInt("priority_level"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}