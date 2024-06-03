package sqlQuiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {

	ArrayList<Question> questions;
	int score = 0;
	String name = null;

	private void startQuiz() {
		Scanner sc = new Scanner(System.in);

		System.out.println("===SQL Quiz===");
		System.out.println("Choose a difficulty: ");
		System.out.println("1. Easy");
		System.out.println("2. Medium");
		System.out.println("3. Hard");

		int diffInput = 0;
		String strInput = "";

		try {
			diffInput = sc.nextInt();
			sc.nextLine(); // Clear newline character
			questions = DbConnector.getQuestions(diffInput);
			Collections.shuffle(questions); // Randomize question order

			// final int QUIZ_LENGTH = 3; // Number of questions to ask
			final int QUIZ_LENGTH = questions.size();

			// Ask questions from the list
			for (int i = 0; i < QUIZ_LENGTH; i++) {
				Question question = questions.get(i);
				System.out.println(question.text);

				strInput = sc.nextLine();
				// Check answer
				if (strInput.toLowerCase().equals(question.answer.toLowerCase())) {
					System.out.println("Correct! \n");
					score++;
				} else {
					System.out.println("Incorrect! The answer is:");
					System.out.println(question.answer + "\n");
				}
			}

			System.out.println("Final score: " + score);
			System.out.println("Enter your name:");

			while (name == "" || name == null) {
				name = sc.nextLine();
			}

			DbConnector.addScore(name, score);
			printHighScores();

			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void printHighScores() {

		final int SCORES_TO_DISPLAY = 10;
		ArrayList<Score> scores = DbConnector.getScores();

		System.out.println("===High Scores===");
		for (int i = 0; i < SCORES_TO_DISPLAY; i++) {

			// Stop if out of bounds
			if (i >= scores.size())
				return;

			System.out.print(scores.get(i).name + " . . . . . " + scores.get(i).value + "\n");
		}

	}

	// Return a random question from the given question list
	private Question randomQuestion(ArrayList<Question> questions) {
		Random rand = new Random();
		return questions.get(rand.nextInt(questions.size()));
	}

	private void initDB() {
		DbInitializer.init();
	}

	public static void main(String[] args) {
		Main quiz = new Main();
		quiz.initDB();
		quiz.startQuiz();

	}

}