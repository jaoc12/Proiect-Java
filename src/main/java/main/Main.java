package main;

import model.Candidat;
import repositories.FileCandidatRepository;
import repositories.PrintCSV;
import service.ServiceCandidat;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        FileCandidatRepository file = new FileCandidatRepository();
        List<Candidat> candidats = file.loadCandidati("Info");
        ServiceCandidat service = new ServiceCandidat();
        for(Candidat c : candidats){
            System.out.println(c.getNota1());
        }
        PrintCSV csv = new PrintCSV();
        csv.printCandidati(candidats);
    }
}
