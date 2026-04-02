
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    // Variabili di connessione ad db
    static String url = "jdbc:mysql://localhost:8889/";
    static String user = "root";
    static String password = "root";
    static String nomeDB = "ospedale";

    // Metodo per query insert
    public static void insert(String query, ArrayList<String> parameters) {
        try (Connection conn = DriverManager.getConnection(url + nomeDB, user, password);
                PreparedStatement pstmt = conn.prepareStatement(query);) {
            for (int i = 1; i <= parameters.size(); i++) {
                pstmt.setString(i, parameters.get(i - 1));
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore: " + e);
        }
    }

    // Metodo per query select
    public static void read(String query, ArrayList<String> parameters) {

        try (Connection conn = DriverManager.getConnection(url + nomeDB, user, password);
                PreparedStatement pstmt = conn.prepareStatement(query);) {
            if (!parameters.isEmpty()) {
                for (int i = 1; i <= parameters.size(); i++) {
                    pstmt.setString(i, parameters.get(i - 1));
                }

            }
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

                    // Andiamo a capo alla fine di ogni riga del database
                }
                System.out.println("\n------------");

            }
        } catch (SQLException e) {
            System.out.println("Errore: " + e);
        }
    }

    // Main programma
    public static void main(String[] args) throws Exception {
        System.out.println("Benvenuto nel programma gestione referti");
        Scanner myScanner = new Scanner(System.in);
        boolean uscita = false;
        while (uscita == false) {
            System.out.println("------------");
            System.out.println("Inserisci:\n1 per visualizzare i pazienti" +
                    "\n2 per visualizzare i medici\n3 per visualizzare i referti" +
                    "\n4 per visualizzare i referti di un singolo paziente\n5 per visualizzare i referti di un singolo medico"
                    +
                    "\n6 per inserire referti\n7 per inserire documenti\n8 per uscire");

            String scelta = myScanner.nextLine();
            System.out.println("------------");
            String query = "";

            switch (scelta) {
                case "1":
                    query = "select * from paziente";
                    read(query, new ArrayList<>());

                    break;
                    
                case "2":
                    query = "select * from medico";
                    read(query, new ArrayList<>());
                    break;

                case "3":
                    query = "SELECT referto.id, referto.id_paziente, paziente.nome, paziente.cognome, " +
                            "referto.id_medico, medico.nome, medico.cognome, allegati.id, allegati.path, allegati.tipo, "
                            +
                            "referto.data_visita, referto.diagnosi " +
                            "FROM referto LEFT JOIN paziente " +
                            "on referto.id_paziente = paziente.id " +
                            "LEFT JOIN medico on referto.id_medico = medico.id " +
                            "LEFT JOIN allegati on referto.id = allegati.id_referto order by referto.id";
                    read(query, new ArrayList<>());

                    break;

                case "4":

                    query = "SELECT referto.id, referto.id_paziente, paziente.nome, paziente.cognome, " +
                            "referto.id_medico, medico.nome, medico.cognome, allegati.id, allegati.path, allegati.tipo, "
                            +
                            "referto.data_visita, referto.diagnosi " +
                            "FROM referto LEFT JOIN paziente " +
                            "on referto.id_paziente = paziente.id " +
                            "LEFT JOIN medico on referto.id_medico = medico.id " +
                            "LEFT JOIN allegati on referto.id = allegati.id_referto " +
                            "where referto.id_paziente = ? order by referto.id";

                    System.out.println("Inserisci id paziente");
                    String idPaziente = myScanner.nextLine();
                    ArrayList<String> parametersP = new ArrayList<>();
                    parametersP.add(idPaziente);
                    read(query, parametersP);

                    break;

                case "5":
                    query = "SELECT referto.id, referto.id_paziente, paziente.nome, paziente.cognome, " +
                            "referto.id_medico, medico.nome, medico.cognome, allegati.id, allegati.path, allegati.tipo, "
                            +
                            "referto.data_visita, referto.diagnosi " +
                            "FROM referto LEFT JOIN paziente " +
                            "on referto.id_paziente = paziente.id " +
                            "LEFT JOIN medico on referto.id_medico = medico.id " +
                            "LEFT JOIN allegati on referto.id = allegati.id_referto " +
                            "where referto.id_medico = ? order by referto.id";

                    System.out.println("Inserisci id medico");
                    String idMedico = myScanner.nextLine();
                    ArrayList<String> parametersM = new ArrayList<>();
                    parametersM.add(idMedico);
                    read(query, parametersM);
                    break;

                case "6":

                    query = "insert into referto(id_paziente, id_medico, data_visita, diagnosi) values(?,?,?,?)";
                    System.out.println("Inserisci id paziente");
                    String idPaz = myScanner.nextLine();
                    System.out.println("Inserisci id medico");
                    String idMed = myScanner.nextLine();
                    System.out.println("Inserisci data visita (formato AAAA-MM-GG)");
                    String dataVisita = myScanner.nextLine();
                    System.out.println("Inserisci diagnosi");
                    String diagnosi = myScanner.nextLine();
                    ArrayList<String> parametersIR = new ArrayList<>();
                    parametersIR.add(idPaz);
                    parametersIR.add(idMed);
                    parametersIR.add(dataVisita);
                    parametersIR.add(diagnosi);
                    insert(query, parametersIR);

                    break;

                case "7":
                    query = "insert into allegati(id_referto, path, tipo) values(?,?,?)";
                    System.out.println("Inserisci id referto");
                    String idRef = myScanner.nextLine();
                    System.out.println("Inserisci path file");
                    String path = myScanner.nextLine();
                    System.out.println("Inserisci tipo (Immagine o Documento o Video)");
                    String tipo = myScanner.nextLine();
                    ArrayList<String> parametersAl = new ArrayList<>();
                    parametersAl.add(idRef);
                    parametersAl.add(path);
                    parametersAl.add(tipo);

                    insert(query, parametersAl);
                    break;

                case "8":
                    System.out.println("Grazie per aver usato il programma!");
                    uscita = true;
                    break;

                default:
                    System.out.println("Comando non valido!");
            }

        }

    }
}
