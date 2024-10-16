import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Polaczenie {
    Connection con;
    String url,dbname,dbuser,dbpassword,dbtablename,fileName = "src/baza.properties";
    public Polaczenie() {
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
            con = DriverManager.getConnection(url+dbname,dbuser,dbpassword);
            System.out.println("Połączenie udane!");
        } catch(Exception e)
        {
            System.out.println("Połączenie nieudane!");
            System.out.println("Powód: ");
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Nie udało się zamknąć połączenia!");
                System.out.println("Powód: ");
                System.out.println(e);
            }
        }
    }
}
