package pl.lublin.wsei.java.cwiczenia;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Controller {
    public Label lbFile;
    public ListView lstNoblisci;
    public TextField txtRok;
    public TextField txtKategoria;
    public TextArea txtMotywacja;
    public TextField txtKraj;
    public Button btnExport;
    private Noblista selNoblista;

    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Pliki CSV (*.csv)", "*.csv");
    String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();

    ObservableList<String> daneNoblistow = FXCollections.observableArrayList();
    InfoNoblisciList noblisciList;

    @FXML
    public void initialize() {
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setInitialDirectory(new File(currentPath));
        btnExport.setDisable(true);

        lstNoblisci.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number old_val, Number new_val) {
                        int index = new_val.intValue();
                        if(index > -1){
                            selNoblista = noblisciList.noblisci.get(index);
                            txtRok.setText(selNoblista.Year);
                            txtKategoria.setText(selNoblista.Category);
                            txtKraj.setText(selNoblista.Country);
                            txtMotywacja.setText(selNoblista.Motivation);
                        }
                        else
                        {
                            txtRok.setText("");
                            txtKategoria.setText("");
                            txtMotywacja.setText("");
                            txtKraj.setText("");
                            selNoblista = null;
                        }
                    }
                }
        );
    }

    public void btnOpenFileAction(ActionEvent actionEvent) {
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            noblisciList = new InfoNoblisciList(file.getAbsolutePath());
            lbFile.setText(file.getAbsolutePath());
            for (Noblista noblista : noblisciList.noblisci) daneNoblistow.add(noblista.FirstName + " " + noblista.LastName);
            lstNoblisci.setItems(daneNoblistow);
            btnExport.setDisable(false);
        }
        else {
            btnExport.setDisable(true);
            lbFile.setText("Prosz wczytać plik z rozszerzeniem csv ...");
        }
    }
//

    public void btnExportDanych(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Export.fxml"));
            Parent root = loader.load();
            Export controller = loader.<Export>getController();

            if(noblisciList!=null) {
                controller.setNoblisci(noblisciList);
            }

            Stage stage =  new Stage();
            stage.setTitle("Filtrowanie i zapisanie danych do pliku");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

