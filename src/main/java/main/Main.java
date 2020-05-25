package main;

import model.Candidat;
import model.Profesor;
import repositories.*;
import service.LoginCandidatService;
import service.LoginProfesorCandidat;

public class Main {

    public static void main(String[] args) {
        LoginCandidatService loginCandidatService = new LoginCandidatService();
        System.out.println(loginCandidatService.login("email1", "parola", "Info"));
    }
}
