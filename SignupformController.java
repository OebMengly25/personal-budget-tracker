import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;

public class SignupformController {

    @FXML private TextField DateofBirthField;
    @FXML private Button createAccountButton;
    @FXML private TextField nameField;
    @FXML private PasswordField passwordField;
    @FXML private Button signin_button;
    @FXML private Label statusLabel;
    @FXML private CheckBox termsCheckbox;

    @FXML
    void createAccountButton_action(ActionEvent event) {
        String name = nameField.getText();
        String password = passwordField.getText();
        String dateOfBirth = DateofBirthField.getText();
        boolean termsAccepted = termsCheckbox.isSelected();

        if (name.isEmpty() || password.isEmpty() || dateOfBirth.isEmpty() || !termsAccepted) {
            statusLabel.setText("Please fill all fields and accept terms.");
            return;
        }

        // Check for duplicate user
        try (BufferedReader br = new BufferedReader(new FileReader("database.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(name)) {
                    statusLabel.setText("Username already exists.");
                    return;
                }
            }
        } catch (IOException ignored) {}

        // Save user credentials
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("database.csv", true))) {
            bw.write(name + "," + password + "," + dateOfBirth);
            bw.newLine();
        } catch (IOException ex) {
            statusLabel.setText("Error saving to database.");
            ex.printStackTrace();
            return;
        }

        // Create user file
        new File("users").mkdir();  // ensure folder exists
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users/user_" + name + ".csv"))) {
            writer.write("Date,Type,Category,Amount,Note\n");
        } catch (IOException e) {
            statusLabel.setText("Error creating user file.");
            e.printStackTrace();
            return;
        }

        statusLabel.setText("Account created successfully!");
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(_ -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Stage stage = (Stage) createAccountButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Login");
                stage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        pause.play();
    }

    @FXML
    void signin_button_action(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage = (Stage) signin_button.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void termsCheckbox_action(ActionEvent event) {
        System.out.println(termsCheckbox.isSelected() ? "Terms accepted." : "Terms not accepted.");
    }
}
