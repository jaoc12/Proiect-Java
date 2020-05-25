package repositories;

import model.Profesor;

import java.util.List;
import java.util.SortedSet;

public interface ProfesorRepository {

    SortedSet<Profesor> loadProfesori(String numeFacultate);
    void addProfesor(Profesor profesor);
    void removeProfesor(String nume, String numeFacultate);
    void changeProfesor(String nume, String numeFacultate, String password);
    boolean isProfesor(String email, String password, String numeFacultate);

    static ProfesorRepository build(Type type) {
        switch (type) {
            case DB: return new DBProfesorRepository();
            case FILE: return new FileProfesorRepository();
        }

        throw new RuntimeException("No such type");
    }

    enum Type {
        DB, FILE
    }
}
