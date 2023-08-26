import java.io.IOException;
import java.util.Scanner;

public class UI {
    public static void softWrapCheck() {
        System.out.println();
        System.out.println("-------------------------------------- WARLOCK --------------------------------------");
        System.out.println();
        System.out.println("Per una maggiore godibilità di questo contenuto, ti consiglio di attivare il Soft-Wrap del terminale.");
        System.out.println("Ogni volta che questo programma chiederà l'inserimento di un input, successivamente alla sua digitazione dovrai premere il tasto invio.");
        System.out.println();
    }

    public static String chiediInput(Scanner sc) {
        String prova = "";
        prova = sc.nextLine();
        return prova;
    }

    public static String benvenutoEGetUser(Scanner sc) throws IOException {
        System.out.println("Benvenut*!");
        System.out.println("Questo programma è stato creato per la criptazione e decriptazione di messaggi");
        System.out.println("Inserisci il tuo nome, costituito solo da caratteri alfanumerici e privo di spazi");
        String user = sc.nextLine();
        System.out.println("Grazie, " + user + ", ti auguro una buona esperienza.");
        System.out.println();
        return user;
    }

    public static String getMode(Scanner sc) throws IOException {
        System.out.println("Questo programma offre due modalità: la modalità criptazione, indicata con C, e la modalità decriptazione, indicata con D.");
        System.out.println("Digita la lettera associata alla modalità desiderata:");
        String mode = chiediInput(sc);
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
        System.out.println("Benvenut* nella modalità decriptazione, dovresti possedere un file dal nome 'outputMittenteToDestinatario.txt' dove tu sei il destinatario (da copiare in textFiles) pieno di numeri, ovvero criptato, e quattro chiavi numeriche etichettate r, s, p e q. Ti verrà chiesto di inserire le chiavi, e successivamente verrà stampato il messaggio decriptato nel terminale. In caso di errori di decriptazione, la colpa è da attribuirsi alle chiavi in quanto il programma è corretto per dimostrazione.");
        System.out.println();
    }

    public static String getSender(Scanner sc) throws IOException {
        System.out.println("Inserisci il nome del mittente");
        String sender = chiediInput(sc);
        System.out.println();
        return sender;
    }

    public static int[] inserisciChiaviD(Scanner sc) throws IOException {
        int[] chiavi = new int[4];
        String linea;
        System.out.println("Inserisci la chiave r");
        linea = sc.nextLine();
        chiavi[0] = Integer.parseInt(linea);
        System.out.println("Inserisci la chiave s");
        linea = sc.nextLine();
        chiavi[1] = Integer.parseInt(linea);
        System.out.println("Inserisci la chiave p");
        linea = sc.nextLine();
        chiavi[2] = Integer.parseInt(linea);
        System.out.println("Inserisci la chiave q");
        linea = sc.nextLine();
        chiavi[3] = Integer.parseInt(linea);
        return chiavi;
    }

    public static void stampaMessaggio(String fileName, int[] chiavi) throws IOException {
        System.out.println("Stampa del messaggio decriptato con la chiave [" + chiavi[0] + ", " + chiavi[1] + ", " + chiavi[2] + ", " + chiavi[3] + "].");
        System.out.println("----- Inizio messaggio! -----");
        System.out.println();
        fileReading.decriptaFile(fileName, "textFiles/index.txt", chiavi);
        System.out.println();
        System.out.println("----- Fine messaggio! -----");
        System.out.println();
    }

    public static void benvenutoC(String user) {
        System.out.println("Benvenut* nella modalità criptazione. Questa modalità richiede la presenza di un file di nome 'input" + user + ".txt' nella cartella textFiles, dove scriverai il tuo messaggio usando i simboli contenuti in 'textFiles/index.txt', e di un file vuoto di nome 'output" + user + ".txt' nella stessa cartella, che verrà riempito col messaggio criptato. Se non li hai ancora non preoccuparti, ti verrà data la possibilità di crearli.");
        System.out.println();
        System.out.println("Ti verrà chiesto di scegliere due numeri primi, p e q, entrambi idealmente minori di 46341, in modo che il loro prodotto non superi il limite degli int di Java, e tali che una volta calcolato phi = (p-1)*(q-1), questo sia strettamente maggiore di 106.");
        System.out.println("Poi dovrai scegliere la chiave s, con delle dovute accortezze:");
        System.out.println("Innanzitutto s dovrà essere strettamente minore di phi e non avere fattori in comune con esso, puoi controllare la fattorizzazione di phi su https://www.wolframalpha.com/ per essere più comod* (in ogni caso il programma ti dirà se ci sono problemi).");
        System.out.println("La chiave r verrà calcolata per te dal programma.");
        System.out.println();
    }

    public static void creaFileC(String tipo, String user, String receiver) throws IOException {
        String fileName = tipo + user + "To" + receiver + ".txt";
        fileReading.creaFile(fileName);
    }

    public static String getReceiver(Scanner sc) throws IOException{
        String receiver = "";
        System.out.println("Per favore, inserisci il nome del destinatario, come se fosse l'utente");
        receiver = receiver + sc.nextLine();
        System.out.println();
        return receiver;
    }

