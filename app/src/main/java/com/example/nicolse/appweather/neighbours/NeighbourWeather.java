package com.example.nicolse.appweather.neighbours;

import java.util.List;

/**
 * Created by NicolásE on 26/02/2016.
 */
public class NeighbourWeather {

    private MainWeatherNeighbour main;
    private CoordWeatherNeighbour coord;
    private List<WeatherWeatherNeighbour> weather;
    public MainWeatherNeighbour getMain() {
        return main;
    }

    public List<WeatherWeatherNeighbour> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherWeatherNeighbour> weather) {
        this.weather = weather;
    }

    public void setMain(MainWeatherNeighbour main) {
        this.main = main;
    }

    public CoordWeatherNeighbour getCoord() {
        return coord;
    }

    public void setCoord(CoordWeatherNeighbour coord) {
        this.coord = coord;
    }
}
