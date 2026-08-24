package co.edu.uptc.model;

import co.edu.uptc.exceptions.ValueNotFoundException;
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

    public boolean addCity(City city) {
        boolean result = false;
        if(findCity(city.getName()) == null){
            cities.add(city);
            result = true;
        }
        return result;
    }

    public City findCity(String cityName) {
        return this.cities.stream().filter(city -> city.getName().equals(cityName)).findFirst().orElse(null);
    }

    public boolean addSchool(String cityName, School schoolToAdd) throws ValueNotFoundException {
        City city = findCity(cityName);
        if(city == null){
            throw new ValueNotFoundException();
        }
        return city.addSchool(schoolToAdd);
    }

    public boolean addCampus(String cityName, String schoolName, Campus campusToAdd) throws ValueNotFoundException {
        City city = findCity(cityName);
        if(city == null){
            throw new ValueNotFoundException();
        }
        return city.addCampus(schoolName, campusToAdd);
    }

    @Override
    public String toString() {
        return cities.toString();
    }
}
