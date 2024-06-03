package mySqlConnection;

import java.sql.*;
import java.util.ArrayList;

public class DbConnector {

	final static String url = "jdbc:mysql://localhost:3306/sql_quiz";
	final static String user = "root";
	final static String password = "root";

	// Returns all questions of a given difficulty
	// 1 = easy, 2 = medium, 3 = hard
	public static ArrayList<Question> getQuestions(int difficulty) {

		ArrayList<Question> questions = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection(url, user, password);

			String query = "SELECT * FROM question WHERE difficulty = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			switch (difficulty) {
			case 1:
				statement.setString(1, "easy");
				break;
			case 2:
				statement.setString(1, "medium");
				break;
			case 3:
				statement.setString(1, "hard");
				break;
			}

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String diff = resultSet.getString("difficulty");
				String text = resultSet.getString("text");
				String answer = resultSet.getString("answer");

				questions.add(new Question(id, diff, text, answer));
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return questions;
	}

	public static boolean addScore(String name, int score) {
		try {
			Connection connection = DriverManager.getConnection(url, user, password);

			String query = """
					INSERT INTO score (name, value)
					VALUES (?, ?)
					""";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setInt(2, score);
			statement.executeUpdate();

			connection.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	// Returns scores in desc order
	public static ArrayList<Score> getScores() {

		ArrayList<Score> scores = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection(url, user, password);

			String query = "SELECT * FROM score ORDER BY value DESC";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int value = resultSet.getInt("value");

				scores.add(new Score(id, name, value));
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return scores;

	}

}
