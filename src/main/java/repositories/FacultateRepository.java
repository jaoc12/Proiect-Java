package repositories;

import model.Facultate;

import java.util.List;

public interface FacultateRepository {

    List<Facultate> loadFacultati();
    void addFacultate(Facultate facultate);
    void removeFacultate(String numeFacultate);
    void changeFacultate(String numeFacultate, int locuriBuget, int locuriTaxa);
    Facultate findFacultate(String numeFacultate);

    static FacultateRepository build(FacultateRepository.Type type) {
        switch (type) {
            case DB: return new DBFacultateRepository();
            case FILE: return new FileFacultateRepository();
        }

        throw new RuntimeException("No such type");
    }

    enum Type {
        DB, FILE
    }
}
