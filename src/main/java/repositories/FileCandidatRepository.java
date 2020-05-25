package repositories;

import model.Candidat;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileCandidatRepository implements CandidatRepository{

    private final String file = "date/Candidati.csv";
    private final PrintCSV printer = new PrintCSV();

    public List<Candidat> loadCandidati(String numeFacultate){
        printer.printAudit("loadCandidati");
        List<Candidat> lista = new ArrayList<>();
        Path path = Paths.get(file);

        try{
            if(!Files.exists(path)){
                throw new FileNotFoundException();
            }
            List<String> linii = Files.readAllLines(path);
            for(String linie : linii){
                String[] attr = linie.split(",");
                if(attr[4].equals(numeFacultate)){
                    Candidat c = new Candidat(attr[0], attr[1], attr[2],
                            attr[3], (attr[4]), Double.parseDouble(attr[5]),
                            Double.parseDouble(attr[6]), Double.parseDouble(attr[7]));
                    lista.add(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void addCandidat(Candidat candidat) {
        printer.printAudit("addCandidat");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter out = new PrintWriter(fileWriter);
            out.println(candidat.getNume()+","+candidat.getPrenume()+","+candidat.getPassword()+","+
                    candidat.getEmail()+","+candidat.getFacultate()+","+candidat.getNota1()+","+
                    candidat.getNota2()+","+candidat.getMedie());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCandidat(String nume, String numeFacultate) {
        printer.printAudit("removeCandidat");
        List<Candidat> listaCandidati = loadCandidati(numeFacultate);
        int i = 0;
        for(Candidat c : listaCandidati){
            if (c.getNume().equals(nume)){
                break;
            }
            i++;
        }
        if(i != listaCandidati.size()){
            listaCandidati.remove(i);
            printer.printCandidati(listaCandidati);
        }
        else {
            System.out.println("Candidatul cautat nu exista!");
        }
    }

    @Override
    public void changeCandidat(String nume, String numeFacultate, double nota1, double nota2) {
        printer.printAudit("changeCandidat");
        List<Candidat> listaCandidati = loadCandidati(numeFacultate);
        int i = 0;
        for(Candidat c : listaCandidati){
            if (c.getNume().equals(nume)){
                c.setNota1(nota1);
                c.setNota2(nota2);
                break;
            }
            i++;
        }
        if(i != listaCandidati.size()){
            printer.printCandidati(listaCandidati);
        }
        else {
            System.out.println("Candidatul cautat nu exista!");
        }
    }

    @Override
    public boolean isCandidat(String email, String password, String numeFacultate) {
        printer.printAudit("isCandidat");
        List<Candidat> listaCandidati = loadCandidati(numeFacultate);
        for(Candidat c : listaCandidati){
            if(c.getEmail().equals(email) && c.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
