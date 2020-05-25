package repositories;

import managers.DBConnectionManager;
import model.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.SortedSet;
import java.util.TreeSet;

public class DBProfesorRepository implements ProfesorRepository{

    private final PrintCSV printer = new PrintCSV();

    @Override
    public SortedSet<Profesor> loadProfesori(String numeFacultate) {
        printer.printAudit("loadProfesori");
        String sql = "select * from profesori where facultate = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql)
        ) {
            SortedSet<Profesor> lista = new TreeSet<>();
            statement.setString(1, numeFacultate);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                String nume = set.getString("nume");
                String prenume = set.getString("prenume");
                String password = set.getString("password");
                String email = set.getString("email");

                lista.add(new Profesor(nume, prenume, password, email,
                        numeFacultate));
            }

            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addProfesor(Profesor profesor) {
        printer.printAudit("addProfesor");
        String sql = "INSERT INTO profesori VALUES(?, ?, ?, ?, ?)";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql)
        ) {
            statement.setString(1, profesor.getNume());
            statement.setString(2, profesor.getPrenume());
            statement.setString(3, profesor.getPassword());
            statement.setString(4, profesor.getEmail());
            statement.setString(5, profesor.getFacultate());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeProfesor(String nume, String numeFacultate) {
        printer.printAudit("removeProfesor");
        String sql = "DELETE FROM profesori Where nume = ? and facultate = ?";

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
    public void changeProfesor(String nume, String numeFacultate, String password) {
        printer.printAudit("changeProfesor");
        String sql = "UPDATE profesori Set password = ? WHERE nume = ? and facultate = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql)
        ) {
            statement.setString(1, password);
            statement.setString(2, nume);
            statement.setString(3, numeFacultate);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isProfesor(String email, String password, String numeFacultate) {
        printer.printAudit("isProfesor");
        String sql = "SELECT * FROM profesori where email = ? and facultate = ? and password = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, email);
            statement.setString(2, numeFacultate);
            statement.setString(3, password);

            ResultSet set = statement.executeQuery();

            if (set.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
