package pl.lublin.wsei.java.cwiczenia;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Export {

    public TextField txtRok;
    public TextField txtKraj;
    public TextField txtKategoria;
    public Label lblWynik;
    InfoNoblisciList noblisciList;
    public ArrayList<String> filtrNoblisci = new ArrayList<String>();

    public void setNoblisci(InfoNoblisciList daneNoblistow) {
        this.noblisciList = daneNoblistow;
    }

    public void Save(ActionEvent actionEvent) {
        try {
            FileWriter myWriter1 = new FileWriter("nobel_filtered.csv");
            myWriter1.write("");
            myWriter1.close();
            String rok = txtRok.getText().toLowerCase();
            String kategoria = txtKategoria.getText().toLowerCase();
            String kraj = txtKraj.getText().toLowerCase();

            ArrayList<Noblista> filteredNoblisciRok = new ArrayList<>();
            for(Noblista nb : noblisciList.noblisci)
            {
                if (rok.equals(""))
                    filteredNoblisciRok.add(nb);
                else if (nb.Year.toLowerCase().contains(rok)) filteredNoblisciRok.add(nb);
            }
            filtrNoblisci.clear();

            ArrayList<Noblista> filteredNoblisciKraj = new ArrayList<>();
            for(Noblista nb : filteredNoblisciRok) {
                if (kraj.equals(""))
                    filteredNoblisciKraj.add(nb);
                else if (nb.Country.toLowerCase().contains(kraj)) {
                    filteredNoblisciKraj.add(nb);
                }
            }
            for(Noblista nb : filteredNoblisciKraj) {
                if (kategoria.equals(""))
                    filtrNoblisci.add(nb.DataRow);
                else if (nb.Category.toLowerCase().contains(kategoria)) {
                    filtrNoblisci.add(nb.DataRow);
                }
            }
            FileWriter myWriter = new FileWriter("nobel_filtered.csv");
            for(int i = 0; i< filtrNoblisci.size(); i++) myWriter.append(filtrNoblisci.get(i));
            myWriter.close();
            System.out.println("Pomyślnie zapisano do pliku "+filtrNoblisci.size()+" noblistów");
            filtrNoblisci.clear();
        } catch (IOException exception) {
            System.out.println("Wystąpił błąd.");
            exception.printStackTrace();
        }

    }}
