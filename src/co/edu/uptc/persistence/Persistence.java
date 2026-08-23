package co.edu.uptc.persistence;

import co.edu.uptc.model.State;
import co.edu.uptc.structures.SimpleList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Persistence {
    private File file;

    public Persistence(String path) {
        this.file = new File(path);
    }

    //En proceso
    public State readSavedData(){
        State result = new State();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String chain;
            while ((chain = bufferedReader.readLine()) != null) {
                String[] data = chain.split(",");
            }
        } catch (IOException e) {
            result = new State();
        }
        return result;
    }
}
