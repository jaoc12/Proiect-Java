package model;

import repositories.FileCandidatRepository;
import java.util.List;

public class Facultate {

    private String nume;
    private Examen examen;
    private int locuriBuget;
    private int locuriTaxa;
    private List<Candidat> candidati;

    public Facultate(String nume, int locuriBuget, int locuriTaxa, double pondere1, double pondere2) {
        FileCandidatRepository file = new FileCandidatRepository();
        this.nume = nume;
        this.examen = new Examen(pondere1, pondere2, "Info");
        this.locuriBuget = locuriBuget;
        this.locuriTaxa = locuriTaxa;
        this.candidati = file.loadCandidati(this.nume);
    }

    public double getMedie(Candidat candidat){
        return this.examen.getMedie(candidat.getNota1(), candidat.getNota2());
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getLocuriBuget() {
        return locuriBuget;
    }

    public void setLocuriBuget(int locuriBuget) {
        this.locuriBuget = locuriBuget;
    }

    public int getLocuriTaxa() {
        return locuriTaxa;
    }

    public void setLocuriTaxa(int locuriTaxa) {
        this.locuriTaxa = locuriTaxa;
    }

    public List<Candidat> getCandidati() {
        return candidati;
    }

    public void setCandidati(List<Candidat> candidati) {
        this.candidati = candidati;
    }
}
