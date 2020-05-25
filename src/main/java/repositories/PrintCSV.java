package repositories;

import model.Candidat;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class PrintCSV {

    public void printAdmitere(String numeFisier, List<Candidat> lista){

        try {
            FileWriter writer = new FileWriter("date/" + numeFisier + ".csv");
            writer.append("Nume,Prenume,Medie");
            writer.append("\n");

            for(Candidat c : lista){
                writer.append(c.getNume() + "," + c.getPrenume() + "," + c.getMedie());
                writer.append("\n");
            }

            writer.flush();
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void printCandidati(List<Candidat> lista){

        FileWriter writer = null;
        try {
            writer = new FileWriter("date/Candidati.csv");
            writer.append("Nume,Prenume,Password,Email,Facultate,Nota1,Nota2,Medie");
            writer.append("\n");

            for(Candidat c : lista){
                writer.append(c.getNume() + "," + c.getPrenume() + "," + c.getPassword() + ","
                        + c.getEmail() + "," + c.getFacultate() + "," + c.getNota1() + ","
                        + c.getNota2() + "," + c.getMedie());
                writer.append("\n");
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
