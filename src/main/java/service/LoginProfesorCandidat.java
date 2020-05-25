package service;

import model.Candidat;
import model.Profesor;
import repositories.CandidatRepository;
import repositories.ProfesorRepository;

public class LoginProfesorCandidat {
    private final ProfesorRepository profesorRepository;

    public LoginProfesorCandidat() {
        profesorRepository = ProfesorRepository.build(ProfesorRepository.Type.DB);
    }

    public boolean login(String email, String password, String numeFacultate) {
        return profesorRepository.isProfesor(email, password, numeFacultate);
    }

    public void register(Profesor profesor) {
        profesorRepository.addProfesor(profesor);
    }
}
