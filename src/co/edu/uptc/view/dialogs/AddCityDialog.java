package co.edu.uptc.view.dialogs;

import co.edu.uptc.presenter.Presenter;
import co.edu.uptc.view.panels.MainPanel;

import javax.swing.*;
import java.awt.*;


public class AddCityDialog extends JDialog {

    private final Presenter presenter;
    private final MainPanel mainPanel;
    private JTextField nameField;

    public AddCityDialog(Frame owner, Presenter presenter, MainPanel mainPanel) {
        super(owner, "Nuevo municipio", true);
        this.presenter = presenter;
        this.mainPanel = mainPanel;
        buildLayout();
    }

    private void buildLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("Nombre del municipio:"), c);

        nameField = new JTextField(18);
        c.gridx = 1;
        add(nameField, c);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> save());
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(cancelButton);
        buttonsPanel.add(saveButton);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        add(buttonsPanel, c);

        getRootPane().setDefaultButton(saveButton);
        pack();
        setResizable(false);
        setLocationRelativeTo(getOwner());
    }

    private void save() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre valido", "Dato invalido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        boolean added = presenter.addCity(name);
        if (!added) {
            JOptionPane.showMessageDialog(this, "Ya existe un municipio con ese nombre", "Municipio duplicado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        mainPanel.refreshAfterAddCity(name);
        dispose();
    }
}
