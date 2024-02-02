package DAO;

import com.example.sistemakademik.LoginUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdbctodatabase.connectionutil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MahasiswaDAO {

    @FXML
    private Button btncompleted;

    @FXML
    private TextField daftarinformasi;

    @FXML
    private TextField daftarnama;

    @FXML
    private TextField daftarnpm;

    @FXML
    private TextField daftarpassword;

    @FXML
    private TextField daftarprogram;

    @FXML
    private TextField daftarriwayat;

    @FXML
    private TextField USERNAMED;

    private String registeredUsername;
    private String registeredPassword;

    public void setRegisteredUser(String username, String password) {
        this.registeredUsername = username;
        this.registeredPassword = password;
    }

    public String getRegisteredUsername() {
        return registeredUsername;
    }

    public String getRegisteredPassword() {
        return registeredPassword;
    }

    @FXML
    void sendCompleted(ActionEvent event) {
        String username = USERNAMED.getText();
        String password = daftarpassword.getText();
        String nama = daftarnama.getText();
        String informasiPribadi = daftarinformasi.getText();
        String riwayatPendidikan = daftarriwayat.getText();
        String programStudi = daftarprogram.getText();
        String npm = daftarnpm.getText();

        System.out.println("Data to be inserted: " + username + ", " + password + ", " + nama + ", " +
                informasiPribadi + ", " + riwayatPendidikan + ", " + programStudi + ", " + npm);

        try (Connection connection = connectionutil.getConnection()) {
            String sql = "INSERT INTO users (username, nama, informasi_pribadi, riwayat_pendidikan, program_studi, npm, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, USERNAMED.getText());
                preparedStatement.setString(2, nama);
                preparedStatement.setString(3, informasiPribadi);
                preparedStatement.setString(4, riwayatPendidikan);
                preparedStatement.setString(5, programStudi);
                preparedStatement.setString(6, npm);
                preparedStatement.setString(7, password);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data berhasil disimpan ke database");
                    setRegisteredUser(username, password);
                    navigateToLogin();
                } else {
                    System.out.println("Gagal menyimpan data ke database. Tidak ada baris yang terpengaruh.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Gagal menyimpan data ke database. Error: " + e.getMessage());
        }
    }

    private void navigateToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistemakademik/LoginUser.fxml"));
            Parent root = loader.load();
            LoginUser loginUser = loader.getController();
            loginUser.setMahasiswaDAO(this);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            Stage currentStage = (Stage) btncompleted.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

