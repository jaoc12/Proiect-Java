package repositories;

import model.Examen;

import java.util.List;

public interface ExamenRepository {
    List<Examen> loadExamene();
    void addExamen(Examen examen);
    void removeExamen(String numeFacultate);
    void changeExamen(String numeFacultate, double pondere1, double pondere2);
    Examen findExamen(String numeFacultate);

    static ExamenRepository build(ExamenRepository.Type type) {
        switch (type) {
            case DB: return new DBExamenRepository();
            case FILE: return new FileExamenRepository();
        }

        throw new RuntimeException("No such type");
    }

    enum Type {
        DB, FILE
    }
}
