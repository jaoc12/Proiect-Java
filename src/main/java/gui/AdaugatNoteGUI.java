package gui;

import model.Candidat;
import service.AdmitereService;
import service.CandidatService;

import java.util.List;
import javax.swing.*;

public class AdaugatNoteGUI extends JFrame{
    private JPanel panel;
    private JList list1;
    private JLabel labelCandidati;
    private JPanel panel1;
    private JPanel panel2;
    private JTextField email;
    private JPanel panelEmail;
    private JPanel panelNota1;
    private JTextField nota1;
    private JPanel panelNota2;
    private JTextField nota2;
    private JButton adaugaNotaButton;
    private JButton inapoiButton;

    public AdaugatNoteGUI(String numeFacultate){
        add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        list1.setModel(getLista(numeFacultate));
        pack();
        adaugaNotaButton.addActionListener(event -> adaugaNota(numeFacultate));
        inapoiButton.addActionListener(event -> inapoi(numeFacultate));
        setVisible(true);
    }

    private void inapoi(String numeFacultate) {
        new ProfesorGUI(numeFacultate);
        dispose();
    }

    private void adaugaNota(String numeFacultate) {
        CandidatService candidatService = new CandidatService();
        candidatService.changeNote(email.getText(), numeFacultate,
                Double.parseDouble(nota1.getText()), Double.parseDouble(nota2.getText()));
        new AdmitereService().printListe(numeFacultate);
        JOptionPane.showMessageDialog(null, "Note schimbate");
        new AdaugatNoteGUI(numeFacultate);
        dispose();
    }

    private DefaultListModel getLista(String numeFacultate){
        DefaultListModel lista = new DefaultListModel();
        List<Candidat> listaCandidati = new AdmitereService().getLista(numeFacultate);
        for(Candidat candidat : listaCandidati){
            lista.addElement(candidat.getNume() + " " + candidat.getEmail() + ": " +
                    candidat.getNota1() + " - " + candidat.getNota2());
        }
        return lista;
    }
}
