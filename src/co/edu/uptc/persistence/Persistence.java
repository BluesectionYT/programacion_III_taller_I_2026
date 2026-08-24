package co.edu.uptc.persistence;

import co.edu.uptc.exceptions.DuplicateException;
import co.edu.uptc.model.Campus;
import co.edu.uptc.model.City;
import co.edu.uptc.model.School;
import co.edu.uptc.model.State;
import co.edu.uptc.structures.SimpleList;

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
                String[] data = chain.replace("\"", "").trim().split(",");
                if(data[0].replace("\"", "").trim().equalsIgnoreCase("2.022")){
                    createCity(data, result);
                }
            }
        } catch (IOException e) {
            result = new State();
        }
        return result;
    }

    private void createCity(String[] data, State state) {
        City city = state.getCities().stream().filter(c -> c.getName().equalsIgnoreCase(data[1])).findFirst().orElse(null);
        if (city == null){
            city = new City(data[1]);
            state.addCity(city);
        }
        createSchool(data, city);
    }

    private void createSchool(String[] data, City city) {
        School school = city.getSchools().stream().filter(s-> s.getName().equalsIgnoreCase(data[3])).findFirst().orElse(null);
        if (school == null){
            school = new School(data[3], data[2]);
            city.addSchool(school);
        }
        createCampus(data, school);
    }

    private void createCampus(String[] data, School school) {
        Campus campus = school.getCampus().stream().filter(c -> c.getName().equalsIgnoreCase(data[5])).findFirst().orElse(null);
        if (campus == null){
            campus = new Campus(data[5]);
            campus.setDaneCode(data[4]);
            campus.setSector(data[7]);
            campus.setZone(data[6]);
            campus.setCourses(createCourses(data));
            school.addCampus(campus);
        }
    }

    private SimpleList<Integer> createCourses(String[] data) {
        SimpleList<Integer> result = new SimpleList<>();
        for (int i = 8; i < 20; i++) {
            if (!data[i].equalsIgnoreCase("")) {
                result.add(Integer.parseInt(data[i]));
            } else{
                result.add(0);
            }
        }
        return result;
    }
}
