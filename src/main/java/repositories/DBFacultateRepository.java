package repositories;

import managers.DBConnectionManager;
import model.Facultate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBFacultateRepository implements FacultateRepository{

    private final PrintCSV printer = new PrintCSV();

    @Override
    public List<Facultate> loadFacultati() {
        printer.printAudit("loadFacultati");
        String sql = "select * from facultati";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql)
        ) {
            List<Facultate> lista = new ArrayList<>();

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                String nume = set.getString("nume");
                int locuriBuget = set.getInt("locuribuget");
                int locuriTaxa = set.getInt("locuritaxa");

                lista.add(new Facultate(nume, locuriBuget, locuriTaxa));
            }

            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addFacultate(Facultate facultate) {
        printer.printAudit("addFacultate");
        String sql = "INSERT INTO facultati VALUES(?, ?, ?)";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql)
        ) {
            statement.setString(1, facultate.getNume());
            statement.setInt(2, facultate.getLocuriBuget());
            statement.setInt(3, facultate.getLocuriTaxa());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeFacultate(String numeFacultate) {
        printer.printAudit("removeFacultate");
        String sql = "DELETE FROM facultati Where nume = ?";

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
    public void changeFacultate(String numeFacultate, int locuriBuget, int locuriTaxa) {
        printer.printAudit("changeFacultate");
        String sql = "UPDATE facultati Set locuribuget = ?, locuritaxa = ? WHERE nume = ?";

        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql)
        ) {
            statement.setInt(1, locuriBuget);
            statement.setInt(2, locuriTaxa);
            statement.setString(3, numeFacultate);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Facultate findFacultate(String numeFacultate) {
        printer.printAudit("findFacultate");
        String sql = "SELECT * FROM facultati WHERE   nume = ?";
        try (
                Connection con = DBConnectionManager.getInstance().createConnection();
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setString(1, numeFacultate);

            ResultSet set = statement.executeQuery();

            if (set.next()) {
                int locuriBuget = set.getInt("locuribuget");
                int locuriTaxa = set.getInt("locuritaxa");
                return(new Facultate(numeFacultate, locuriBuget, locuriTaxa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
