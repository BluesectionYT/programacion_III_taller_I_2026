package co.edu.uptc.model;

import co.edu.uptc.exceptions.ValueNotFoundException;
import co.edu.uptc.structures.DoubleList;

public class City {
    private final String[] COURSES_NAMES = new String[]{"Preescolar", "1ro", "2do", "3ro", "4to", "5to", "6to", "7mo", "8vo", "9no", "10mo", "Once"};

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

    public int[] allStudentsNumberByCourses(){
        int[] result = new int[this.schools.getFirst().allStudentsNumberByCourses().length];
        int[] temporalCourses = null;
        for(School school : schools){
            temporalCourses = school.allStudentsNumberByCourses();
            for(int i = 0; i < temporalCourses.length; i++){
                result[i] += temporalCourses[i];
            }
        }
        return result;
    }

    public  String cityInformationBasedInSchools(){
        return this.toString() + " " + schools.toString();
    }

    private String allCoursesInformation(int[] allCoursesStudentsNumber) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < allCoursesStudentsNumber.length; i++) {
            result.append(COURSES_NAMES[i]+" "+allCoursesStudentsNumber[i]+"\n");
        }
        return result.toString();
    }

    public  String cityInformationBasedInCourses(){
        return this.toString() + " " + allCoursesInformation(allStudentsNumberByCourses());
    }

    @Override
    public String toString() {
        return "Ciudad\n" +
                "Nombre " + name + "\n";
    }
}
