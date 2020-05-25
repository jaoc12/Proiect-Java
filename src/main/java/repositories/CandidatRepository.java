package repositories;

import model.Candidat;

import java.util.List;

public interface CandidatRepository {

    List<Candidat> loadCandidati(String numeFacultate);
    void addCandidat(Candidat candidat);
    void removeCandidat(String nume, String numeFacultate);
    void changeCandidat(String nume, String numeFacultate, double nota1, double nota2);
    boolean isCandidat(String email, String password, String numeFacultate);

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
