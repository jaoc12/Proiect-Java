package gui;

import service.LoginCandidatService;

import javax.swing.*;

public class LoginGUI extends JFrame{
    private JButton button1;
    private JTextField textField1;
    private JLabel email;
    private JPasswordField passwordField1;
    private JLabel parola;
    private JCheckBox profesorCheckBox;
    private JCheckBox candidatCheckBox;
    private JPanel panelEmail;
    private JPanel panelParola;
    private JPanel panel;
    private JTextField textField2;
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
        InregistrareGUI inregistrareGUI = new InregistrareGUI();
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
                LoginCandidatService loginCandidatService = new LoginCandidatService();
                if(loginCandidatService.login(textField1.getText(), passwordField1.getText(), textField2.getText()) != null){
                    JOptionPane.showMessageDialog(null, "Logare cu succes");
                    ListaAdmitereGUI listaAdmitereGUI = new ListaAdmitereGUI(textField2.getText());
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Email/parola gresita");
                }
            }
        }
    }
}
