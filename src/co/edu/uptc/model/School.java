package co.edu.uptc.model;

import co.edu.uptc.structures.SimpleList;

public class School {
    private final String[] COURSES_NAMES = new String[]{"Preescolar", "1ro", "2do", "3ro", "4to", "5to", "6to", "7mo", "8vo", "9no", "10mo", "Once"};

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

    public int allCampusStudentsNumber(){
        int result = 0;
        for (Campus campus : campus) {
            result += campus.studentsNumber();
        }
        return result;
    }

    public int[] allStudentsNumberByCourses(){
        int[] result = new int[this.campus.getFirst().getCourses().size()];
        Integer[] temporalCourses = null;
        for(Campus campus : campus){
            temporalCourses = campus.getCourses().toArray(Integer[]::new);
            for(int i = 0; i < temporalCourses.length; i++){
                result[i] += temporalCourses[i];
            }
        }
        return result;
    }

    public String schoolInformationBasedInCampus(){
        StringBuilder result = new StringBuilder();
        for (Campus campus : campus) {
            result.append(campus.toString()+"Estudiantes matriculados " + campus.studentsNumber()+"\n");
        }
        return result.toString();
    }

    public String schoolInformationBasesInCourses(){
        return this.toString() + allCoursesInformation(allStudentsNumberByCourses()) + "\n";
    }

    private String allCoursesInformation(int[] allCoursesStudentsNumber) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < allCoursesStudentsNumber.length; i++) {
            result.append(COURSES_NAMES[i]+" "+allCoursesStudentsNumber[i]+"\n");
        }
        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Campus campus : campus){
            if(campus.getName() != null){
                result.append("Nombre " + this.name +
                        "\nCodigo Dane " + this.daneCode +
                        "\nSedes " + this.campus.size() +
                        "\nEstudiantes Matriculados " + this.allCampusStudentsNumber() + "\n");
            }
        }
        return result.toString();
    }
}
