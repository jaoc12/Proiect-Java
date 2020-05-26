package gui;

import model.Facultate;

import javax.swing.*;
import java.awt.*;

public class ProfesorGUI extends JFrame{
    private JPanel panel;
    private JButton adaugaNoteButton;
    private JButton facultateButton;
    private JButton examenButton;

    public ProfesorGUI(String numeFacultate){
        add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        adaugaNoteButton.addActionListener(event -> adaugaNote(numeFacultate));
        facultateButton.addActionListener(event -> switchToFacultate(numeFacultate));
        examenButton.addActionListener(event -> switchToExament(numeFacultate));
        setVisible(true);
    }

    private void switchToExament(String numeFacultate) {
        new ExamenGUI(numeFacultate);
        dispose();
    }

    private void switchToFacultate(String numeFacultate) {
        new FacultateGUI(numeFacultate);
        dispose();
    }

    private void adaugaNote(String numeFacultate) {
        new AdaugatNoteGUI(numeFacultate);
        dispose();
    }
}
