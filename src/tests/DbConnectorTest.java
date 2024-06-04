package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Question;
import sqlQuiz.DbConnector;

public class DbConnectorTest {

	@Test
	public void testGetQuestions() {

		ArrayList<Question> result = DbConnector.getQuestions(1);
		for (Question q : result) {
			assertTrue(q.difficulty.equals("easy"));
			assertTrue(q.text != null);
			assertTrue(q.answer != null);
		}

		result = DbConnector.getQuestions(2);
		for (Question q : result) {
			assertTrue(q.difficulty.equals("medium"));
			assertTrue(q.text != null);
			assertTrue(q.answer != null);
		}

		result = DbConnector.getQuestions(3);
		for (Question q : result) {
			assertTrue(q.difficulty.equals("hard"));
			assertTrue(q.text != null);
			assertTrue(q.answer != null);
		}

	}

}
