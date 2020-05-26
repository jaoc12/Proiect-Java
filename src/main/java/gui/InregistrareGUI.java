package gui;

import repositories.CandidatRepository;
import service.InregistrareService;

import javax.swing.*;
import java.awt.*;

public class InregistrareGUI extends JFrame{
    private JPanel panel;
    private JPanel panel1;
    private JLabel nume;
    private JTextField textField1;
    private JPanel panel2;
    private JLabel prenume;
    private JTextField textField2;
    private JPanel panel3;
    private JLabel password;
    private JPasswordField passwordField1;
    private JLabel email;
    private JLabel facultate;
    private JTextField textField3;
    private JTextField textField4;
    private JButton inregistrareButton;
    private JButton inapoiButton;

    public InregistrareGUI(){
        add(panel);
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        inregistrareButton.addActionListener(event -> inregistrare());
        inapoiButton.addActionListener(event -> inapoi());
    }

    private void inregistrare() {
        InregistrareService inregistrareService = new InregistrareService();
        inregistrareService.addCandidat(textField1.getText(), textField2.getText(), passwordField1.getText(),
                textField3.getText(), textField4.getText());
        JOptionPane.showMessageDialog(null, "Candidat intregistrat");
        new LoginGUI();
        dispose();
    }

    private void inapoi(){
        new LoginGUI();
        dispose();
    }
}
