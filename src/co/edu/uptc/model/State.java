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

    public String cityInformationBasedInCourses(String cityName) throws ValueNotFoundException {
        City temporalCity = findCity(cityName);
        if(temporalCity == null){
            throw new ValueNotFoundException();
        } else {
            return  temporalCity.cityInformationBasedInCourses();
        }
    }

    public String cityInformationBasedInSchools(String cityName) throws ValueNotFoundException {
        City temporalCity = findCity(cityName);
        if(temporalCity == null){
            throw new ValueNotFoundException();
        } else {
            return  temporalCity.cityInformationBasedInSchools();
        }
    }

    public String schoolInformationByCourses(String[] selection) throws ValueNotFoundException {
        City temporalCity = findCity(selection[0]);
        School temporalSchool;
        if(temporalCity == null){
            throw new ValueNotFoundException();
        } else{
            temporalSchool = temporalCity.findSchool(selection[1]);
            if(temporalSchool == null){
                throw new ValueNotFoundException();
            } else {
                return temporalSchool.schoolInformationBasesInCourses();
            }
        }
    }

    public String schoolInformationByCampus(String[] selection) throws ValueNotFoundException {
        City temporalCity = findCity(selection[0]);
        School temporalSchool;
        if(temporalCity == null){
            throw new ValueNotFoundException();
        } else{
            temporalSchool = temporalCity.findSchool(selection[1]);
            if(temporalSchool == null){
                throw new ValueNotFoundException();
            } else {
                return temporalSchool.schoolInformationBasedInCampus();
            }
        }
    }

    public String campusInformation(String[] selection) throws ValueNotFoundException {
        City temporalCity = findCity(selection[0]);
        School temporalSchool;
        Campus temporalCampus;
        if(temporalCity == null){
            throw new ValueNotFoundException();
        } else {
            temporalSchool = temporalCity.findSchool(selection[1]);
            if(temporalSchool == null){
                throw new ValueNotFoundException();
            } else {
                temporalCampus = temporalSchool.findCampus(selection[2]);
                if(temporalCampus == null){
                    throw new ValueNotFoundException();
                }
                return temporalCampus.coursesInformation();
            }
        }
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
