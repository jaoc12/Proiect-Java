package service;

import model.Candidat;
import repositories.CandidatRepository;

import java.util.Optional;

public class LoginCandidatService {
    private final CandidatRepository candidatRepository;

    public LoginCandidatService() {
        candidatRepository = CandidatRepository.build(CandidatRepository.Type.DB);
    }

    public boolean login(String email, String password, String numeFacultate) {
        return candidatRepository.isCandidat(email, password, numeFacultate);
    }

    public void register(Candidat candidat) {
        candidatRepository.addCandidat(candidat);
    }
}
