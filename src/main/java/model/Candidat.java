package model;

public class Candidat extends User implements Comparable<Candidat> {

    private double nota1;
    private double nota2;
    private double medie;

    public Candidat(String nume, String prenume, String password, String email,
                    String facultate, double nota1, double nota2) {
        super(nume, prenume, password, email, facultate);
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.medie = 0;
    }

    public Candidat(String nume, String prenume, String password, String email,
                    String facultate, double nota1, double nota2, double medie) {
        super(nume, prenume, password, email, facultate);
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.medie = medie;
    }

    @Override
    public int compareTo(Candidat o) {
        if(this.getMedie() < o.getMedie()){
            return 1;
        }

        if(this.getMedie() > o.getMedie()){
            return -1;
        }
        if(this.getNume().equals(o.getNume())){
            return this.getPrenume().compareTo(o.getPrenume());
        }
        return this.getNume().compareTo(o.getNume());
    }

    public double getMedie() {
        return medie;
    }

    public void setMedie(double medie) {
        this.medie = medie;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }
}
