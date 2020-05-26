package gui;

import service.CandidatService;
import service.ProfesorService;

import javax.swing.*;

public class LoginGUI extends JFrame{
    private JButton button1;
    private JTextField email;
    private JLabel jLabel1;
    private JPasswordField password;
    private JLabel jLabel2;
    private JCheckBox profesorCheckBox;
    private JCheckBox candidatCheckBox;
    private JPanel panelEmail;
    private JPanel panelParola;
    private JPanel panel;
    private JTextField facultate;
    private JPanel panel3;
    private JButton inregistrareCandidatButton;

    public LoginGUI(){
        add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
        button1.addActionListener(event -> login());
        inregistrareCandidatButton.addActionListener(event -> addCandidat());
    }

    private void addCandidat() {
        new InregistrareGUI();
        dispose();
    }

    private void login(){
        if(profesorCheckBox.isSelected() == candidatCheckBox.isSelected()){
            if(profesorCheckBox.isSelected()){
                JOptionPane.showMessageDialog(null, "Doar o optiune trebuie selectata.");
            }
            else {
                JOptionPane.showMessageDialog(null, "Trebuie selectata o optiune.");
            }
        }
        else{
            if(candidatCheckBox.isSelected()){
                CandidatService candidatService = new CandidatService();
                if(candidatService.login(email.getText(),
                        password.getText(), facultate.getText()) != null){
                    JOptionPane.showMessageDialog(null, "Logare cu succes");
                    new ListaAdmitereGUI(facultate.getText());
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Email/Parola gresita");
                }
            }
            else{
                ProfesorService profesorService = new ProfesorService();
                if(profesorService.login(email.getText(),
                        password.getText(), facultate.getText()) != null){
                    JOptionPane.showMessageDialog(null, "Logare cu succes");
                    new ProfesorGUI(facultate.getText());
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Email/Parola gresita");
                }
            }
        }
    }
}
