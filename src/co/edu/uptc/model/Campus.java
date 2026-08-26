package co.edu.uptc.model;

import co.edu.uptc.structures.SimpleList;

public class Campus {
    private final String[] COURSES_NAMES = new String[]{"Preescolar", "1ro", "2do", "3ro", "4to", "5to", "6to", "7mo", "8vo", "9no", "10mo", "Once"};

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

    public short studentsNumber(){
        short result = 0;
        for (Integer course : courses){
            result += course;
        }
        return result;
    }

    public String coursesInformation(){
        StringBuilder result = new StringBuilder();
        int i = 0;
        result.append(this);
        for (Integer course : courses){
            result.append(COURSES_NAMES[i] + ": " + course + "\n");
            i++;
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return name + "\nCodigo DANE " + daneCode +"\nZona " + zone + "    Sector " + sector + "\n";
    }
}
