package co.edu.uptc.model;

import co.edu.uptc.structures.SimpleList;

public class Campus {
    private String name;
    private String daneCode;
    private String sector;
    private String zone;
    private SimpleList<Integer> courses;

    public Campus(String name) {
        this.name = name;
        this.daneCode = "";
        this.sector = "";
        this.zone = "";
        this.courses = new SimpleList<>();
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

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public SimpleList<Integer> getCourses() {
        return courses;
    }

    public void setCourses(SimpleList<Integer> courses) {
        this.courses = courses;
    }
}
