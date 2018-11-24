package com.example.game.domain;

public class User {
	
	String name;
	String num;
	public User() {
		super();
	}
	public User(String name, String num) {
		super();
		this.name = name;
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", num=" + num + "]";
	}

}
