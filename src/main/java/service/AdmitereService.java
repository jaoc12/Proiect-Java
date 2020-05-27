package service;

import model.Candidat;
import model.Facultate;
import repositories.FacultateRepository;
import repositories.PrintCSV;
import java.util.Collections;
import java.util.List;

public class AdmitereService {

    private Facultate getFacultate(String numeFacultate){
        return FacultateRepository.build(FacultateRepository.Type.FILE).findFacultate(numeFacultate);
    }

    public List<Candidat> getLista(String numeFacultate){
        Facultate facultate = getFacultate(numeFacultate);
        List<Candidat> candidati = facultate.getCandidati();

        for(Candidat c : candidati){
            c.setMedie(facultate.getMedie(c));
        }
        Collections.sort(candidati);
        return candidati;
    }

    public List<Candidat> getListaSpecifica(String numeFacultate, String listaCautata){
        List<Candidat> candidati = getLista(numeFacultate);
        Facultate facultate = getFacultate(numeFacultate);
        List<Candidat> lista = null;
        if(listaCautata.equals("Buget")) {
            lista = candidati.subList(0, facultate.getLocuriBuget());
        }
        else{
            if(listaCautata.equals("Taxa")){
                lista = candidati.subList(facultate.getLocuriBuget(),
                        facultate.getLocuriBuget() + facultate.getLocuriTaxa());
            }
            else{
                lista = candidati.subList(facultate.getLocuriBuget() + facultate.getLocuriTaxa(),
                        candidati.size());
            }
        }
        return lista;
    }

    public void printListe(String numeFacultate) {
        Facultate facultate = getFacultate(numeFacultate);
        List<Candidat> candidati = getLista(numeFacultate);
        List<Candidat> admisiBuget = candidati.subList(0, facultate.getLocuriBuget());
        List<Candidat> admisiTaxa = candidati.subList(facultate.getLocuriBuget(),
                facultate.getLocuriBuget() + facultate.getLocuriTaxa());
        List<Candidat> respinsi = candidati.subList(facultate.getLocuriBuget() + facultate.getLocuriTaxa(),
                candidati.size());

        PrintCSV csv = PrintCSV.getInstance();

        csv.printAdmitere("Admisi Buget", admisiBuget, numeFacultate);
        csv.printAdmitere("Admisi Taxa", admisiTaxa, numeFacultate);
        csv.printAdmitere("Respinsi", respinsi, numeFacultate);
    }

}
