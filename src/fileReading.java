import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class fileReading {
    public static void closure(Scanner sc, FileReader fr) throws IOException {
        if (sc != null) {
            sc.close();
        }
        if (fr != null) {
            fr.close();
        }
    }

    public static void gigaClosure(Scanner sc, FileReader fr, FileWriter fw) throws IOException {
        if (sc != null) {
            sc.close();
        }
        if (fr != null) {
            fr.close();
        }
        if (fw != null) {
            fw.close();
        }
    }

    public static ArrayList<String> getIndex(String fileName) throws IOException {
        ArrayList<String> index = new ArrayList<>(0);
        FileReader fr = null;
        Scanner sc = null;
        index.add(" ");
        try {
            fr = new FileReader(fileName);
            sc = new Scanner(fr);
            while (sc.hasNextLine()) {
                index.add(sc.nextLine());
            }
        } finally {
            closure(sc, fr);
        }
        return index;
    }

    public static int[] stringaIndicizzata(String input, ArrayList<String> index) {
        int[] indici = new int[input.length()];
        String[] inputSplit = input.split("");
        for (int i = 0; i < indici.length; i++) {
            for (int j = 0; j < index.size(); j++) {
                indici[i] = 0;
                if (inputSplit[i].equals(index.get(j))) {
                    indici[i] = j;
                    break;
                }
            }
        }
        return indici;
    }

    public static void criptaFile(String inputName, String outputName, String indexName, int[] chiavi) throws IOException {
        FileReader fr = null;
        FileWriter fw = null;
        Scanner sc = null;
        ArrayList<String> index;
        try {
            index = getIndex(indexName);
            fr = new FileReader(inputName);
            fw = new FileWriter(outputName);
            sc = new Scanner(fr);
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                int[] numeri = stringaIndicizzata(linea, index);
                if(linea.equals("")){
                    fw.write("0");
                } else {
                    for (int i = 0; i < numeri.length; i++) {
                        numeri[i] = RSA.criptaInt(numeri[i], chiavi);
                        String numerino = "" + numeri[i];
                        fw.write(numerino);
                        if (i < numeri.length - 1) {
                            fw.write(" ");
                        }
                    }
                }
                if (sc.hasNextLine()) {
                    fw.write(System.getProperty("line.separator"));
                }
            }
        } finally {
            gigaClosure(sc, fr, fw);
        }
    }

    public static void decriptaFile(String fileName, String indexName, int[] chiavi) throws IOException {
        FileReader fr = null;
        Scanner sc = null;
        ArrayList<String> index;
        try {
            index = getIndex(indexName);
            fr = new FileReader(fileName);
            sc = new Scanner(fr);
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] lineaSplit = linea.split(" ");
                int[] numeri = new int[lineaSplit.length];
                String lineaOutput = "";
                for (int i = 0; i < numeri.length; i++) {
                    numeri[i] = Integer.parseInt(lineaSplit[i]);
                    numeri[i] = RSA.decriptaInt(numeri[i], chiavi);
                    lineaOutput = lineaOutput + index.get(numeri[i]);
                }
                System.out.println(lineaOutput);
            }
        } finally {
            closure(sc, fr);
        }
    }
}