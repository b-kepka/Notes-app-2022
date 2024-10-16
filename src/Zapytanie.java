public class Zapytanie {
    static String SQL_ADD_QUERY = "INSERT INTO Notatki(notatka, data_wprowadzenia, data_waznosci) VALUES(?, curdate(), ?);";
    static String SQL_SEARCH_QUERY_DATA_WPR = "SELECT * FROM Notatki WHERE data_wprowadzenia like ?";
    static String SQL_SEARCH_QUERY_DATA_WAZ = "SELECT * FROM Notatki WHERE data_waznosci like ?";
    static String SQL_SEARCH_QUERY_ID = "SELECT * FROM Notatki WHERE id = ?";
    static String SQL_SEARCH_QUERY_KEYWORD = "SELECT * FROM Notatki WHERE notatka like %?% "; // sprawdzic
    static String SQL_DELETE_QUERY = "DELETE FROM Notatki WHERE id = ?";
    static String SQL_UPDATE_QUERY = "UPDATE Notatki SET data_waznosci = ? WHERE id = ?";
}