    public static void bisognoFileC(Scanner sc, String user, String receiver) throws IOException {
        System.out.println("Hai bisogno di creare il file di input, di output o entrambi? Digita 'in' per creare l'input, digita 'out' per creare l'output, digita 'both' per creare entrambi, altrimenti digita qualsiasi altro carattere per procedere. Nel caso in cui dovesse essere creato il file 'input" + user + "To" + receiver + ".txt', il programma terminerà per darti la possibilità di scrivere il tuo messaggio.");
        String prova = chiediInput(sc);
        boolean input = prova.equals("in");
        boolean output = prova.equals("out");
        boolean both = prova.equals("both");
        if (output || both) {
            creaFileC("output", user, receiver);
        }
        if (input || both) {
            creaFileC("input", user, receiver);
            termineEsecuzione();
        }
        System.out.println();
    }

    public static int[] inserisciChiaviC(Scanner sc) throws IOException {
        int[] chiavi = new int[4];
        String linea;
        System.out.println("Inserisci la chiave s");
        linea = sc.nextLine();
        chiavi[1] = Integer.parseInt(linea);
        System.out.println("Inserisci il primo p");
        linea = sc.nextLine();
        chiavi[2] = Integer.parseInt(linea);
        while(!RSA.isPrime(chiavi[2])){
            System.out.println(chiavi[2] + " non è primo, ritenta");
            linea = sc.nextLine();
            chiavi[2] = Integer.parseInt(linea);
        }
        while(chiavi[2] >= 46341){
            System.out.println("Sia p che q dovrebbero essere strettamente minori di 46341, ritenta con un p diverso");
            linea = sc.nextLine();
            chiavi[2] = Integer.parseInt(linea);
        }
        System.out.println("Inserisci il primo q");
        linea = sc.nextLine();
        chiavi[3] = Integer.parseInt(linea);
        while(!RSA.isPrime(chiavi[3])){
            System.out.println(chiavi[3] + " non è primo, ritenta");
            linea = sc.nextLine();
            chiavi[3] = Integer.parseInt(linea);
        }
        while(chiavi[3] >= 46341){
            System.out.println("Sia p che q dovrebbero essere strettamente minori di 46341, ritenta con un q diverso");
            linea = sc.nextLine();
            chiavi[3] = Integer.parseInt(linea);
        }
        int phi = (chiavi[3]-1)*(chiavi[2]-1);
        while(phi < 106){
            System.out.println("Il valore di phi, ovvero "+ phi + "è minore o uguale a 106, tenta con p e q diversi");
            System.out.println("Inserisci il primo p");
            linea = sc.nextLine();
            chiavi[2] = Integer.parseInt(linea);
            while(!RSA.isPrime(chiavi[2])){
                System.out.println(chiavi[2] + " non è primo, ritenta");
                linea = sc.nextLine();
                chiavi[2] = Integer.parseInt(linea);
            }
            while(chiavi[2] >= 46341){
                System.out.println("Sia p che q dovrebbero essere strettamente minori di 46341, ritenta con un p diverso");
                linea = sc.nextLine();
                chiavi[2] = Integer.parseInt(linea);
            }
            System.out.println("Inserisci il primo q");
            linea = sc.nextLine();
            chiavi[3] = Integer.parseInt(linea);
            while(!RSA.isPrime(chiavi[3])){
                System.out.println(chiavi[3] + " non è primo, ritenta");
                linea = sc.nextLine();
                chiavi[3] = Integer.parseInt(linea);
            }
            while(chiavi[3] >= 46341){
                System.out.println("Sia p che q dovrebbero essere strettamente minori di 46341, ritenta con un q diverso");
                linea = sc.nextLine();
                chiavi[3] = Integer.parseInt(linea);
            }
        }
        while(!RSA.checkCoprimi(phi,chiavi[1]) || chiavi[1]>=phi){
            System.out.println("Il valore scelto di s non è accettabile per il valore di phi, ovvero " + phi + ", riprova");
            linea = sc.nextLine();
            chiavi[1] = Integer.parseInt(linea);
        }
        chiavi[0] = RSA.EuclideEsteso(chiavi[1], chiavi[2], chiavi[3]);
        System.out.println("Il valore calcolato della chiave r è " + chiavi[0] + ".");
        return chiavi;
    }

    public static void criptazione(Scanner sc, String user, String receiver, int[] chiavi) throws IOException {
        System.out.println("Criptazione del file in corso...");
        String inputName = "textFiles/input" + user + "To" + receiver + ".txt";
        String outputName = "textFiles/output" + user + "To" + receiver + ".txt";
        fileReading.criptaFile(inputName, outputName, "textFiles/index.txt", chiavi);
        System.out.println("Criptazione completata! Digita 'S' per ottenere una stampa di controllo, altrimenti digita qualunque altro input per terminare l'esecuzione.");
        String prova = chiediInput(sc);
        if (prova.equals("S")) {
            stampaMessaggio(outputName, chiavi);
        }
    }

    public static boolean altraRun(Scanner sc) throws IOException {
        System.out.println("Se desideri ripetere l'esecuzione del programma, anche in altre modalità, digita 'R', altrimenti digita qualsiasi altro input per terminare l'esecuzione");
        String repeat = chiediInput(sc);
        return repeat.equals("R");
    }

    public static void termineEsecuzione() {
        System.out.println("------------------TERMINE ESECUZIONE------------------");
        System.out.println("Grazie di aver usato il mio programma, buona giornata.");
        System.out.println("In caso di problemi con il programma, visita https://fil-cyan-tron.github.io/, troverai i miei contatti dove potrai dirmi che sono un incompetente!");
        System.exit(0);
    }
}
