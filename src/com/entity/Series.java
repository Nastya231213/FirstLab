package com.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Series {
	private int id;
	private String name;
	private LocalDate dateOfTheStart;
	private String description;
	private SeriesType seriesType;

	public Series(String name, LocalDate dateOfTheStart, String description, SeriesType seriesType) {
		this.name = name;
		this.dateOfTheStart = dateOfTheStart;
		this.description = description;
		this.seriesType = seriesType;
	}

	public Series() {

	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfTheStart() {
		return dateOfTheStart;
	}

	public void setDateOfTheStart(LocalDate dateOfTheStart) {
		this.dateOfTheStart = dateOfTheStart;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSeriesNameInString() {
		return seriesType.getSeriesTypeName();
	}

	public SeriesType getSeriesType() {
		return seriesType;
	}

	public void setSeriesType(SeriesType seriesType) {
		this.seriesType = seriesType;
	}

}
