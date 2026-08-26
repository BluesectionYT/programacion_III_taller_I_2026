package co.edu.uptc.view.frames;

import co.edu.uptc.presenter.Presenter;
import co.edu.uptc.view.dialogs.AddCampusDialog;
import co.edu.uptc.view.dialogs.AddCityDialog;
import co.edu.uptc.view.dialogs.AddSchoolDialog;
import co.edu.uptc.view.panels.MainPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final Presenter presenter;
    private final MainPanel mainPanel;

    public MainFrame(Presenter presenter) {
        this.presenter = presenter;
        this.mainPanel = new MainPanel(presenter);
        buildFrame();
    }

    private void buildFrame() {
        setTitle("Matricula Instituciones Educativas - Boyaca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(buildMenuBar());
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(920, 560));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        this.setVisible(true);
    }

    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu registrarMenu = new JMenu("Registrar");

        JMenuItem newCity = new JMenuItem("Nuevo municipio");
        newCity.addActionListener(e -> openAddCityDialog());

        JMenuItem newSchool = new JMenuItem("Nueva institucion educativa");
        newSchool.addActionListener(e -> openAddSchoolDialog());

        JMenuItem newCampus = new JMenuItem("Nueva sede");
        newCampus.addActionListener(e -> openAddCampusDialog());

        registrarMenu.add(newCity);
        registrarMenu.add(newSchool);
        registrarMenu.add(newCampus);
        menuBar.add(registrarMenu);
        return menuBar;
    }

    private void openAddCityDialog() {
        AddCityDialog dialog = new AddCityDialog(this, presenter, mainPanel);
        dialog.setVisible(true);
    }

    private void openAddSchoolDialog() {
        String selectedCity = mainPanel.getSelectedCity();
        if (selectedCity == null) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione primero un municipio",
                    "Municipio requerido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        AddSchoolDialog dialog = new AddSchoolDialog(this, presenter, mainPanel, selectedCity);
        dialog.setVisible(true);
    }

    private void openAddCampusDialog() {
        String selectedCity = mainPanel.getSelectedCity();
        String selectedSchool = mainPanel.getSelectedSchool();
        if (selectedCity == null || selectedSchool == null) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione primero un municipio y una institucion educativa",
                    "Datos requeridos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        AddCampusDialog dialog = new AddCampusDialog(this, presenter, mainPanel, selectedCity, selectedSchool);
        dialog.setVisible(true);
    }
}
