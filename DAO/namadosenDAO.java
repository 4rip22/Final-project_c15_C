package DAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class namadosenDAO {

    @FXML
    private Button BtnBackTo;

    @FXML
    private Text menuUser;

    @FXML
    void SendBackDosen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistemakademik/menusiakadu.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();

            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
