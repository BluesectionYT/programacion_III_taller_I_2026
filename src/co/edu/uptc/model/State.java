package co.edu.uptc.model;

import co.edu.uptc.exceptions.ValueNotFoundException;
import co.edu.uptc.structures.SimpleList;

import java.util.Iterator;
import java.util.concurrent.ForkJoinPool;

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
        StringBuilder result = new  StringBuilder();
        Iterator<City> iterator = cities.iterator();
        for(City city : cities){
            if(city != null){
                result.append(city.toString());
            }
        }
        return result.toString();
    }
}
