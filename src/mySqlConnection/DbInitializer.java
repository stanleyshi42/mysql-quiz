package mySqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DbInitializer {

	final static String url = "jdbc:mysql://localhost:3306/sql_quiz";
	final static String user = "root";
	final static String password = "root";

	public static void init() {
		initQuestionTable();
		initHighScoreTable();
	}

	// Creates and populates a table for the sql_quiz database
	public static void initQuestionTable() {

		try {
			Connection connection = DriverManager.getConnection(url, user, password);

			// Create table
			Statement statement = connection.createStatement();

			String query = """
					DROP TABLE IF EXISTS question
					""";
			statement.execute(query);

			query = """
					CREATE TABLE IF NOT EXISTS question (
					id INT PRIMARY KEY AUTO_INCREMENT,
					difficulty VARCHAR(255) NOT NULL,
					text VARCHAR(255) NOT NULL,
					answer VARCHAR(255) NOT NULL
					)
					""";
			statement.execute(query);

			// Insert data into table
			query = "INSERT INTO question (difficulty, text, answer) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "easy");
			preparedStatement.setString(2, "Write a statement that creates a table");
			preparedStatement.setString(3, "CREATE TABLE table_name (column1 datatype, column2 datatype, .... );");
			preparedStatement.executeUpdate();

			preparedStatement.setString(1, "easy");
			preparedStatement.setString(2, "Write a statement that selects all the columns of a table");
			preparedStatement.setString(3, "SELECT * FROM table_name;");
			preparedStatement.executeUpdate();

			preparedStatement.setString(1, "easy");
			preparedStatement.setString(2, "Write a statement that selects specified columns of a table");
			preparedStatement.setString(3, "SELECT column1, column2, ... FROM table_name;");
			preparedStatement.executeUpdate();

			preparedStatement.setString(1, "easy");
			preparedStatement.setString(2, "Write a statement that creates a database");
			preparedStatement.setString(3, "CREATE DATABASE database_name;");
			preparedStatement.executeUpdate();

			preparedStatement.setString(1, "easy");
			preparedStatement.setString(2, "Write a statement that drops a table");
			preparedStatement.setString(3, "DROP TABLE table_name;");
			preparedStatement.executeUpdate();

			preparedStatement.setString(1, "medium");
			preparedStatement.setString(2, "Write a statement that returns the number of rows in a table");
			preparedStatement.setString(3, "SELECT COUNT(column_name) FROM table_name");
			preparedStatement.executeUpdate();

			preparedStatement.setString(1, "medium");
			preparedStatement.setString(2, "Write a statement that returns the average of a column");
			preparedStatement.setString(3, "SELECT AVG(column_name) FROM table_name;");
			preparedStatement.executeUpdate();

			preparedStatement.setString(1, "medium");
			preparedStatement.setString(2, "Write a statement that selects a column with a WHERE condition ");
			preparedStatement.setString(3, "SELECT column1 FROM table_name WHERE condition;");
			preparedStatement.executeUpdate();

			preparedStatement.setString(1, "hard");
			preparedStatement.setString(2, "Write a statement that selects a column and sorts it by ascending order");
			preparedStatement.setString(3, "SELECT column1 FROM table_name ORDER BY column1 ASC;");
			preparedStatement.executeUpdate();

			// TODO make more hard questions
			preparedStatement.setString(1, "hard");
			preparedStatement.setString(2, "Write a statement that selects a column and sorts it by ascending order");
			preparedStatement.setString(3, "SELECT column1 FROM table_name ORDER BY column1 ASC;");
			preparedStatement.executeUpdate();

			preparedStatement.setString(1, "hard");
			preparedStatement.setString(2, "Write a statement that selects a column and sorts it by ascending order");
			preparedStatement.setString(3, "SELECT column1 FROM table_name ORDER BY column1 ASC;");
			preparedStatement.executeUpdate();

			// Close resources
			connection.close();
			statement.close();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void initHighScoreTable() {

		try {
			Connection connection = DriverManager.getConnection(url, user, password);

			// Create table
			Statement statement = connection.createStatement();

			String query = """
					DROP TABLE IF EXISTS high_score
					""";
			statement.execute(query);

			query = """
					CREATE TABLE IF NOT EXISTS score (
					id INT PRIMARY KEY AUTO_INCREMENT,
					name VARCHAR(255) NOT NULL,
					value INT NOT NULL
					)
					""";
			statement.execute(query);

			// Close resources
			connection.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
