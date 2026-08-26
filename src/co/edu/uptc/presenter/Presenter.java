package co.edu.uptc.presenter;

import co.edu.uptc.exceptions.ValueNotFoundException;
import co.edu.uptc.model.Campus;
import co.edu.uptc.model.City;
import co.edu.uptc.model.School;
import co.edu.uptc.model.State;
import co.edu.uptc.persistence.Persistence;
import co.edu.uptc.structures.DoubleList;
import co.edu.uptc.structures.SimpleList;
import co.edu.uptc.view.frames.MainFrame;

public class Presenter {
    private State state;
    private MainFrame mainFrame;

    public Presenter() {
        Persistence persistence = new Persistence("data/Matrícula_Instituciones_Educativas_oficiales_y_no_oficiales_-_DEPARTAMENTO_DE_BOYACÁ_20260821.csv");
        this.state = persistence.readSavedData();
        mainFrame = new MainFrame(this);
    }

    public SimpleList<City> getCities() {
        return state.getCities();
    }

    public DoubleList<School> getSchools(String cityName) throws ValueNotFoundException {
        City city = state.findCity(cityName);
        if (city == null) {
            throw new ValueNotFoundException();
        }
        return city.getSchools();
    }

    public SimpleList<Campus> getCampus(String cityName, String schoolName) throws ValueNotFoundException {
        City city = state.findCity(cityName);
        if (city == null) {
            throw new ValueNotFoundException();
        }
        School school = city.findSchool(schoolName);
        if (school == null) {
            throw new ValueNotFoundException();
        }
        return school.getCampus();
    }


    public boolean addCity(String cityName) {
        return state.addCity(new City(cityName));
    }

    public boolean addSchool(String cityName, String schoolName, String daneCode) throws ValueNotFoundException {
        return state.addSchool(cityName, new School(schoolName, daneCode));
    }

    public boolean addCampus(String cityName, String schoolName, String campusName, SimpleList<Integer> courses) throws ValueNotFoundException {
        Campus campus = new Campus(campusName);
        campus.setCourses(courses);
        return state.addCampus(cityName, schoolName, campus);
    }


    public String getCityInformationBySchools(String cityName) throws ValueNotFoundException {
        return state.cityInformationBasedInSchools(cityName);
    }

    public String getCityInformationByCourses(String cityName) throws ValueNotFoundException {
        return state.cityInformationBasedInCourses(cityName);
    }

    public String getSchoolInformationByCampus(String cityName, String schoolName) throws ValueNotFoundException {
        return state.schoolInformationByCampus(new String[]{cityName, schoolName});
    }

    public String getSchoolInformationByCourses(String cityName, String schoolName) throws ValueNotFoundException {
        return state.schoolInformationByCourses(new String[]{cityName, schoolName});
    }

    public String getCampusInformation(String cityName, String schoolName, String campusName) throws ValueNotFoundException {
        return state.campusInformation(new String[]{cityName, schoolName, campusName});
    }
}
