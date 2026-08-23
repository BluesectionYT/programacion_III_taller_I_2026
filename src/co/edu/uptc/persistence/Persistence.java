package co.edu.uptc.persistence;

import co.edu.uptc.model.Campus;
import co.edu.uptc.model.City;
import co.edu.uptc.model.School;
import co.edu.uptc.model.State;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Persistence {
    private File file;

    public Persistence(String path) {
        this.file = new File(path);
    }

    public State readSavedData(){
        State result = new State();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String chain;
            while ((chain = bufferedReader.readLine()) != null) {
                String[] data = chain.split(",");
                createCity(data, result);
            }
        } catch (IOException e) {
            result = new State();
        }
        return result;
    }

    private void createCity(String[] data, State state){
        City city = state.getCities().stream().filter(c -> c.getName().equalsIgnoreCase(data[1])).findFirst().orElse(null);
        if (city == null){
            state.addCity(new City(data[1]));
        } else{
            createSchool(data, city);
        }
    }

    private void createSchool(String[] data, City city) {
        School school = city.getSchools().stream().filter(s-> s.getName().equalsIgnoreCase(data[3])).findFirst().orElse(null);
        if (school == null){
            city.addSchool(new School(data[3], data[2]));
        } else{
            createCampus(data, school);
        }
    }

    private void createCampus(String[] data, School school) {
        Campus campus = school.getCampus().stream().filter(c -> c.getName().equalsIgnoreCase(data[5])).findFirst().orElse(null);
        if (campus == null){
            school.addCampus(new Campus(data[5]));
        }
    }
}
