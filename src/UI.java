import java.io.IOException;
import java.util.Scanner;

public class UI {
    public static void softWrapCheck() {
        System.out.println("Per una maggiore godibilità di questo contenuto, ti consiglio di attivare il Soft-Wrap del terminale.");
        System.out.println("Ogni volta che questo programma chiederà l'inserimento di un input, successivamente alla sua digitazione dovrai premere il tasto invio.");
        System.out.println();
    }

    public static String benvenutoEGetUser(Scanner sc) throws IOException {
        String user = "";
        System.out.println("Benvenut*!");
        System.out.println("Questo programma è stato creato per la criptazione e decriptazione di messaggi");
        System.out.println("Inserisci il tuo nome, costituito solo da caratteri alfanumerici e privo di spazi");
        user = sc.nextLine();
        System.out.println("Grazie, " + user + ", ti auguro una buona esperienza.");
        System.out.println();
        return user;
    }

    public static String getMode(Scanner sc) throws IOException {
        String mode = "";
        System.out.println("Questo programma offre due modalità: la modalità criptazione, indicata con C, e la modalità decriptazione, indicata con D.");
        System.out.println("Digita la lettera associata alla modalità desiderata:");
        mode = sc.nextLine();
        boolean crit = mode.equals("C");
        boolean decrit = mode.equals("D");
        while (!(crit || decrit)) {
            System.out.println("Modalità inserita invalida, riprova");
            mode = sc.nextLine();
        }
        System.out.println();
        return mode;
    }

    public static void benvenutoD() {
        System.out.println("Benvenut* nella modalità decriptazione, dovresti possedere un file dal nome 'outputMittente.txt' (da copiare in textFiles) pieno di numeri, ovvero criptato, e quattro chiavi numeriche etichettate r, s, p e q. Ti verrà chiesto di inserire le chiavi, e successivamente verrà stampato il messaggio decriptato nel terminale. In caso di errori di decriptazione, la colpa è da attribuirsi alle chiavi in quanto il programma è corretto per dimostrazione.");
        System.out.println();
    }

    public static String getSender(Scanner sc) throws IOException {
        String sender = "";
        System.out.println("Inserisci il nome del mittente");
        sender = sc.nextLine();
        System.out.println();
        return sender;
    }

    public static int[] inserisciChiaviD(Scanner sc) throws IOException {
        int[] chiavi = new int[4];
        System.out.println("Inserisci la chiave r");
        chiavi[0] = sc.nextInt();
        System.out.println("Inserisci la chiave s");
        chiavi[1] = sc.nextInt();
        System.out.println("Inserisci la chiave p");
        chiavi[2] = sc.nextInt();
        System.out.println("Inserisci la chiave q");
        chiavi[3] = sc.nextInt();
        return chiavi;
    }

    public static void stampaMessaggio(String fileName, int[] chiavi) throws IOException {
        System.out.println("Stampa del messaggio decriptato con la chiave [" + chiavi[0] + ", " + chiavi[1] + ", " + chiavi[2] + ", " + chiavi[3] + "].");
        System.out.println("----- Inizio messaggio! -----");
        System.out.println();
        fileReading.decriptaFile(fileName, "index.txt", chiavi);
        System.out.println();
        System.out.println("----- Fine messaggio! -----");
        System.out.println();
    }

    public static void benvenutoC(String user) {
        System.out.println("Benvenut* nella modalità criptazione. Questa modalità richiede la presenza di un file di nome 'input" + user + ".txt' nella cartella textFiles, dove scriverai il tuo messaggio usando i simboli contenuti in 'index.txt', e di un file vuoto di nome 'output" + user + ".txt' nella stessa cartella, che verrà riempito col messaggio criptato.");
        System.out.println();
        System.out.println("Ti verrà chiesto di scegliere due numeri primi, p e q, entrambi idealmente minori di 46341, in modo che il loro prodotto non superi il limite degli int di Java, e tali che una volta calcolato phi = (p-1)*(q-1), questo sia strettamente maggiore di 106.");
        System.out.println("Poi dovrai scegliere la chiave s, con delle dovute accortezze:");
        System.out.println("Innanzitutto s dovrà essere strettamente minore di phi e non avere fattori in comune con esso, puoi controllare la fattorizzazione di phi su https://www.wolframalpha.com/ per essere più comod*.");
        System.out.println("La chiave r verrà calcolata per te dal programma.");
        System.out.println();
    }

    public static int[] inserisciChiaviC(Scanner sc) throws IOException {
        int[] chiavi = new int[4];
        System.out.println("Inserisci la chiave s");
        chiavi[1] = sc.nextInt();
        System.out.println("Inserisci il primo p");
        chiavi[2] = sc.nextInt();
        System.out.println("Inserisci il primo q");
        chiavi[3] = sc.nextInt();
        chiavi[0] = RSA.EuclideEsteso(chiavi[1], chiavi[2], chiavi[3]);
        System.out.println("Il valore calcolato della chiave r è " + chiavi[0] + ".");
        return chiavi;
    }

    public static void criptazione(String user, int[] chiavi) throws IOException {
        System.out.println("Criptazione del file in corso...");
        String inputName = "textFiles/input" + user + ".txt";
        String outputName = "textFiles/output" + user + ".txt";
        fileReading.criptaFile(inputName, outputName, "index.txt", chiavi);
        System.out.println("Criptazione completata! Decriptazione e stampa di controllo");
        System.out.println();
        stampaMessaggio(outputName,chiavi);
    }

    public static void termineEsecuzione(){
        System.out.println("------------------TERMINE ESECUZIONE------------------");
        System.out.println("Grazie di aver usato il mio programma, buona giornata.");
        System.out.println("In caso di problemi con il programma, visitare https://fil-cyan-tron.github.io/");
    }
}
