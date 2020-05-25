package repositories;

import model.Candidat;
import model.Facultate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileFacultateRepository {
    private final String file = "date/Facultati.csv";

    public List<Facultate> loadCandidati(){
        List<Facultate> lista = new ArrayList<>();
        Path path = Paths.get(file);

        try{
            if(!Files.exists(path)){
                throw new FileNotFoundException();
            }
            List<String> linii = Files.readAllLines(path);
            for(String linie : linii){
                String[] attr = linie.split(",");
                Facultate facultate = new Facultate(attr[0], Integer.parseInt(attr[1]), Integer.parseInt(attr[2]),
                        Double.parseDouble(attr[3]), Double.parseDouble(attr[4]));
                lista.add(facultate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
