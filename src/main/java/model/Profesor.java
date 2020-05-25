package model;

public class Profesor extends User implements Comparable<Profesor>{

    public Profesor(String nume, String prenume, String password, String email, String facultate) {
        super(nume, prenume, password, email, facultate);
    }

    @Override
    public int compareTo(Profesor o) {
        if(this.getNume().equals(o.getNume())){
            return this.getPrenume().compareTo(o.getPrenume());
        }
        return this.getNume().compareTo(o.getNume());
    }
}
