package co.edu.uptc.model;

import co.edu.uptc.structures.SimpleList;

public class State {
    private SimpleList<City> cities;

    public State() {
        this.cities = new SimpleList<>();
    }

    public SimpleList<City> getCities() {
        return cities;
    }

    public void setCities(SimpleList<City> cities) {
        this.cities = cities;
    }
}
