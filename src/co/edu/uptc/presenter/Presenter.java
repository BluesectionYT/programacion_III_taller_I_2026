package co.edu.uptc.presenter;

import co.edu.uptc.model.State;
import co.edu.uptc.persistence.Persistence;

public class Presenter {
    private State state;

    public Presenter() {
        Persistence persistence = new Persistence("data/Matrícula_Instituciones_Educativas_oficiales_y_no_oficiales_-_DEPARTAMENTO_DE_BOYACÁ_20260821.csv");
        this.state = persistence.readSavedData();
        System.out.println(state);
    }


}
