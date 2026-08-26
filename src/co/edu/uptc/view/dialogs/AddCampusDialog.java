package co.edu.uptc.view.dialogs;

import co.edu.uptc.exceptions.ValueNotFoundException;
import co.edu.uptc.presenter.Presenter;
import co.edu.uptc.structures.SimpleList;
import co.edu.uptc.view.panels.MainPanel;

import javax.swing.*;
import java.awt.*;

public class AddCampusDialog extends JDialog {

    private final Presenter presenter;
    private final MainPanel mainPanel;
    private final String cityName;
    private final String schoolName;

    private JTextField nameField;
    private JSpinner kidsSpinner;
    private JList<Integer> coursesJList;

    private SimpleList<Integer> coursesList;

    public AddCampusDialog(Frame owner, Presenter presenter, MainPanel mainPanel, String cityName, String schoolName) {
        super(owner, "Nueva sede", true);
        this.presenter = presenter;
        this.mainPanel = mainPanel;
        this.cityName = cityName;
        this.schoolName = schoolName;
        this.coursesList = new SimpleList<>();
        buildLayout();
    }

    private void buildLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(new JLabel("Municipio: " + cityName), c);

        c.gridy = 1;
        add(new JLabel("Institucion: " + schoolName), c);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        add(new JLabel("Nombre de la sede:"), c);
        nameField = new JTextField(15);
        c.gridx = 1;
        add(nameField, c);

        c.gridx = 0;
        c.gridy = 3;
        add(new JLabel("Niños en nuevo curso:"), c);

        JPanel addCoursePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        kidsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JButton addButton = new JButton("+ Agregar Curso");
        addButton.addActionListener(e -> addCourseToList());

        addCoursePanel.add(kidsSpinner);
        addCoursePanel.add(addButton);
        c.gridx = 1;
        add(addCoursePanel, c);

        c.gridx = 0;
        c.gridy = 4;
        add(new JLabel("Cursos agregados:"), c);

        coursesJList = new JList<>();
        coursesJList.setVisibleRowCount(4);
        JScrollPane scrollPane = new JScrollPane(coursesJList);
        scrollPane.setPreferredSize(new Dimension(150, 80));

        JButton removeButton = new JButton("Quitar seleccionado");
        removeButton.addActionListener(e -> removeSelectedCourse());

        JPanel listPanel = new JPanel(new BorderLayout(5, 5));
        listPanel.add(scrollPane, BorderLayout.CENTER);
        listPanel.add(removeButton, BorderLayout.SOUTH);

        c.gridx = 1;
        add(listPanel, c);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> save());
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(cancelButton);
        buttonsPanel.add(saveButton);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        add(buttonsPanel, c);

        getRootPane().setDefaultButton(saveButton);
        pack();
        setResizable(false);
        setLocationRelativeTo(getOwner());
    }

    private void addCourseToList() {
        int kidsCount = (int) kidsSpinner.getValue();
        coursesList.add(kidsCount);
        updateJList();
    }

    private void removeSelectedCourse() {
        int selectedIndex = coursesJList.getSelectedIndex();
        if (selectedIndex != -1) {
            coursesList.remove(selectedIndex);
            updateJList();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un curso de la lista para quitarlo", "Atencion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateJList() {
        Integer[] array = coursesList.toArray(new Integer[0]);
        if(array.length <= 12){
            coursesJList.setListData(array);
        } else{
            JOptionPane.showMessageDialog(this, "El numero de cursos debe ser menor o igual a 12", "Error",JOptionPane.ERROR_MESSAGE);
        }
        coursesJList.setListData(array);
    }

    private void save() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre valido", "Dato invalido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            boolean added = presenter.addCampus(cityName, schoolName, name, coursesList);
            if (!added) {
                JOptionPane.showMessageDialog(this, "Ya existe una sede con ese nombre en la institucion", "Sede duplicada", JOptionPane.WARNING_MESSAGE);
                return;
            }
            mainPanel.refreshAfterAddCampus(cityName, schoolName, name);
            dispose();
        } catch (ValueNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "El municipio o la institucion seleccionada no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}