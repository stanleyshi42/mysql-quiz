package mySqlConnection;

public class Score {
	public int id;
	public String name; // Name of the person who got this score
	public int value; // Value of score

	public Score(int id, String name, int value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

}
