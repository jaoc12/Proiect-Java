package service;

import model.Candidat;

import java.util.List;

public class ServiceCandidat {

    public List<Candidat> modificaNota(List<Candidat> candidati, String nume, double nota1, double nota2){

        for(Candidat candidat : candidati){
            if(nume.equals(candidat.getNume())){
                candidat.setNota1(nota1);
                candidat.setNota2(nota2);
                break;
            }
        }
        return candidati;
    }

    public List<Candidat> addUser(List<Candidat> candidati, String nume, String prenume,
                                     String password, String email,
                                     String facultate, double nota1, double nota2){

        candidati.add(new Candidat(nume, prenume, password, email,
                facultate, nota1, nota2));
        return candidati;
    }
    
    public List<Candidat> removeUser(List<Candidat> candidati, String nume){

        for(Candidat candidat : candidati){
            if(nume.equals(candidat.getNume())){
                candidati.remove(candidat);
            }
        }
        return candidati;
    }
}
