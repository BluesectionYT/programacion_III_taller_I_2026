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

    public boolean addCampus(Campus campus) {
        boolean result = false;
        if(findCampus(campus.getName()) == null){
            this.campus.add(campus);
            result = true;
        }
        return result;
    }

    public Campus findCampus(String campusName){
        return campus.stream().filter(campus -> campus.getName().equals(campusName)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "Escuela" +
                "\nNombre " + name +
                "\nCodigo Dane " + daneCode +
                "\nSedes " + campus.toString() + '\n';
    }
}
