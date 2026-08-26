package co.edu.uptc.view.dialogs;

import co.edu.uptc.exceptions.ValueNotFoundException;
import co.edu.uptc.presenter.Presenter;
import co.edu.uptc.view.panels.MainPanel;

import javax.swing.*;
import java.awt.*;

public class AddSchoolDialog extends JDialog {

    private final Presenter presenter;
    private final MainPanel mainPanel;
    private final String cityName;

    private JTextField nameField;
    private JTextField daneCodeField;

    public AddSchoolDialog(Frame owner, Presenter presenter, MainPanel mainPanel, String cityName) {
        super(owner, "Nueva institucion educativa", true);
        this.presenter = presenter;
        this.mainPanel = mainPanel;
        this.cityName = cityName;
        buildLayout();
    }

    private void buildLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(new JLabel("Municipio: " + cityName), c);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("Nombre de la institucion:"), c);
        nameField = new JTextField(18);
        c.gridx = 1;
        add(nameField, c);

        c.gridx = 0;
        c.gridy = 2;
        add(new JLabel("Codigo DANE:"), c);
        daneCodeField = new JTextField(18);
        c.gridx = 1;
        add(daneCodeField, c);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> save());
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(cancelButton);
        buttonsPanel.add(saveButton);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        add(buttonsPanel, c);

        getRootPane().setDefaultButton(saveButton);
        pack();
        setResizable(false);
        setLocationRelativeTo(getOwner());
    }

    private void save() {
        String name = nameField.getText().trim();
        String daneCode = daneCodeField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre valido", "Dato invalido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            boolean added = presenter.addSchool(cityName, name, daneCode);
            if (!added) {
                JOptionPane.showMessageDialog(this, "Ya existe una institucion con ese nombre en el municipio", "Institucion duplicada", JOptionPane.WARNING_MESSAGE);
                return;
            }
            mainPanel.refreshAfterAddSchool(cityName, name);
            dispose();
        } catch (ValueNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "El municipio seleccionado no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
