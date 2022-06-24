package service;

import model.Examen;
import repositories.ExamenRepository;

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
