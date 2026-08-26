package co.edu.uptc.view.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SelectionPanel extends JPanel {

    private final JComboBox<String> municipioCombo;
    private final JComboBox<String> ieCombo;
    private final JComboBox<String> sedeCombo;

    private final JButton infoMunicipioButton;
    private final JButton infoIeButton;
    private final JButton infoSedeButton;

    public SelectionPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        municipioCombo = new JComboBox<>();
        ieCombo = new JComboBox<>();
        sedeCombo = new JComboBox<>();

        JPanel combosRow = new JPanel(new GridLayout(1, 3, 10, 0));
        combosRow.add(new LabeledComboPanel("Municipio", municipioCombo));
        combosRow.add(new LabeledComboPanel("IE", ieCombo));
        combosRow.add(new LabeledComboPanel("Sede", sedeCombo));

        infoMunicipioButton = new JButton("Informaciòn. Municipio");
        infoIeButton = new JButton("Informaciòn. IE");
        infoSedeButton = new JButton("Informaciòn. Sede");

        JPanel buttonsRow = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonsRow.add(infoMunicipioButton);
        buttonsRow.add(infoIeButton);
        buttonsRow.add(infoSedeButton);

        add(combosRow);
        add(Box.createVerticalStrut(10));
        add(buttonsRow);
    }


    public JComboBox<String> getMunicipioCombo() {
        return municipioCombo;
    }

    public JComboBox<String> getIeCombo() {
        return ieCombo;
    }

    public JComboBox<String> getSedeCombo() {
        return sedeCombo;
    }


    public void addMunicipioComboListener(ActionListener listener) {
        municipioCombo.addActionListener(listener);
    }

    public void addIeComboListener(ActionListener listener) {
        ieCombo.addActionListener(listener);
    }

    public void addInfoMunicipioListener(ActionListener listener) {
        infoMunicipioButton.addActionListener(listener);
    }

    public void addInfoIeListener(ActionListener listener) {
        infoIeButton.addActionListener(listener);
    }

    public void addInfoSedeListener(ActionListener listener) {
        infoSedeButton.addActionListener(listener);
    }
}
