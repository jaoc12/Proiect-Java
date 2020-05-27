package repositories;

import model.Facultate;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileFacultateRepository implements FacultateRepository{
    private final String file = "date/Facultati.csv";
    private final PrintCSV printer = PrintCSV.getInstance();

    @Override
    public List<Facultate> loadFacultati() {
        printer.printAudit("loadFacultati");
        List<Facultate> lista = new ArrayList<>();
        Path path = Paths.get(file);

        try{
            if(!Files.exists(path)){
                throw new FileNotFoundException();
            }
            List<String> linii = Files.readAllLines(path);
            for(String linie : linii){
                String[] attr = linie.split(",");
                if(!attr[0].equals("Nume")) {
                    Facultate facultate = new Facultate(attr[0], Integer.parseInt(attr[1]), Integer.parseInt(attr[2]));
                    lista.add(facultate);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void addFacultate(Facultate facultate) {
        printer.printAudit("addFacultate");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter out = new PrintWriter(fileWriter);
            out.println(facultate.getNume()+","+facultate.getLocuriBuget()+","+facultate.getLocuriTaxa());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeFacultate(String numeFacultate) {
        printer.printAudit("removeFacultate");
        List<Facultate> listaFacultate = loadFacultati();
        int i = 0;
        for(Facultate facultate : listaFacultate){
            if (facultate.getNume().equals(numeFacultate)){
                break;
            }
            i++;
        }
        if(i != listaFacultate.size()){
            listaFacultate.remove(i);
            printer.printFacultati(listaFacultate);
        }
        else {
            System.out.println("Candidatul cautat nu exista!");
        }
    }

    @Override
    public void changeFacultate(String numeFacultate, int locuriBuget, int locuriTaxa) {
        printer.printAudit("changeFacultate");
        List<Facultate> listaFacultate = loadFacultati();
        int i = 0;
        for(Facultate facultate : listaFacultate){
            if (facultate.getNume().equals(numeFacultate)){
                facultate.setLocuriBuget(locuriBuget);
                facultate.setLocuriTaxa(locuriTaxa);
                break;
            }
            i++;
        }
        if (i != listaFacultate.size()) {
            printer.printFacultati(listaFacultate);
        } else {
            System.out.println("Candidatul cautat nu exista!");
        }
    }

    @Override
    public Facultate findFacultate(String numeFacultate) {
        printer.printAudit("findFacultate");
        List<Facultate> listaFacultate = loadFacultati();
        int i = 0;
        for (Facultate facultate : listaFacultate) {
            if (facultate.getNume().equals(numeFacultate)) {
                return facultate;
            }
        }
        return null;
    }
}
