package com.entity;

import java.util.List;

public class Character {
	private int id;
	private String name;
    private String description;
    private ParticipationLevel participationLevel;

    public Character(String name, String description, ParticipationLevel participationLevel) {
		super();
		this.name = name;
		this.description = description;
		this.participationLevel = participationLevel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Character() {
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ParticipationLevel getParticipationLevel() {
		return participationLevel;
	}

	public void setParticipationLevel(ParticipationLevel participationLevel) {
		this.participationLevel = participationLevel;
	}

	public Character(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void describeRole() {
	    System.out.println("I don't have the role");
	}
	
	public String getParcitipationLevelStr() {
		return participationLevel.getDisplayName();
	}

}
