package repositories;

import model.Candidat;
import model.Facultate;
import model.Profesor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class FileProfesorRepository {

    private final String file = "date/Profesori.csv";

    public SortedSet<Profesor> loadProfesori(String nume){

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
}
