package examples;

import java.sql.*;

public class CRUDOperations {
    private static final String URL = "jdbc:mysql://localhost:3306/sampledb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            // Create operation
            String createQuery = "INSERT INTO users (id, name, email) VALUES (?, ?, ?)";
            try (PreparedStatement createStatement = connection.prepareStatement(createQuery)) {
                createStatement.setInt(1, 2);
                createStatement.setString(2, "Michle");
                createStatement.setString(3, "Michle@example.com");
                createStatement.executeUpdate();
                System.out.println("Record created successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Read operation
            String readQuery = "SELECT * FROM users";
            try (Statement readStatement = connection.createStatement();
                 ResultSet resultSet = readStatement.executeQuery(readQuery)) {
                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id"));
                    System.out.println("Name: " + resultSet.getString("name"));
                    System.out.println("Email: " + resultSet.getString("email"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Update operation
            String updateQuery = "UPDATE users SET email = ? WHERE id = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setString(1, "updated.email@example.com");
                updateStatement.setInt(2, 1);
                updateStatement.executeUpdate();
                System.out.println("Record updated successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Delete operation
            String deleteQuery = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                deleteStatement.setInt(1, 1);
                deleteStatement.executeUpdate();
                System.out.println("Record deleted successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
