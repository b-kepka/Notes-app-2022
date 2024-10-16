import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Baza {
    Connection con;
    String url,dbname,dbuser,dbpassword, dbtablename, fileName = "src/baza.properties";
    String sql;
    ArrayList<String[]> values;
    public Baza() {
        try {
            FileReader freader = new FileReader(fileName);
            Properties prop = new Properties();
            prop.load(freader);
            url = prop.getProperty("db.url");
            dbname = prop.getProperty("db.name");
            dbuser = prop.getProperty("db.username");
            dbpassword = prop.getProperty("db.password");
            dbtablename = prop.getProperty("db.tablename");
            con = null;
            con = DriverManager.getConnection(url + dbname, dbuser, dbpassword);
            System.out.println("Połączenie udane!");
        } catch (Exception e) {
            System.out.println("Połączenie nieudane!");
            System.out.println("Powód: ");
            System.out.println(e);
        }
    }
    public void dodajNotatke(String notatka, String data_waz) {
        sql = Zapytanie.SQL_ADD_QUERY;
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, notatka);
            stmt.setString(2, data_waz);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<String[]> wyszukajNotatkebyDate(DateType dateType,String data) {
        if (dateType == DateType.WPROWADZENIA) {
            sql = Zapytanie.SQL_SEARCH_QUERY_DATA_WPR;
        } else if (dateType == DateType.WAZNOSCI) {
            sql = Zapytanie.SQL_SEARCH_QUERY_DATA_WAZ;
        }
        System.out.println(sql);
        values = new ArrayList<String[]>();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,data);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String valid = Integer.toString(rs.getInt("id"));
                String valnotatka = rs.getString("notatka");
                String valldata_wpr = rs.getString("data_wprowadzenia");
                String valdata_waz = rs.getString("data_waznosci");
                values.add(new String[]{valid, valnotatka, valldata_wpr, valdata_waz});
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        for (String[] e:
                values) {
            for (String el:
                    e) {
                System.out.print(el+" | ");
            }
            System.out.println();
        }
        return values;
    }
    public ArrayList<String[]> wyszukajNotatkebyID(int id) {
        sql = Zapytanie.SQL_SEARCH_QUERY_ID;
        values = new ArrayList<String[]>();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,Integer.toString(id));
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String valid = Integer.toString(rs.getInt("id"));
                String valnotatka = rs.getString("notatka");
                String valldata_wpr = rs.getString("data_wprowadzenia");
                String valdata_waz = rs.getString("data_waznosci");
                values.add(new String[]{valid, valnotatka, valldata_wpr, valdata_waz});
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        for (String[] e:
                values) {
            for (String el:
                 e) {
                System.out.print(el+" | ");
            }
            System.out.println();
        }
        return values;
    }
    public void wyszukajKeyWord() {

    }
    public void usunNotatke(int id) {
        sql = Zapytanie.SQL_DELETE_QUERY;
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, Integer.toString(id));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void zmienDateWaznosci(int id, String data_waznosci) {
        sql = Zapytanie.SQL_UPDATE_QUERY;
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_waznosci);
            stmt.setString(2,Integer.toString(id));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
