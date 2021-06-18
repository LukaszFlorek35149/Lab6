package pl.lublin.wsei.java.cwiczenia;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class InfoNoblisciList {

    public ArrayList<Noblista> noblisci;

    public InfoNoblisciList(String fileName) {
        noblisci = new ArrayList<Noblista>();
        String contents;
        try {
            contents = new String(Files.readAllBytes(Paths.get(fileName)));
        }
        catch(Exception e) {
            System.out.println("Błąd przy odczycie pliku nobel_prize_by_winner.csv"+e.getLocalizedMessage());
            e.printStackTrace();
            contents="";
        }
        String[] items = contents.split("\n");
        for (int i = 1; i < items.length;i++) {
            items[i]=items[i].replaceAll(",(?!(?:[^\"]*\"[^\"]*\")*[^\"]*$)", "");
            items[i]=items[i].replaceAll(",,", " , , ");
            items[i]=items[i].replaceAll("\"\"\"", "\"");
            noblisci.add(new Noblista(items[i]));
        }
    }
}
