package service;

import model.Candidat;
import repositories.CandidatRepository;

public class InregistrareService {

    private final CandidatRepository candidatRepository = CandidatRepository.build(CandidatRepository.Type.FILE);

    public InregistrareService() {
    }

    public void addCandidat(String nume, String prenume, String password, String email, String facultate){
        Candidat c = new Candidat(nume, prenume, password, email, facultate, 0, 0);
        candidatRepository.addCandidat(c);
    }
}
