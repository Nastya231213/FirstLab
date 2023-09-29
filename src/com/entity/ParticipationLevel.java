package com.entity;

public enum ParticipationLevel {
	MAIN("Main"), SECONDARY("Secondary"), EPISODIC("Episodic");

	private final String displayName;
	
	private ParticipationLevel(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
    public static ParticipationLevel getParticipationLevelByString(String partLevel) {
    	for(ParticipationLevel type:ParticipationLevel.values()) {
    		if(type.getDisplayName().equals(partLevel)) {
    			return type;
    		}
    	}
    	return null;	
    }

}
