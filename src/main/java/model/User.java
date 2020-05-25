package model;

public class User {

    protected String nume;
    protected String prenume;
    protected String password;
    protected String email;
    protected String facultate;

    public User(String nume, String prenume, String password, String email, String facultate) {
        this.nume = nume;
        this.prenume = prenume;
        this.password = password;
        this.email = email;
        this.facultate = facultate;
    }

    @Override
    public String toString() {
        return nume + " " + prenume + ", Facultate: "  + facultate;
    }

    public String getFacultate() {
        return facultate;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
