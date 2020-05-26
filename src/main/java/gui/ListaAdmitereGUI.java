package gui;

import model.Candidat;
import service.AdmitereService;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class ListaAdmitereGUI extends JFrame{
    private JPanel panel;
    private JList list1;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel labelBuget;
    private JLabel labelTaxa;
    private JList list2;
    private JLabel labelRespinsi;
    private JList list3;

    public ListaAdmitereGUI(String numeFacultate) {
        add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        list1.setModel(getLista(numeFacultate, "Buget"));
        list2.setModel(getLista(numeFacultate, "Taxa"));
        list3.setModel(getLista(numeFacultate, "Respinsi"));
        pack();
        setVisible(true);
    }

    private DefaultListModel getLista(String numeFacultate, String listaCautata){
        DefaultListModel lista = new DefaultListModel();
        List<Candidat> listaCompleta = new AdmitereService().getListaSpecifica(numeFacultate, listaCautata);
        for(Candidat candidat : listaCompleta){
            lista.addElement(candidat.getNume() + " " + candidat.getPrenume() +
                    ": " + candidat.getMedie());
        }
        return lista;
    }
}
