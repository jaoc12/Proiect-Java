package service;

import model.Candidat;
import model.Facultate;
import repositories.PrintCSV;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceAdmitere {

    public void listaFinala(Facultate facultate) {
        List<Candidat> candidati = facultate.getCandidati();

        for(Candidat c : candidati){
            c.setMedie(facultate.getMedie(c));
        }
        Collections.sort(candidati);
        List<Candidat> admisiBuget = candidati.subList(0, facultate.getLocuriBuget());
        List<Candidat> admisiTaxa = candidati.subList(facultate.getLocuriBuget(),
                facultate.getLocuriBuget() + facultate.getLocuriTaxa());
        List<Candidat> respinsi = candidati.subList(facultate.getLocuriBuget() + facultate.getLocuriTaxa(),
                candidati.size());

        PrintCSV csv = new PrintCSV();

        csv.printAdmitere("Admisi Buget", admisiBuget);
        csv.printAdmitere("Admisi Taxa", admisiTaxa);
        csv.printAdmitere("Respinsi", respinsi);
    }

}
