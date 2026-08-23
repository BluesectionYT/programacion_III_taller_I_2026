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

    public void addCity(City city) {
        cities.add(city);
    }

    public City findCity(String cityName) {
        return this.cities.stream().filter(city -> city.getName().equals(cityName)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return cities.toString();
    }
}
