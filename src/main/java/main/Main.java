package main;

import model.Candidat;
import repositories.CandidatRepository;
import repositories.FileCandidatRepository;
import repositories.PrintCSV;
import service.ServiceCandidat;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        CandidatRepository candidatRepository = CandidatRepository.build(CandidatRepository.Type.DB);
        candidatRepository.changeCandidat("Gelu", "Info", 7.2, 4.5);
        var lista = candidatRepository.loadCandidati("Info");
        for (Candidat c : lista){
            System.out.println(c.getNume());
        }
    }
}
