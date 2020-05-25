package repositories;

import managers.DBConnectionManager;
import model.Candidat;
import model.Examen;
import model.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class DBExamenRepository implements ExamenRepository{

    private final PrintCSV printer = new PrintCSV();

    @Override
    public List<Examen> loadExamene() {
        printer.printAudit("loadExamene");
        String sql = "select * from examene";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql)
        ) {
            List<Examen> lista = new ArrayList<>();

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                Double pondere1 = set.getDouble("pondere1");
                Double pondere2 = set.getDouble("pondere2");
                String facultate = set.getString("facultate");

                lista.add(new Examen(pondere1, pondere2, facultate));
            }

            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addExamen(Examen examen) {
        printer.printAudit("addExamen");
        String sql = "INSERT INTO examene VALUES(?, ?, ?)";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql)
        ) {
            statement.setDouble(1, examen.getPondere1());
            statement.setDouble(2, examen.getPondere2());
            statement.setString(3, examen.getNumeFacultate());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeExamen(String numeFacultate) {
        printer.printAudit("removeExamen");
        String sql = "DELETE FROM examene Where facultate = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql)
        ) {
            statement.setString(1, numeFacultate);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeExamen(String numeFacultate, double pondere1, double pondere2) {
        printer.printAudit("changeExamen");
        String sql = "UPDATE examene Set pondere1 = ?, pondere2 = ? WHERE facultate = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql)
        ) {
            statement.setDouble(1, pondere1);
            statement.setDouble(2, pondere2);
            statement.setString(3, numeFacultate);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Examen findExamen(String numeFacultate) {
        printer.printAudit("findExamen");
        String sql = "SELECT * FROM examene WHERE   facultate = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, numeFacultate);

            ResultSet set = statement.executeQuery();

            if (set.next()) {
                double pondere1 = set.getDouble("pondere1");
                double pondere2 = set.getDouble("pondere2");
                return(new Examen(pondere1, pondere2, numeFacultate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
