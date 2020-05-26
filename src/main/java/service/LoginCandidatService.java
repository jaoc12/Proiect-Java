package service;

import model.Candidat;
import repositories.CandidatRepository;

public class LoginCandidatService {
    private final CandidatRepository candidatRepository;

    public LoginCandidatService() {
        candidatRepository = CandidatRepository.build(CandidatRepository.Type.FILE);
    }

    public Candidat login(String email, String password, String numeFacultate) {
        return candidatRepository.findCandidat(email, password, numeFacultate);
    }

    public void register(Candidat candidat) {
        candidatRepository.addCandidat(candidat);
    }
}
