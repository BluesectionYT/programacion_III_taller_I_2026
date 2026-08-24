package co.edu.uptc.model;

import co.edu.uptc.exceptions.ValueNotFoundException;
import co.edu.uptc.structures.DoubleList;

public class City {
    private String name;
    private DoubleList<School> schools;

    public City(String name) {
        this.name = name;
        this.schools = new DoubleList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DoubleList<School> getSchools() {
        return schools;
    }

    public void setSchools(DoubleList<School> schools) {
        this.schools = schools;
    }

    public boolean addSchool(School school) {
        boolean result = false;
        if(findSchool(school.getName()) == null){
            this.schools.add(school);
            result = true;
        }
        return result;
    }

    public School findSchool(String schoolName){
        return schools.stream().filter(school -> school.getName().equals(schoolName)).findFirst().orElse(null);
    }

    public boolean addCampus(String schoolName, Campus campusToAdd) throws ValueNotFoundException {
        School school = findSchool(schoolName);
        if(school == null){
            throw new ValueNotFoundException();
        }
        return school.addCampus(campusToAdd);
    }

    @Override
    public String toString() {
        return "Ciudad\n" +
                "Nombre " + name +
                "\nEscuelas" + schools.toString() + '\n';
    }
}
