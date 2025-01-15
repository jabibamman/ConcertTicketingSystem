package esgi.fyc.dao;

import esgi.fyc.entity.Concert;
import esgi.fyc.repository.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConcertDAO {

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
