package co.edu.uptc.model;

import co.edu.uptc.structures.SimpleList;

public class School {
    private String name;
    private String daneCode;
    private SimpleList<Campus> campus;

    public School(String name, String daneCode) {
        this.name = name;
        this.daneCode = daneCode;
        this.campus = new SimpleList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDaneCode() {
        return daneCode;
    }

    public void setDaneCode(String daneCode) {
        this.daneCode = daneCode;
    }

    public SimpleList<Campus> getCampus() {
        return campus;
    }

    public void setCampus(SimpleList<Campus> campus) {
        this.campus = campus;
    }

    public void addCampus(Campus campus) {
        this.campus.add(campus);
    }
}
