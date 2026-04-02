
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    static String url = "jdbc:mysql://localhost:8889/";
    static String user = "root";
    static String password = "root";
    static String nomeDB = "ospedale";

    public static void insert(String query, ArrayList<String> parameters) {
        try (Connection conn = DriverManager.getConnection(url + nomeDB, user, password);
                PreparedStatement pstmt = conn.prepareStatement(query);) {
            for (int i = 1; i <= parameters.size(); i++) {
                pstmt.setString(i, parameters.get(i));
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore");
        }
    }

    public static void read(String query) {
        try (Connection conn = DriverManager.getConnection(url + nomeDB, user, password);
                PreparedStatement pstmt = conn.prepareStatement(query);) {
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();

            int columnCount = meta.getColumnCount();
            while (rs.next()) {
                // Cicliamo su ogni colonna della riga corrente
                for (int i = 1; i <= columnCount; i++) {
                    String nomeColonna = meta.getColumnName(i);
                    Object valore = rs.getObject(i);

                    // Stampiamo direttamente nome e valore
                    System.out.print(nomeColonna + ": " + valore);

                    // Aggiungiamo un separatore, tranne che dopo l'ultima colonna
                    if (i < columnCount) {
                        System.out.print(" | ");
                    }
                }

                // Andiamo a capo alla fine di ogni riga del database
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Errore");
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Benvenuto nel programma gestione referti");
        Scanner myScanner = new Scanner(System.in);
        boolean uscita = false;
        while(uscita == false){
        System.out.println("------------");
        System.out.println("Inserisci:\n1 per visualizzare i pazienti" +
                "\n2 per visualizzare i medici\n3 per visualizzare i referti" +
                "\n4 per inserire referti\n5 per inserire documenti\n6 per uscire");

        
        
        String scelta = myScanner.nextLine();

        switch (scelta) {
            case "1":
                System.out.println("visualizzare i pazienti");
                break;
            case "2":
                System.out.println("visualizzare i medici");
                break;
            case "3":
                System.out.println("visualizzare i referti");
                break;
            case "4":
                System.out.println("per inserire referti");
                break;
            case "5":
                System.out.println("inserire documenti");
                break;
            case "6":
                System.out.println("uscire");
                uscita = true;
                break;
        }
        }

    }
}
