package co.edu.uptc.view.panels;

import javax.swing.*;
import java.awt.*;

public class ResultsPanel extends JPanel {

    private final JTextArea matriculaArea;
    private final JTextArea matriculaGradosArea;

    public ResultsPanel() {
        super(new GridLayout(1, 2, 10, 0));

        matriculaArea = createTextArea();
        matriculaGradosArea = createTextArea();

        add(titledScroll("Matricula", matriculaArea));
        add(titledScroll("Matricula por grados", matriculaGradosArea));
    }

    private JTextArea createTextArea() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        return area;
    }

    private JScrollPane titledScroll(String title, JTextArea area) {
        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setBorder(BorderFactory.createTitledBorder(title));
        return scrollPane;
    }

    public void setMatriculaText(String text) {
        matriculaArea.setText(text);
    }

    public void setMatriculaGradosText(String text) {
        matriculaGradosArea.setText(text);
    }
}
