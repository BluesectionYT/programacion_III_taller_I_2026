package co.edu.uptc.presenter;

import co.edu.uptc.model.Campus;
import co.edu.uptc.model.City;
import co.edu.uptc.model.School;
import co.edu.uptc.model.State;
import co.edu.uptc.persistence.Persistence;
import co.edu.uptc.structures.DoubleList;
import co.edu.uptc.structures.DoubleNode;
import co.edu.uptc.structures.SimpleList;

public class Presenter {
    private State state;

    public Presenter() {
        Persistence persistence = new Persistence("data/Matrícula_Instituciones_Educativas_oficiales_y_no_oficiales_-_DEPARTAMENTO_DE_BOYACÁ_20260821.csv");
        this.state = persistence.readSavedData();
        System.out.println(state);
    }

    public SimpleList<City> getCities(){
        return state.getCities();
    }

    public DoubleList<School> getSchools(String cityName){
        return state.findCity(cityName).getSchools();
    }

    public SimpleList<Campus> getCampus(String cityName, String schoolName, String campusName){
        return state.findCity(cityName).findSchool(schoolName).getCampus();
    }

    public void addCity( City city){
        state.addCity(city);
    }

    public void addSchool(String cityName, School school){
        state.findCity(cityName).addSchool(school);
    }

    public void addCampus(String cityName, String schoolName, Campus campus){
        state.findCity(cityName).findSchool(schoolName).addCampus(campus);
    }


}
