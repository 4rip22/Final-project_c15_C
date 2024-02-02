package DAO;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class menusiakaduDAO {

    @FXML
    private Button matakuliah;

    @FXML
    private Button menuNamaDosen;

    @FXML
    private Button menuRuangan;

    @FXML
    private Text menuUser;

    @FXML
    void SendRuangan(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistemakademik/ruangan.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));


            stage.show();


            ((Stage) menuRuangan.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    @FXML
    void Sendmatakuliah(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistemakademik/matakuliah.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) matakuliah.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void menuNamaDosen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistemakademik/namadosen.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();
            ((Stage) menuNamaDosen.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
