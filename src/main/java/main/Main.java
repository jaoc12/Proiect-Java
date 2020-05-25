package main;

import model.Candidat;
import model.Examen;
import model.Facultate;
import model.Profesor;
import repositories.*;
import service.LoginCandidatService;
import service.LoginProfesorCandidat;

public class Main {

    public static void main(String[] args) {
        FacultateRepository facultateRepository = FacultateRepository.build(FacultateRepository.Type.DB);
        facultateRepository.changeFacultate("Info", 10, 5, 0.5, 0.5);
        facultateRepository.removeFacultate("Drept");
        System.out.println(facultateRepository.findFacultate("Info").getNume());
        var lista = facultateRepository.loadFacultati();
        for(Facultate e : lista){
            System.out.println(e.getNume() + " " + e.getLocuriBuget());
        }
    }
}
