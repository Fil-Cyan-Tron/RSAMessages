import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UI.softWrapCheck();
        String user = "";
        String mode = "";
        int[] chiavi;
        Scanner sc = null;
        try {
            sc = new Scanner(System.in);
            user = UI.benvenutoEGetUser(sc);
            mode = UI.getMode(sc);
            if (mode.equals("D")) {
                UI.benvenutoD();
                String sender = "";
                sender = UI.getSender(sc);
                chiavi = UI.inserisciChiaviD(sc);
                String fileName = "textFiles/output" + sender + ".txt";
                UI.stampaMessaggio(fileName, chiavi);
            }
            if (mode.equals("C")) {
                UI.benvenutoC(user);
                UI.bisognoFileC(sc, user);
                chiavi = UI.inserisciChiaviC(sc);
                UI.criptazione(sc, user, chiavi);
            }
            if (UI.altraRun(sc)) {
                main(args);
            }
        } catch (IOException e) {
            System.out.println("Errore: " + e.getMessage());
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        UI.termineEsecuzione();
    }
}