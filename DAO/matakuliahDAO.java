package DAO;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdbctodatabase.connectionutil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class matakuliahDAO {


    @FXML
    private TextField Deskripsi;

    @FXML
    private ChoiceBox<String> mataKuliahChoiceBox;

    @FXML
    private TextField sks;

    @FXML
    private TextField jadwal;

    @FXML
    private Button matakuliah;

    @FXML
    void initialize() {
        // Inisialisasi ChoiceBox dengan daftar mata kuliah
        mataKuliahChoiceBox.getItems().addAll("Pmrograman interpreter 1", "Pemrograman Berioentasi Object 2", "DAA 3", "PSD 4");

        // Inisialisasi nilai default
        mataKuliahChoiceBox.setValue("Pemograman interpreter 1");

    }

    @FXML
    void SendMatakuliah(ActionEvent event) {
        String selectedMataKuliah = mataKuliahChoiceBox.getValue();
        String deskripsiMatakuliah = Deskripsi.getText();
        String sksMatakuliah = sks.getText();
        String jadwalMatakuliah = jadwal.getText();

        if (selectedMataKuliah == null || deskripsiMatakuliah.isEmpty() || sksMatakuliah.isEmpty() || jadwalMatakuliah.isEmpty()) {
            showAlert("Error", "Semua field harus diisi");
            return;
        }
        try {
            int sksValue = Integer.parseInt(sksMatakuliah);
            if (sksValue <= 0 || sksValue > 24) {
                showAlert("Error", "SKS harus antara 1 dan 24");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Masukkan nilai SKS yang valid");
            return;
        }

        try (Connection connection = connectionutil.getConnection()) {
            String sql = "INSERT INTO matakuliah (deskripsi, sks, jadwal) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, deskripsiMatakuliah);
                preparedStatement.setString(2, sksMatakuliah);
                preparedStatement.setString(3, jadwalMatakuliah);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    showAlert("Success", "Data berhasil disimpan ke database");
                    clearFields();
                    navigateToMenusiakad();
                } else {
                    showAlert("Error", "Gagal menyimpan data ke database");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Gagal menyimpan data ke database. Error: " + e.getMessage());
        }
    }

    private void clearFields() {
        Deskripsi.clear();
        sks.clear();
        jadwal.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    private void navigateToMenusiakad() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistemakademik/menusiakadu.fxml"));

            Parent root = loader.load();

            menusiakaduDAO menusiakadController = loader.getController();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            Stage currentStage = (Stage) matakuliah.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Gagal memuat tampilan Menusiakadu");
        }
    }
}
