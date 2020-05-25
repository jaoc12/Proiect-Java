package repositories;

import model.Candidat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileCandidatRepository {

    private final String file = "date/Candidati.csv";

    public List<Candidat> loadCandidati(String nume){
        List<Candidat> lista = new ArrayList<>();
        Path path = Paths.get(file);

        try{
            if(!Files.exists(path)){
                throw new FileNotFoundException();
            }
            List<String> linii = Files.readAllLines(path);
            for(String linie : linii){
                String[] attr = linie.split(",");
                if(attr[4].equals(nume)){
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
}
