package co.edu.uptc.view.panels;

import javax.swing.*;
import java.awt.*;

public class LabeledComboPanel extends JPanel {

    public LabeledComboPanel(String label, JComboBox<String> combo) {
        super(new BorderLayout(6, 0));
        JLabel jLabel = new JLabel(label);
        jLabel.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
        add(jLabel, BorderLayout.WEST);
        add(combo, BorderLayout.CENTER);
    }
}