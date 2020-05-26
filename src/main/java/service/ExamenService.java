package service;

import model.Examen;
import model.Facultate;
import repositories.ExamenRepository;
import repositories.FacultateRepository;

public class ExamenService {
    private final ExamenRepository examenRepository;

    public ExamenService() {
        examenRepository = ExamenRepository.build(ExamenRepository.Type.FILE);
    }

    public void changeExamen(String numeFacultate, double pondere1, double pondere2){
        examenRepository.changeExamen(numeFacultate, pondere1, pondere2);
    }

    public Examen findExamen(String numeFacultate){
        return examenRepository.findExamen(numeFacultate);
    }
}
