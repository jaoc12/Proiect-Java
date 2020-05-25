package repositories;

import model.Candidat;
import model.Examen;
import model.Profesor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;


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

        try {
            FileWriter writer = new FileWriter("date/Candidati.csv");
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

    public void printProfesori(SortedSet<Profesor> lista){
        try {
            FileWriter writer = new FileWriter("date/Profesori.csv");
            writer.append("Nume,Prenume,Password,Email,Facultate");
            writer.append("\n");

            for(Profesor p : lista){
                writer.append(p.getNume() + "," + p.getPrenume() + "," + p.getPassword() + ","
                        + p.getEmail() + "," + p.getFacultate());
                writer.append("\n");
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printExamene(List<Examen> lista){
        try {
            FileWriter writer = new FileWriter("date/Examene.csv");
            writer.append("Pondere1,Pondere2,Facultate");
            writer.append("\n");

            for(Examen e : lista){
                writer.append(e.getPondere1() + "," + e.getPondere2() + "," + e.getNumeFacultate());
                writer.append("\n");
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printAudit(String nume_actiune){
        try {
            FileWriter fileWriter = new FileWriter("date/Audit.csv", true);
            PrintWriter out = new PrintWriter(fileWriter);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            out.println(nume_actiune+","+formatter.format(new Date()));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
