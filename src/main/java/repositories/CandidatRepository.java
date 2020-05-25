package repositories;

import model.Candidat;
import model.User;

import java.util.List;
import java.util.Optional;

public interface CandidatRepository {

    List<Candidat> loadCandidati(String numeFacultate);
    void addCandidat(Candidat candidat);
    void removeCandidat(String nume, String numeFacultate);
    void changeCandidat(String nume, String numeFacultate, double nota1, double nota2);

    static CandidatRepository build(Type type) {
        switch (type) {
            case DB: return new DBCandidatRepository();
            case FILE: return new FileCandidatRepository();
        }

        throw new RuntimeException("No such type");
    }

    enum Type {
        DB, FILE
    }
}
