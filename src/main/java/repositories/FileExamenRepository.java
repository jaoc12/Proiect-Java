package repositories;

import model.Examen;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileExamenRepository implements ExamenRepository{

    private final String file = "date/Examene.csv";
    private final PrintCSV printer = PrintCSV.getInstance();

    public List<Examen> loadExamene(){
        printer.printAudit("loadExamene");
        List<Examen> lista = new ArrayList<>();
        Path path = Paths.get(file);

        try{
            if(!Files.exists(path)){
                throw new FileNotFoundException();
            }
            List<String> linii = Files.readAllLines(path);
            for(String linie : linii){
                String[] attr = linie.split(",");
                if(!attr[0].equals("Pondere1")) {
                    Examen examen = new Examen(Double.parseDouble(attr[0]), Double.parseDouble(attr[1]), attr[2]);
                    lista.add(examen);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void addExamen(Examen examen) {
        printer.printAudit("addExamen");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter out = new PrintWriter(fileWriter);
            out.println(examen.getPondere1()+","+examen.getPondere2()+","+examen.getNumeFacultate());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeExamen(String numeFacultate) {
        printer.printAudit("removeExamen");
        List<Examen> listaExamene = loadExamene();
        int i = 0;
        for(Examen e : listaExamene){
            if (e.getNumeFacultate().equals(numeFacultate)){
                break;
            }
            i++;
        }
        if(i != listaExamene.size()){
            listaExamene.remove(i);
            printer.printExamene(listaExamene);
        }
        else {
            System.out.println("Candidatul cautat nu exista!");
        }
    }

    @Override
    public void changeExamen(String numeFacultate, double pondere1, double pondere2) {
        printer.printAudit("changeExamen");
        List<Examen> listaExamene = loadExamene();
        int i = 0;
        for (Examen e : listaExamene) {
            if (e.getNumeFacultate().equals(numeFacultate)) {
                e.setPondere1(pondere1);
                e.setPondere2(pondere2);
                break;
            }
            i++;
        }
        if (i != listaExamene.size()) {
            printer.printExamene(listaExamene);
        } else {
            System.out.println("Candidatul cautat nu exista!");
        }
    }

    @Override
    public Examen findExamen(String numeFacultate) {
        printer.printAudit("findExamen");
        List<Examen> listaExamene = loadExamene();
        for (Examen e : listaExamene) {
            if (e.getNumeFacultate().equals(numeFacultate)) {
                return e;
            }
        }
        System.out.println("Candidatul cautat nu exista!");
        return null;
    }
}
