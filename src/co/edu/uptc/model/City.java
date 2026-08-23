package co.edu.uptc.model;

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

    public void addSchool(School school) {
        this.schools.add(school);
    }
}
