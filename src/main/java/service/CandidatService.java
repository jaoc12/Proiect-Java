package service;

import model.Candidat;
import repositories.CandidatRepository;

public class CandidatService {
    private final CandidatRepository candidatRepository;

    public CandidatService() {
        candidatRepository = CandidatRepository.build(CandidatRepository.Type.FILE);
    }

    public Candidat login(String email, String password, String numeFacultate) {
        return candidatRepository.findCandidat(email, password, numeFacultate);
    }

    public void changeNote(String email, String numeFacultate, double nota1, double nota2){
        candidatRepository.changeCandidat(email, numeFacultate, nota1, nota2);
    }

    public void register(Candidat candidat) {
        candidatRepository.addCandidat(candidat);
    }
}
