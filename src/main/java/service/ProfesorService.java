package service;

import model.Profesor;
import repositories.ProfesorRepository;

public class ProfesorService {
    private final ProfesorRepository profesorRepository;

    public ProfesorService() {
        profesorRepository = ProfesorRepository.build(ProfesorRepository.Type.FILE);
    }

    public Profesor login(String email, String password, String numeFacultate) {
        return profesorRepository.findProfesor(email, password, numeFacultate);
    }

    public void register(Profesor profesor) {
        profesorRepository.addProfesor(profesor);
    }
}
