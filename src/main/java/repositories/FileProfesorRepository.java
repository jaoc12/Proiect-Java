package repositories;

import model.Profesor;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class FileProfesorRepository implements ProfesorRepository{

    private final String file = "date/Profesori.csv";
    PrintCSV printer = PrintCSV.getInstance();

    public SortedSet<Profesor> loadProfesori(String nume){
        printer.printAudit("loadProfesori");
        SortedSet<Profesor> lista = new TreeSet<>();
        Path path = Paths.get(file);

        try{
            if(!Files.exists(path)){
                throw new FileNotFoundException();
            }
            List<String> linii = Files.readAllLines(path);
            for(String linie : linii){
                String[] attr = linie.split(",");
                if(attr[4].equals(nume)){
                    Profesor p = new Profesor(attr[0], attr[1], attr[2], attr[3], attr[4]);
                    lista.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void addProfesor(Profesor profesor) {
        printer.printAudit("addProfesor");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter out = new PrintWriter(fileWriter);
            out.println(profesor.getNume()+","+profesor.getPrenume()+","+profesor.getPassword()+","+
                    profesor.getEmail()+","+profesor.getFacultate());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeProfesor(String nume, String numeFacultate) {
        printer.printAudit("removeProfesor");
        SortedSet<Profesor> listaProfesori = loadProfesori(numeFacultate);
        int i = 0;
        Profesor profesorEliminat = null;
        for(Profesor p : listaProfesori){
            if (p.getNume().equals(nume)){
                profesorEliminat = new Profesor(p.getNume(), p.getPrenume(), p.getPassword(),
                        p.getEmail(), p.getFacultate());
                break;
            }
            i++;
        }
        if(i != listaProfesori.size()){
            listaProfesori.remove(profesorEliminat);
            printer.printProfesori(listaProfesori);
        }
        else {
            System.out.println("Profesorul cautat nu exista!");
        }
    }

    @Override
    public void changeProfesor(String nume, String numeFacultate, String password) {
        printer.printAudit("changeProfesor");
        SortedSet<Profesor> listaProfesori = loadProfesori(numeFacultate);
        int i = 0;
        for(Profesor p : listaProfesori){
            if (p.getNume().equals(nume)){
                p.setPassword(password);
                break;
            }
            i++;
        }
        if(i != listaProfesori.size()){
            printer.printProfesori(listaProfesori);
        }
        else {
            System.out.println("Profesorul cautat nu exista!");
        }
    }

    @Override
    public Profesor findProfesor(String email, String password, String numeFacultate) {
        printer.printAudit("isProfesor");
        SortedSet<Profesor> listaProfesori = loadProfesori(numeFacultate);
        for(Profesor p : listaProfesori){
            if (p.getEmail().equals(email) && p.getPassword().equals(password)){
                return p;
            }
        }
        return null;
    }
}
