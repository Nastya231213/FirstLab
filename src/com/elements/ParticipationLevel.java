package com.elements;

public enum ParticipationLevel {
	MAIN("Main"), SECONDARY("Secondary"), EPISODIC("Episodic");

	private final String displayName;

	private ParticipationLevel(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	};
	
}
