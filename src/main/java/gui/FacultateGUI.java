package gui;

import model.Facultate;
import repositories.FacultateRepository;
import service.AdmitereService;
import service.FacultateService;

import javax.swing.*;

public class FacultateGUI extends JFrame{
    private JPanel panel;
    private JPanel panelFacultate;
    private JLabel numeFacultate;
    private JLabel locuriBuget;
    private JLabel locuriTaxa;
    private JPanel panelLocuriNumere;
    private JPanel panelLocuriText;
    private JLabel bugetText;
    private JLabel taxaText;
    private JPanel panelSchimbare;
    private JTextField bugetSchimbare;
    private JTextField taxaSchimbare;
    private JButton inapoiButton;
    private JButton schimbaNumarulLocurilorButton;
    private final FacultateService facultateService = new FacultateService();

    public FacultateGUI(String nume){
        add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        adaugaInformatii(nume);
        pack();
        inapoiButton.addActionListener(event -> inapoi(nume));
        schimbaNumarulLocurilorButton.addActionListener(event -> schimbaLocuri(nume));
        setVisible(true);
    }

    private void schimbaLocuri(String nume) {
        facultateService.changeFacultate(nume,
                Integer.parseInt(bugetSchimbare.getText()), Integer.parseInt(taxaSchimbare.getText()));
        new AdmitereService().printListe(nume);
        JOptionPane.showMessageDialog(null, "Nr. locurilor a fost schimbat");
        new FacultateGUI(nume);
        dispose();
    }

    private void inapoi(String nume) {
        new ProfesorGUI(nume);
        dispose();
    }

    private void adaugaInformatii(String nume) {
        Facultate facultate = facultateService.findFacultate(nume);
        numeFacultate.setText(nume);
        locuriBuget.setText(String.valueOf(facultate.getLocuriBuget()));
        locuriTaxa.setText(String.valueOf(facultate.getLocuriTaxa()));
    }
}
