package com.entity;

public interface Series {
    String getSeriesTypeName();
    void displayInfo();
}

public class CanonicalSeries implements Series {
    private final String seriesTypeName;

    public CanonicalSeries() {
        this.seriesTypeName = "Canonical";
    }

    @Override
    public String getSeriesTypeName() {
        return seriesTypeName;
    }

    @Override
    public void displayInfo() {
        System.out.println("Series Type: " + getClass().getSimpleName());
        System.out.println("String Representation: " + getSeriesTypeName());
    }
}

public class MovieBasedSeries implements Series {
    private final String seriesTypeName;

    public MovieBasedSeries() {
        this.seriesTypeName = "Movie-based";
    }

    @Override
    public String getSeriesTypeName() {
        return seriesTypeName;
    }

    @Override
    public void displayInfo() {
        System.out.println("Series Type: " + getClass().getSimpleName());
        System.out.println("String Representation: " + getSeriesTypeName());
    }
}

public class UnofficialSeries implements Series {
    private final String seriesTypeName;

    public UnofficialSeries() {
        this.seriesTypeName = "Unofficial";
    }

    @Override
    public String getSeriesTypeName() {
        return seriesTypeName;
    }

    @Override
    public void displayInfo() {
        System.out.println("Series Type: " + getClass().getSimpleName());
        System.out.println("String Representation: " + getSeriesTypeName());
    }
}
