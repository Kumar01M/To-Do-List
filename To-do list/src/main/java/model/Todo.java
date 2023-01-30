package model;

public class Todo {
	
	private int id;
	private String data;
	private int check;
	
	public Todo() {}
	
	public Todo(int id, String data, int check) {
		this.id = id;
		this.data = data;
		this.check = check;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
