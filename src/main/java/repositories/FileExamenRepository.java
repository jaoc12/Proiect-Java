package repositories;

import model.Examen;
import model.Facultate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileExamenRepository {

    private final String file = "date/Examene.csv";

    public List<Examen> loadCandidati(){
        List<Examen> lista = new ArrayList<>();
        Path path = Paths.get(file);

        try{
            if(!Files.exists(path)){
                throw new FileNotFoundException();
            }
            List<String> linii = Files.readAllLines(path);
            for(String linie : linii){
                String[] attr = linie.split(",");
                Examen examen = new Examen(Double.parseDouble(attr[0]), Double.parseDouble(attr[1]));
                lista.add(examen);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
