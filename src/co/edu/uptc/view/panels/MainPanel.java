package co.edu.uptc.view.panels;

import co.edu.uptc.exceptions.ValueNotFoundException;
import co.edu.uptc.model.Campus;
import co.edu.uptc.model.City;
import co.edu.uptc.model.School;
import co.edu.uptc.presenter.Presenter;
import co.edu.uptc.structures.DoubleList;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private final Presenter presenter;
    private final SelectionPanel selectionPanel;
    private final ResultsPanel resultsPanel;

    public MainPanel(Presenter presenter) {
        this.presenter = presenter;
        this.selectionPanel = new SelectionPanel();
        this.resultsPanel = new ResultsPanel();

        buildLayout();
        registerListeners();
        loadCities();
    }

    private void buildLayout() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        add(selectionPanel, BorderLayout.NORTH);
        add(resultsPanel, BorderLayout.CENTER);
    }

    private void registerListeners() {
        selectionPanel.addMunicipioComboListener(e -> onMunicipioSelected());
        selectionPanel.addIeComboListener(e -> onIeSelected());

        selectionPanel.addInfoMunicipioListener(e -> showMunicipioInfo());
        selectionPanel.addInfoIeListener(e -> showIeInfo());
        selectionPanel.addInfoSedeListener(e -> showSedeInfo());
    }

    public final void loadCities() {
        JComboBox<String> municipioCombo = selectionPanel.getMunicipioCombo();
        municipioCombo.removeAllItems();
        for (City city : presenter.getCities()) {
            municipioCombo.addItem(city.getName());
        }
    }

    private void onMunicipioSelected() {
        loadIe(getSelectedCity());
    }

    private void loadIe(String cityName) {
        JComboBox<String> ieCombo = selectionPanel.getIeCombo();
        JComboBox<String> sedeCombo = selectionPanel.getSedeCombo();

        ieCombo.removeAllItems();
        sedeCombo.removeAllItems();
        if (cityName != null) {
            try {
                DoubleList<School> schools = presenter.getSchools(cityName);
                for (int i = 0; i < schools.size(); i++) {
                    ieCombo.addItem(schools.get(i).getName());
                }
            } catch (ValueNotFoundException ex) {
            }
        }

        onIeSelected();
    }

    private void onIeSelected() {
        loadSedes(getSelectedCity(), getSelectedSchool());
    }

    private void loadSedes(String cityName, String schoolName) {
        JComboBox<String> sedeCombo = selectionPanel.getSedeCombo();
        sedeCombo.removeAllItems();
        if (cityName != null && schoolName != null) {
            try {
                for (Campus campus : presenter.getCampus(cityName, schoolName)) {
                    sedeCombo.addItem(campus.getName());
                }
            } catch (ValueNotFoundException ex) {

            }
        }
    }

    private void showMunicipioInfo() {
        String cityName = getSelectedCity();
        if (cityName == null) {
            warn("Seleccione un municipio");
            return;
        }
        try {
            resultsPanel.setMatriculaText(presenter.getCityInformationBySchools(cityName));
            resultsPanel.setMatriculaGradosText(presenter.getCityInformationByCourses(cityName));
        } catch (ValueNotFoundException ex) {
            warn("El municipio seleccionado no existe");
        } catch (RuntimeException ex) {
            warn("El municipio aun no tiene instituciones registradas");
        }
    }

    private void showIeInfo() {
        String cityName = getSelectedCity();
        String schoolName = getSelectedSchool();
        if (cityName == null || schoolName == null) {
            warn("Seleccione un municipio y una institucion educativa");
            return;
        }
        try {
            resultsPanel.setMatriculaText(presenter.getSchoolInformationByCampus(cityName, schoolName));
            resultsPanel.setMatriculaGradosText(presenter.getSchoolInformationByCourses(cityName, schoolName));
        } catch (ValueNotFoundException ex) {
            warn("La institucion educativa seleccionada no existe");
        } catch (RuntimeException ex) {
            warn("La institucion aun no tiene sedes registradas");
        }
    }

    private void showSedeInfo() {
        String cityName = getSelectedCity();
        String schoolName = getSelectedSchool();
        String campusName = getSelectedCampus();
        if (cityName == null || schoolName == null || campusName == null) {
            warn("Seleccione un municipio, una institucion educativa y una sede");
            return;
        }
        try {
            String header = findCampusHeader(cityName, schoolName, campusName);
            resultsPanel.setMatriculaText(header);
            resultsPanel.setMatriculaGradosText(presenter.getCampusInformation(cityName, schoolName, campusName));
        } catch (ValueNotFoundException ex) {
            warn("La sede seleccionada no existe");
        }
    }

    private String findCampusHeader(String cityName, String schoolName, String campusName) throws ValueNotFoundException {
        for (Campus campus : presenter.getCampus(cityName, schoolName)) {
            if (campus.getName().equals(campusName)) {
                return campus.toString();
            }
        }
        return "";
    }

    private void warn(String message) {
        JOptionPane.showMessageDialog(this, message, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public String getSelectedCity() {
        return (String) selectionPanel.getMunicipioCombo().getSelectedItem();
    }

    public String getSelectedSchool() {
        return (String) selectionPanel.getIeCombo().getSelectedItem();
    }

    public String getSelectedCampus() {
        return (String) selectionPanel.getSedeCombo().getSelectedItem();
    }

    public void refreshAfterAddCity(String newCityName) {
        loadCities();
        selectionPanel.getMunicipioCombo().setSelectedItem(newCityName);
    }

    public void refreshAfterAddSchool(String cityName, String newSchoolName) {
        loadIe(cityName);
        selectionPanel.getIeCombo().setSelectedItem(newSchoolName);
    }

    public void refreshAfterAddCampus(String cityName, String schoolName, String newCampusName) {
        loadSedes(cityName, schoolName);
        selectionPanel.getSedeCombo().setSelectedItem(newCampusName);
    }
}