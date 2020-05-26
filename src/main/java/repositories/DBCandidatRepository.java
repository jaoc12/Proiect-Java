package repositories;

import managers.DBConnectionManager;
import model.Candidat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBCandidatRepository implements CandidatRepository{

    private final PrintCSV printer = new PrintCSV();

    @Override
    public List<Candidat> loadCandidati(String numeFacultate) {
        printer.printAudit("loadCandidati");
        String sql = "select * from candidati where facultate = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql)
        ) {
            List<Candidat> lista = new ArrayList<>();
            statement.setString(1, numeFacultate);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                String nume = set.getString("nume");
                String prenume = set.getString("prenume");
                String password = set.getString("password");
                String email = set.getString("email");
                double nota1 = set.getDouble("nota1");
                double nota2 = set.getDouble("nota2");
                double medie = set.getDouble("medie");

                lista.add(new Candidat(nume, prenume, password, email,
                        numeFacultate, nota1, nota2, medie));
            }

            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addCandidat(Candidat candidat) {
        printer.printAudit("addCandidat");
        String sql = "INSERT INTO candidati VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql)
        ) {
            statement.setString(1, candidat.getNume());
            statement.setString(2, candidat.getPrenume());
            statement.setString(3, candidat.getPassword());
            statement.setString(4, candidat.getEmail());
            statement.setString(5, candidat.getFacultate());
            statement.setDouble(6, candidat.getNota1());
            statement.setDouble(7, candidat.getNota2());
            statement.setDouble(8, candidat.getMedie());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCandidat(String nume, String numeFacultate) {
        printer.printAudit("removeCandidat");
        String sql = "DELETE FROM candidati Where nume = ? and facultate = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql)
        ) {
            statement.setString(1, nume);
            statement.setString(2, numeFacultate);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeCandidat(String nume, String numeFacultate, double nota1, double nota2) {
        printer.printAudit("changeCandidat");
        String sql = "UPDATE candidati Set nota1 = ?, nota2 = ? WHERE nume = ? and facultate = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql)
        ) {
            statement.setDouble(1, nota1);
            statement.setDouble(2, nota2);
            statement.setString(3, nume);
            statement.setString(4, numeFacultate);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Candidat findCandidat(String email, String password, String numeFacultate) {
        printer.printAudit("isCandidat");
        String sql = "SELECT * FROM candidati where email = ? and facultate = ? and password = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, email);
            statement.setString(2, numeFacultate);
            statement.setString(3, password);

            ResultSet set = statement.executeQuery();

            if (set.next()) {
                String nume = set.getString("nume");
                String prenume = set.getString("prenume");
                double nota1 = set.getDouble("nota1");
                double nota2 = set.getDouble("nota2");
                double medie = set.getDouble("medie");
                return new Candidat(nume, prenume, password, email, numeFacultate,
                        nota1, nota2, medie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
