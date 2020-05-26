package gui;

import model.Examen;
import service.AdmitereService;
import service.ExamenService;

import javax.swing.*;
import java.awt.*;

public class ExamenGUI extends JFrame{
    private JPanel panel;
    private JPanel panelExamen;
    private JLabel numeFacultate;
    private JPanel panelPonderiNumere;
    private JLabel pondere2;
    private JLabel pondere1;
    private JPanel panelPonderiText;
    private JLabel labelPondere1;
    private JLabel labelPondere2;
    private JPanel panelSchimbare;
    private JTextField pondere2Schimbare;
    private JTextField pondere1Schimbare;
    private JButton executaSchimbariButton;
    private JButton inapoiButton;
    private final ExamenService examenService = new ExamenService();

    public ExamenGUI(String nume){
        add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        adaugaInformatii(nume);
        inapoiButton.addActionListener(event -> inapoi(nume));
        executaSchimbariButton.addActionListener(event -> schimbaPonderi(nume));
        pack();
        setVisible(true);
    }

    private void schimbaPonderi(String nume) {
        examenService.changeExamen(nume, Double.parseDouble(pondere1Schimbare.getText()),
                Double.parseDouble(pondere2Schimbare.getText()));
        new AdmitereService().printListe(nume);
        JOptionPane.showMessageDialog(null, "Ponderile au fost schimbate");
        new FacultateGUI(nume);
        dispose();
    }

    private void inapoi(String nume) {
        new ProfesorGUI(nume);
        dispose();
    }

    private void adaugaInformatii(String nume) {
        Examen examen = examenService.findExamen(nume);
        numeFacultate.setText(nume);
        pondere1.setText(String.valueOf(examen.getPondere1()));
        pondere2.setText(String.valueOf(examen.getPondere2()));
    }
}
