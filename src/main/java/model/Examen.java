package model;

public class Examen {

    private double pondere1;
    private double pondere2;

    public Examen(double pondere1, double pondere2) {
        this.pondere1 = pondere1;
        this.pondere2 = pondere2;
    }

    public double getPondere1() {
        return pondere1;
    }

    public void setPondere1(double pondere1) {
        this.pondere1 = pondere1;
    }

    public double getPondere2() {
        return pondere2;
    }

    public void setPondere2(double pondere2) {
        this.pondere2 = pondere2;
    }

    public double getMedie(double nota1, double nota2){
        return nota1 * this.pondere1 + nota2 * this.pondere2;
    }
}
