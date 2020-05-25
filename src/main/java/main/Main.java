package main;

import model.Candidat;
import model.Examen;
import model.Profesor;
import repositories.*;
import service.LoginCandidatService;
import service.LoginProfesorCandidat;

public class Main {

    public static void main(String[] args) {
        ExamenRepository examenRepository = ExamenRepository.build(ExamenRepository.Type.DB);
        System.out.println(examenRepository.findExamen("Info").getNumeFacultate());
        var lista = examenRepository.loadExamene();
        for(Examen e : lista){
            System.out.println(e.getNumeFacultate());
        }
    }
}
