package model;

public class Question {

	public int id;
	public String difficulty;
	public String text;
	public String answer;

	public Question(int id, String difficulty, String text, String answer) {
		this.id = id;
		this.difficulty = difficulty;
		this.text = text;
		this.answer = answer;
	}

}
