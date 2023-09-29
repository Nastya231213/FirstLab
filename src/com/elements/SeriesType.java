package com.elements;

public enum SeriesType {

	CANONICAL("Canonical"),MOVIE_BASED("Movie-based"),UNOFFICIAL("Unofficial");
	
	private final String seriesTypeName;
	private SeriesType(String seriesTypeName) {
		this.seriesTypeName=seriesTypeName;
	}
	public String getSeriesTypeName() {
		return seriesTypeName;
	}
	
	public static SeriesType getSeriesTypeFromString(String typeName) {
		for(SeriesType type:SeriesType.values()){
			if(type.getSeriesTypeName().equals(typeName)) {
				return type;
			}
		}
		return null;
	}
	

}
